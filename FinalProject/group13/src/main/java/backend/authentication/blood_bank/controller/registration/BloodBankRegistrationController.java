package backend.authentication.blood_bank.controller.registration;

import backend.authentication.blood_bank.database.registration.BloodBankRegistrationQueryBuilderDAO;
import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.HashAlgorithmUtil;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * {@code BloodBankRegistrationController} implements the
 * {@code BloodBankRegistrationControllerDAO} to provide a concrete
 * implementation for the blood bank registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRegistrationController
    implements BloodBankRegistrationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank registration query builder instance.
  private final BloodBankRegistrationQueryBuilderDAO
      bloodBankRegistrationQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankRegistrationController} instance.
   *
   * @param databaseConnectionDAO                database connection instance.
   * @param bloodBankRegistrationQueryBuilderDAO blood bank registration
   *                                             query builder instance.
   */
  public BloodBankRegistrationController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodBankRegistrationQueryBuilderDAO bloodBankRegistrationQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankRegistrationQueryBuilderDAO =
        bloodBankRegistrationQueryBuilderDAO;
  }

  /**
   * Validates blood bank fields.
   *
   * @param bloodBank            blood bank instance.
   * @param securityQuestion1Ans security question 1 answer.
   * @param securityQuestion2Ans security question 2 answer.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   */
  private void validateBloodBankFields(final BloodBank bloodBank,
                                       final String securityQuestion1Ans,
                                       final String securityQuestion2Ans)
      throws BloodBankAuthenticationException {
    // Blood bank name validation.
    final boolean isBloodBankNameValid = (bloodBank.getName() != null) &&
        (!bloodBank.getName().trim().isEmpty()) &&
        (RegistrationValidationUtil.isNameValid(bloodBank.getName().trim()));
    if (!isBloodBankNameValid) {
      throw new BloodBankAuthenticationException("Invalid blood bank name.");
    }
    // Blood bank email validation.
    final boolean isBloodBankEmailValid = (bloodBank.getEmail() != null) &&
        (!bloodBank.getEmail().trim().isEmpty()) &&
        (RegistrationValidationUtil.isEmailValid(bloodBank.getEmail().trim()));
    if (!isBloodBankEmailValid) {
      throw new BloodBankAuthenticationException("Invalid email.");
    }
    // Blood bank password validation.
    final boolean isBloodBankPasswordValid = (bloodBank.getPassword() != null) &&
        (!bloodBank.getPassword().trim().isEmpty()) &&
        (RegistrationValidationUtil.isPasswordValid(bloodBank.getPassword().trim()));
    if (!isBloodBankPasswordValid) {
      throw new BloodBankAuthenticationException("Invalid password.");
    }
    // Blood bank contact number validation.
    final boolean isBloodBankContactNumberValid = (bloodBank.getContactNumber() != null) &&
        (!bloodBank.getContactNumber().trim().isEmpty()) &&
        (RegistrationValidationUtil.isContactNumberValid(bloodBank.getContactNumber().trim()));
    if (!isBloodBankContactNumberValid) {
      throw new BloodBankAuthenticationException("Invalid contact number.");
    }
    // Blood bank address first line validation.
    final boolean isBloodBankAddressFirstLineValid = (bloodBank.getAddressFirstLine() != null) &&
        (!bloodBank.getAddressFirstLine().trim().isEmpty());
    if (!isBloodBankAddressFirstLineValid) {
      throw new BloodBankAuthenticationException("Invalid address first line.");
    }
    // Blood bank address street validation.
    final boolean isBloodBankAddressStreetValid = (bloodBank.getAddressStreet() != null) &&
        (!bloodBank.getAddressStreet().trim().isEmpty());
    if (!isBloodBankAddressStreetValid) {
      throw new BloodBankAuthenticationException("Invalid address street.");
    }
    // Blood bank address city validation.
    final boolean isBloodBankAddressCityValid = (bloodBank.getAddressCity() != null) &&
        (!bloodBank.getAddressCity().trim().isEmpty());
    if (!isBloodBankAddressCityValid) {
      throw new BloodBankAuthenticationException("Invalid address city.");
    }
    // Blood bank address province validation.
    final boolean isBloodBankAddressProvinceValid = (bloodBank.getAddressProvince() != null) &&
        (!bloodBank.getAddressProvince().trim().isEmpty());
    if (!isBloodBankAddressProvinceValid) {
      throw new BloodBankAuthenticationException("Invalid address province.");
    }
    // Blood bank address zip code validation.
    final boolean isBloodBankZipCodeValid = (bloodBank.getAddressZipCode() != null) &&
        (!bloodBank.getAddressZipCode().trim().isEmpty()) &&
        (RegistrationValidationUtil.isZipCodeValid(bloodBank.getAddressZipCode().trim()));
    if (!isBloodBankZipCodeValid) {
      throw new BloodBankAuthenticationException("Invalid address zip code.");
    }
    // Blood bank address country validation.
    final boolean isBloodBankAddressCountryValid = (bloodBank.getAddressCountry() != null) &&
        (!bloodBank.getAddressCountry().trim().isEmpty());
    if (!isBloodBankAddressCountryValid) {
      throw new BloodBankAuthenticationException("Invalid address country.");
    }
    // Blood bank security question 1 ans validation.
    final boolean isBloodBankSQ1AnsValid = (securityQuestion1Ans != null) &&
        (!securityQuestion1Ans.trim().isEmpty());
    if (!isBloodBankSQ1AnsValid) {
      throw new BloodBankAuthenticationException("Invalid security question 1 answer.");
    }
    // Blood bank security question 2 ans validation.
    final boolean isBloodBankSQ2AnsValid = (securityQuestion2Ans != null) &&
        (!securityQuestion2Ans.trim().isEmpty());
    if (!isBloodBankSQ2AnsValid) {
      throw new BloodBankAuthenticationException("Invalid security question 2 answer.");
    }
  }

  /**
   * Performs registration of this blood bank.
   *
   * @param bloodBank            blood bank to insert in the table.
   * @param securityQuestion1Id  security question 1 id of this blood bank.
   * @param securityQuestion1Ans security question 1 answer of this blood bank.
   * @param securityQuestion2Id  security question 2 id of this blood bank.
   * @param securityQuestion2Ans security question 2 answer of this blood bank.
   *
   * @return {@code true} if blood bank registered successfully otherwise
   * {@code false}.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public boolean register(final BloodBank bloodBank,
                          final int securityQuestion1Id,
                          String securityQuestion1Ans,
                          final int securityQuestion2Id,
                          String securityQuestion2Ans)
      throws BloodBankAuthenticationException, DatabaseConnectionException {
    if (bloodBank == null) {
      throw new BloodBankAuthenticationException("Invalid blood bank.");
    }
    validateBloodBankFields(bloodBank, securityQuestion1Ans, securityQuestion2Ans);
    bloodBank.setName(bloodBank.getName().trim());
    bloodBank.setEmail(bloodBank.getEmail().trim());
    bloodBank.setContactNumber(bloodBank.getContactNumber().trim());
    bloodBank.setAddressFirstLine(bloodBank.getAddressFirstLine().trim());
    bloodBank.setAddressStreet(bloodBank.getAddressStreet().trim());
    bloodBank.setAddressCity(bloodBank.getAddressCity().trim());
    bloodBank.setAddressProvince(bloodBank.getAddressProvince().trim());
    bloodBank.setAddressZipCode(bloodBank.getAddressZipCode().trim());
    bloodBank.setAddressCountry(bloodBank.getAddressCountry().trim());
    securityQuestion1Ans = securityQuestion1Ans.trim();
    securityQuestion2Ans = securityQuestion2Ans.trim();
    final String passwordSha256Hash =
        HashAlgorithmUtil.getSHA256Hash(bloodBank.getPassword().trim());
    if (passwordSha256Hash == null) {
      throw new BloodBankAuthenticationException("Failed to hash password" +
          ". Please select a different password.");
    }
    bloodBank.setPassword(passwordSha256Hash);
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final CallableStatement bloodBankRegisterStatement =
             dbConnection.prepareCall(bloodBankRegistrationQueryBuilderDAO
                 .storedProcedureForBloodBankRegistrationQuery(bloodBank,
                     securityQuestion1Id,
                     securityQuestion1Ans,
                     securityQuestion2Id,
                     securityQuestion2Ans))) {
      bloodBankRegisterStatement.execute();
      return true;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}