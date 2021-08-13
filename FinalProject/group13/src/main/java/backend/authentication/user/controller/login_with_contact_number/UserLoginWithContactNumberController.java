package backend.authentication.user.controller.login_with_contact_number;

import backend.authentication.user.database.UserDatabaseConstant;
import backend.authentication.user.database.login_with_contact_number.UserLoginWithContactNumberQueryBuilderDAO;
import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.HashAlgorithmUtil;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static backend.authentication.user.database.UserDatabaseConstant.USER_ID_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_FIRST_NAME_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_LAST_NAME_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_GENDER_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_EMAIL_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_PASSWORD_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_CONTACT_NUMBER_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_BLOOD_GROUP_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_FIRST_LINE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_STREET_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_CITY_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_COUNTRY_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_ZIP_CODE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ACCOUNT_ACTIVE_COLUMN;

/**
 * {@code UserLoginWithContactNumberController} implements the
 * {@code UserLoginWithContactNumberControllerDAO} to provide a concrete
 * implementation for the user login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserLoginWithContactNumberController
    implements UserLoginWithContactNumberControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // user login with contact number query builder instance.
  private final UserLoginWithContactNumberQueryBuilderDAO
      userLoginWithContactNumberQueryBuilderDAO;

  /**
   * Constructs this {@code UserLoginWithContactNumberController} instance.
   *
   * @param databaseConnectionDAO                     database connection
   *                                                  instance.
   * @param userLoginWithContactNumberQueryBuilderDAO user login
   *                                                  with contact number
   *                                                  query builder
   *                                                  instance.
   */
  public UserLoginWithContactNumberController(final DatabaseConnectionDAO
                                                  databaseConnectionDAO,
                                              final UserLoginWithContactNumberQueryBuilderDAO
                                                  userLoginWithContactNumberQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.userLoginWithContactNumberQueryBuilderDAO =
        userLoginWithContactNumberQueryBuilderDAO;
  }

  /**
   * Validate login credentials of this user.
   *
   * @param contactNumber contact number of this user.
   * @param password      password of this user.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   */
  private void validateLoginWithContactNumberCredentials(final String contactNumber,
                                                         final String password)
      throws UserAuthenticationException {
    final boolean isUserContactNumberValid = (contactNumber != null) &&
        (!contactNumber.trim().isEmpty()) &&
        (RegistrationValidationUtil.isContactNumberValid(contactNumber.trim()));
    final boolean isUserPasswordValid = (password != null) &&
        (!password.trim().isEmpty()) &&
        (RegistrationValidationUtil.isPasswordValid(password.trim()));
    if (!isUserContactNumberValid || !isUserPasswordValid) {
      throw new UserAuthenticationException("Invalid contact number and/or password");
    }
  }

  /**
   * Prepare user instance after login credentials are correct.
   *
   * @param resultSet result set of logged-in user.
   *
   * @return user instance of logged-in user.
   *
   * @throws SQLException if any error occurs while user authentication.
   */
  private User prepareUser(final ResultSet resultSet)
      throws SQLException {
    User user;
    final int id =
        resultSet.getInt(USER_ID_COLUMN);
    final String firstName =
        resultSet.getString(USER_FIRST_NAME_COLUMN);
    final String lastName =
        resultSet.getString(USER_LAST_NAME_COLUMN);
    final String gender =
        resultSet.getString(USER_GENDER_COLUMN);
    final String dateOfBirth =
        resultSet.getString(USER_DATE_OF_BIRTH_COLUMN);
    final String email =
        resultSet.getString(USER_EMAIL_COLUMN);
    final String password =
        resultSet.getString(USER_PASSWORD_COLUMN);
    final String contactNum =
        resultSet.getString(USER_CONTACT_NUMBER_COLUMN);
    final String bloodGroup =
        resultSet.getString(USER_BLOOD_GROUP_COLUMN);
    final String addFirstLine =
        resultSet.getString(USER_ADDRESS_FIRST_LINE_COLUMN);
    final String addStreet =
        resultSet.getString(USER_ADDRESS_STREET_COLUMN);
    final String addCity =
        resultSet.getString(USER_ADDRESS_CITY_COLUMN);
    final String addProvince =
        resultSet.getString(USER_ADDRESS_PROVINCE_COLUMN);
    final String addZipCode =
        resultSet.getString(USER_ADDRESS_ZIP_CODE_COLUMN);
    final String addCountry =
        resultSet.getString(USER_ADDRESS_COUNTRY_COLUMN);
    user = new User(
        id,
        firstName,
        lastName,
        gender,
        dateOfBirth,
        email,
        password,
        contactNum,
        bloodGroup,
        addFirstLine,
        addStreet,
        addCity,
        addProvince,
        addZipCode,
        addCountry,
        true);
    return user;
  }

  /**
   * Performs login of this user with contact number.
   *
   * @param contactNumber user contact number.
   * @param password      user password.
   *
   * @return logged-in user instance.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public User loginWithContactNumber(final String contactNumber,
                                     final String password)
      throws UserAuthenticationException,
      DatabaseConnectionException {
    validateLoginWithContactNumberCredentials(contactNumber, password);
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             dbConnection.createStatement();
         final ResultSet userResultSet =
             statement.executeQuery(userLoginWithContactNumberQueryBuilderDAO.selectUserByContactNumber(contactNumber))) {
      if (userResultSet == null) {
        throw new UserAuthenticationException("Invalid contact number and/or password");
      }
      if (userResultSet.next()) {
        final boolean isPasswordValid =
            HashAlgorithmUtil.validateSHA256Hash(password, userResultSet.getString(USER_PASSWORD_COLUMN));
        if (!isPasswordValid) {
          throw new UserAuthenticationException("Invalid contact number and/or password");
        }
        final boolean isAccountActive =
            userResultSet.getBoolean(USER_ACCOUNT_ACTIVE_COLUMN);
        if (!isAccountActive) {
          throw new UserAuthenticationException("Account not active");
        }
        return prepareUser(userResultSet);
      }
      return null;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}