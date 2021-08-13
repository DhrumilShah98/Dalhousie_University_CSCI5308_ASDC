package backend.blood_bank_view_all.controller;

import backend.blood_bank_view_all.database.BloodBankViewDatabaseConstant;
import backend.blood_bank_view_all.database.BloodBankViewQueryBuilderDAO;
import backend.blood_bank_view_all.model.BloodBankView;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BloodBankViewController} implements the
 * {@code BloodBankViewControllerDAO} to provide a concrete
 * implementation for the blood bank registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankViewController
    implements BloodBankViewControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank view query builder instance.
  private final BloodBankViewQueryBuilderDAO
      bloodBankViewQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankViewController} instance.
   *
   * @param databaseConnectionDAO        database connection instance.
   * @param bloodBankViewQueryBuilderDAO blood bank registration
   *                                     query builder instance.
   */
  public BloodBankViewController(final DatabaseConnectionDAO databaseConnectionDAO,
                                 final BloodBankViewQueryBuilderDAO bloodBankViewQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankViewQueryBuilderDAO =
        bloodBankViewQueryBuilderDAO;
  }

  /**
   * Prepares a list of all blood banks.
   *
   * @param bloodBankViewResultSet result set of all blood banks.
   *
   * @return list of all blood banks.
   *
   * @throws SQLException if any error occurs while accessing the result set.
   */
  public List<BloodBankView> prepareAllBloodBanks(final ResultSet bloodBankViewResultSet)
      throws SQLException {
    final List<BloodBankView> bloodBankViewList = new ArrayList<>();

    while (bloodBankViewResultSet.next()) {
      final int bloodBankId =
          bloodBankViewResultSet
              .getInt(BloodBankViewDatabaseConstant.BLOOD_BANK_ID_COLUMN);
      final String bloodBankName =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_NAME_COLUMN);
      final String bloodBankEmail =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_EMAIL_COLUMN);
      final String bloodBankContactNum =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_CONTACT_NUMBER_COLUMN);
      final String bloodBankAddressFirstLine =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN);
      final String bloodBankAddressStreet =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_STREET_COLUMN);
      final String bloodBankAddressCity =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_CITY_COLUMN);
      final String bloodBankAddressProvince =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN);
      final String bloodBankAddressZipCode =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN);
      final String bloodBankAddressCountry =
          bloodBankViewResultSet
              .getString(BloodBankViewDatabaseConstant.BLOOD_BANK_ADDRESS_COUNTRY_COLUMN);

      final BloodBankView bloodBankView = new BloodBankView(
          bloodBankId,
          bloodBankName,
          bloodBankEmail,
          bloodBankContactNum,
          bloodBankAddressFirstLine,
          bloodBankAddressStreet,
          bloodBankAddressCity,
          bloodBankAddressProvince,
          bloodBankAddressZipCode,
          bloodBankAddressCountry);

      bloodBankViewList.add(bloodBankView);
    }
    return bloodBankViewList;
  }

  /**
   * Gets all blood banks.
   *
   * @return list of all blood banks.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public List<BloodBankView> getAllBloodBanks()
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodBankViewStatement =
             dbConnection.createStatement();
         final ResultSet bloodBankViewResultSet =
             bloodBankViewStatement.executeQuery(bloodBankViewQueryBuilderDAO
                 .selectAllBloodBanksQuery())) {
      return prepareAllBloodBanks(bloodBankViewResultSet);
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}