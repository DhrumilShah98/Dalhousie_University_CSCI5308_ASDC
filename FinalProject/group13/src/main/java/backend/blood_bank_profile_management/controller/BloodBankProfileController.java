package backend.blood_bank_profile_management.controller;

import backend.authentication.blood_bank.model.BloodBank;
import backend.blood_bank_profile_management.database.update_blood_bank_profile_details.UpdateBloodBankProfileDetailsQueryBuilderDAO;
import backend.blood_bank_profile_management.exception.BloodBankProfileException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code BloodBankProfileController} implements the
 * {@code BloodBankProfileControllerDAO} to provide a concrete
 * implementation for updating blood bank profile details.
 */
public class BloodBankProfileController
    implements BloodBankProfileControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Update blood bank profile details query builder instance.
  private final UpdateBloodBankProfileDetailsQueryBuilderDAO
      updateBloodBankProfileDetailsQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankProfileController} instance.
   *
   * @param databaseConnectionDAO                        database connection
   *                                                     instance.
   * @param updateBloodBankProfileDetailsQueryBuilderDAO update blood bank
   *                                                     profile details
   *                                                     query builder instance.
   */
  public BloodBankProfileController(
      final DatabaseConnectionDAO
          databaseConnectionDAO,
      final UpdateBloodBankProfileDetailsQueryBuilderDAO
          updateBloodBankProfileDetailsQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.updateBloodBankProfileDetailsQueryBuilderDAO =
        updateBloodBankProfileDetailsQueryBuilderDAO;
  }

  /**
   * @param bloodBank object of this blood bank.
   *
   * @throws BloodBankProfileException if any error occurs while updating the
   *                                   blood bank profile details.
   */
  private void validateBloodBankProfileFields(final BloodBank bloodBank)
      throws BloodBankProfileException {

    // Checking if Blood Bank Account is Active
    if (bloodBank.isAccountActive()) {
      // Blood Bank name validation
      final boolean isBloodBankNameValid = (bloodBank.getName() != null) &&
          (!bloodBank.getName().trim().isEmpty()) &&
          (RegistrationValidationUtil.isNameValid(bloodBank.getName().trim()));
      if (!isBloodBankNameValid) {
        throw new BloodBankProfileException("Invalid blood bank name.");
      }

      // Blood Bank address first line validation
      final boolean isBloodBankAddressFirstLineValid =
          (bloodBank.getAddressFirstLine() != null) &&
              (!bloodBank.getAddressFirstLine().trim().isEmpty());
      if (!isBloodBankAddressFirstLineValid) {
        throw new BloodBankProfileException("Invalid address first line.");
      }

      // Blood Bank address street validation
      final boolean isBloodBankAddressStreetValid =
          (bloodBank.getAddressStreet() != null) &&
              (!bloodBank.getAddressStreet().trim().isEmpty());
      if (!isBloodBankAddressStreetValid) {
        throw new BloodBankProfileException("Invalid address street.");
      }

      // Blood Bank address city validation
      final boolean isBloodBankAddressCityValid =
          (bloodBank.getAddressCity() != null) &&
              (!bloodBank.getAddressCity().trim().isEmpty());
      if (!isBloodBankAddressCityValid) {
        throw new BloodBankProfileException("Invalid address city.");
      }

      // Blood Bank address province validation
      final boolean isBloodBankAddressProvinceValid =
          (bloodBank.getAddressProvince() != null) &&
              (!bloodBank.getAddressProvince().trim().isEmpty());
      if (!isBloodBankAddressProvinceValid) {
        throw new BloodBankProfileException("Invalid address province.");
      }

      // Blood Bank address zip code validation
      final boolean isBloodBankZipCodeValid =
          (bloodBank.getAddressZipCode() != null) &&
              (!bloodBank.getAddressZipCode().trim().isEmpty()) &&
              (RegistrationValidationUtil.isZipCodeValid(bloodBank.getAddressZipCode().trim()));
      if (!isBloodBankZipCodeValid) {
        throw new BloodBankProfileException("Invalid address zip code.");
      }

      // Blood Bank address country validation
      final boolean isBloodBankAddressCountryValid =
          (bloodBank.getAddressCountry() != null) &&
              (!bloodBank.getAddressCountry().trim().isEmpty());
      if (!isBloodBankAddressCountryValid) {
        throw new BloodBankProfileException("Invalid address country.");
      }
    } else {
      throw new BloodBankProfileException("Blood Bank Account is Inactive. Cannot Update Profile Details.");
    }
  }

  /**
   * @param bloodBank object of this blood bank.
   *
   * @return boolean true/false for successful update.
   *
   * @throws BloodBankProfileException   if any error occurs while updating the
   *                                     blood bank profile details.
   * @throws DatabaseConnectionException if any error occurs while connecting
   *                                     to the database.
   */
  @Override
  public boolean updateBloodBankProfileDetails(final BloodBank bloodBank)
      throws BloodBankProfileException,
      DatabaseConnectionException {

    // Blood Bank object null
    if (bloodBank == null) {
      throw new BloodBankProfileException("Null Blood Bank.");
    }

    // Validate Blood Bank Profile Fields
    validateBloodBankProfileFields(bloodBank);

    // Trim extra spaces from Blood Bank Profile Details
    bloodBank.setName(bloodBank.getName().trim());
    bloodBank.setAddressFirstLine(bloodBank.getAddressFirstLine().trim());
    bloodBank.setAddressStreet(bloodBank.getAddressStreet().trim());
    bloodBank.setAddressCity(bloodBank.getAddressCity().trim());
    bloodBank.setAddressProvince(bloodBank.getAddressProvince().trim());
    bloodBank.setAddressZipCode(bloodBank.getAddressZipCode().trim());
    bloodBank.setAddressCountry(bloodBank.getAddressCountry().trim());

    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement()) {
      final String updateBloodBankQuery =
          updateBloodBankProfileDetailsQueryBuilderDAO.updateBloodBankProfileDetailsQuery(bloodBank);
      final int bloodBankRowUpdated =
          statement.executeUpdate(updateBloodBankQuery);

      if (bloodBankRowUpdated > 0) {
        return true;
      } else {
        throw new BloodBankProfileException("Error while updating the blood bank profile details. ");
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}