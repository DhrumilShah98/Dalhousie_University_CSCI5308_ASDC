package backend.demographic_information.controller;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.demographic_information.database.DemographicInformationQueryBuilderDAO;
import backend.demographic_information.exception.DemographicInformationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * {@code DemographicInformationController} implements the
 * {@code DemographicInformationControllerDAO} to provide a concrete
 * implementation for the fetching information of active user and bloodbank
 * number from particular province.
 */
public final class DemographicInformationController implements DemographicInformationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Demographic information query builder instance.
  private final DemographicInformationQueryBuilderDAO demographicInformationQueryBuilderDAO;

  /**
   * Constructs this {@code DemographicInformationController} instance.
   *
   * @param databaseConnectionDAO                 database connection
   *                                              instance.
   * @param demographicInformationQueryBuilderDAO demographic
   *                                              information
   *                                              query builder
   *                                              instance.
   */
  public DemographicInformationController(DatabaseConnectionDAO databaseConnectionDAO, DemographicInformationQueryBuilderDAO demographicInformationQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.demographicInformationQueryBuilderDAO = demographicInformationQueryBuilderDAO;
  }

  /**
   * fetching information of active users and bloodbanks number from
   * particular province.
   *
   * @param provinceName contact number of this blood bank.
   *
   * @throws DemographicInformationException if any error occurs while
   *                                         fetching information.
   * @throws DatabaseConnectionException     if any error occurs while
   *                                         connecting to the database.
   */
  @Override
  public HashMap<String, Integer> retrieveDemographicInformation(String provinceName) throws DemographicInformationException, DatabaseConnectionException {

    if (provinceName == null || provinceName.isEmpty()) {
      throw new DemographicInformationException("Please provide valid province name");
    }

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {

      // fetching result in result set of active user in province
      final ResultSet activeUserInProvinceResultSet =
          statement.executeQuery(demographicInformationQueryBuilderDAO.fetchActiveUserInProvince(provinceName));
      int activeUserInProvince = 0;
      if (activeUserInProvinceResultSet.next()) {
        activeUserInProvince = activeUserInProvinceResultSet.getInt(1);
      }

      // fetching result in result set of active blood bank in province
      final ResultSet activeBloodBankInProvinceResultSet =
          statement.executeQuery(demographicInformationQueryBuilderDAO.fetchActiveBloodBankInProvince(provinceName));
      int activeBloodBankInProvince = 0;
      if (activeBloodBankInProvinceResultSet.next()) {
        activeBloodBankInProvince =
            activeBloodBankInProvinceResultSet.getInt(1);
      }

      // adding value in hashmap for active user and blood bank
      HashMap<String, Integer> demographicInformation = new HashMap<>();
      demographicInformation.put("Active Users in " + provinceName, activeUserInProvince);
      demographicInformation.put("Active BloodBank in " + provinceName, activeBloodBankInProvince);

      activeUserInProvinceResultSet.close();
      activeBloodBankInProvinceResultSet.close();

      return demographicInformation;
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
