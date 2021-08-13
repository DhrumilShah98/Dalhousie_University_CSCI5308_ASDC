package backend.authentication.blood_bank.controller.login_with_email;

import backend.authentication.blood_bank.database.BloodBankDatabaseConstant;
import backend.authentication.blood_bank.database.login_with_email.BloodBankLoginWithEmailQueryBuilderDAO;
import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.HashAlgorithmUtil;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ID_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_NAME_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_EMAIL_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_PASSWORD_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_CONTACT_NUMBER_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_STREET_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_CITY_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_COUNTRY_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN;

/**
 * {@code BloodBankLoginWithEmailController} implements the
 * {@code BloodBankLoginWithEmailControllerDAO} to provide a concrete
 * implementation for the blood bank login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankLoginWithEmailController
    implements BloodBankLoginWithEmailControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank login with email query builder instance.
  private final BloodBankLoginWithEmailQueryBuilderDAO
      bloodBankLoginWithEmailQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankLoginWithEmailController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodBankLoginWithEmailQueryBuilderDAO blood bank login with email
   *                                               query builder instance.
   */
  public BloodBankLoginWithEmailController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodBankLoginWithEmailQueryBuilderDAO bloodBankLoginWithEmailQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankLoginWithEmailQueryBuilderDAO =
        bloodBankLoginWithEmailQueryBuilderDAO;
  }

  /**
   * Validate login credentials of this blood bank.
   *
   * @param email    email address of this blood bank.
   * @param password password of this blood bank.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   */
  private void validateLoginWithEmailCredentials(final String email,
                                                 final String password)
      throws BloodBankAuthenticationException {
    final boolean isBloodBankEmailValid = (email != null) &&
        (!email.trim().isEmpty()) &&
        (RegistrationValidationUtil.isEmailValid(email.trim()));
    final boolean isBloodBankPasswordValid = (password != null) &&
        (!password.trim().isEmpty()) &&
        (RegistrationValidationUtil.isPasswordValid(password.trim()));
    if (!isBloodBankEmailValid || !isBloodBankPasswordValid) {
      throw new BloodBankAuthenticationException("Invalid email and/or password");
    }
  }

  /**
   * Prepare blood bank instance after login credentials are correct.
   *
   * @param resultSet result set of logged-in blood bank.
   *
   * @return blood bank instance of logged-in blood bank.
   *
   * @throws SQLException if any error occurs while blood bank authentication.
   */
  private BloodBank prepareBloodBank(final ResultSet resultSet)
      throws SQLException {
    BloodBank bloodBank;
    final int id =
        resultSet.getInt(BLOOD_BANK_ID_COLUMN);
    final String bloodBankName =
        resultSet.getString(BLOOD_BANK_NAME_COLUMN);
    final String email =
        resultSet.getString(BLOOD_BANK_EMAIL_COLUMN);
    final String password =
        resultSet.getString(BLOOD_BANK_PASSWORD_COLUMN);
    final String contactNum =
        resultSet.getString(BLOOD_BANK_CONTACT_NUMBER_COLUMN);
    final String addFirstLine =
        resultSet.getString(BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN);
    final String addStreet =
        resultSet.getString(BLOOD_BANK_ADDRESS_STREET_COLUMN);
    final String addCity =
        resultSet.getString(BLOOD_BANK_ADDRESS_CITY_COLUMN);
    final String addProvince =
        resultSet.getString(BLOOD_BANK_ADDRESS_PROVINCE_COLUMN);
    final String addZipCode =
        resultSet.getString(BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN);
    final String addCountry =
        resultSet.getString(BLOOD_BANK_ADDRESS_COUNTRY_COLUMN);
    bloodBank = new BloodBank(
        id,
        bloodBankName,
        email,
        password,
        contactNum,
        addFirstLine,
        addStreet,
        addCity,
        addProvince,
        addZipCode,
        addCountry,
        true);
    return bloodBank;
  }

  /**
   * Performs login of this blood bank with email.
   *
   * @param email    blood bank email.
   * @param password blood bank password.
   *
   * @return logged-in blood bank instance.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public BloodBank loginWithEmail(final String email, final String password)
      throws BloodBankAuthenticationException, DatabaseConnectionException {
    validateLoginWithEmailCredentials(email, password);
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodBankLoginStatement = dbConnection.createStatement();
         final ResultSet bloodBankResultSet =
             bloodBankLoginStatement.executeQuery(bloodBankLoginWithEmailQueryBuilderDAO
                 .selectBloodBankByEmailQuery(email))) {
      if (bloodBankResultSet == null) {
        throw new BloodBankAuthenticationException("Invalid email and/or password");
      }
      if (bloodBankResultSet.next()) {
        final boolean isPasswordValid =
            HashAlgorithmUtil.validateSHA256Hash(password,
                bloodBankResultSet
                    .getString(BLOOD_BANK_PASSWORD_COLUMN));
        if (!isPasswordValid) {
          throw new BloodBankAuthenticationException("Invalid email and/or password");
        }
        final boolean isAccountActive = bloodBankResultSet
            .getBoolean(BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN);
        if (!isAccountActive) {
          throw new BloodBankAuthenticationException("Account not active");
        }
        return prepareBloodBank(bloodBankResultSet);
      }
      throw new BloodBankAuthenticationException("Invalid email and/or password");
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}