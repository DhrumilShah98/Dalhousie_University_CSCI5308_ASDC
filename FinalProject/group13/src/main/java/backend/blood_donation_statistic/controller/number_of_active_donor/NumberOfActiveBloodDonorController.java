package backend.blood_donation_statistic.controller.number_of_active_donor;

import backend.blood_donation_statistic.database.BloodDonationStatisticsDatabaseConstant;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilderDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code NumberOfActiveBloodDonorController} implements the
 * {@code NumberOfActiveBloodDonorControllerDAO} to provide a concrete
 * implementation for calculating number of active blood donors.
 */
public class NumberOfActiveBloodDonorController
    implements NumberOfActiveBloodDonorControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood donation statistics query builder instance.
  private final BloodDonationStatisticsQueryBuilderDAO
      bloodDonationStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code NumberOfActiveBloodDonorController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodDonationStatisticsQueryBuilderDAO blood donation statistics
   *                                               query builder instance.
   */
  public NumberOfActiveBloodDonorController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodDonationStatisticsQueryBuilderDAO =
        bloodDonationStatisticsQueryBuilderDAO;
  }

  /**
   * @param allBloodDonorResultSet blood donation statistics result set.
   *
   * @return int number of active blood donors.
   *
   * @throws SQLException if any error occurs while running the query.
   */
  private int activeBloodDonorCountHelper(
      final ResultSet allBloodDonorResultSet)
      throws SQLException {
    int activeBloodDonorCount = 0;
    final String statusActive =
        BloodDonationStatisticsDatabaseConstant.STATUS_ACTIVE;
    while (allBloodDonorResultSet.next()) {
      final String status =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.STATUS_COLUMN);
      if (status.equals(statusActive)) {
        activeBloodDonorCount++;
      }
    }
    return activeBloodDonorCount;
  }

  /**
   * @return int number of active blood donors.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating the number of active
   *                                          blood donors.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public int countActiveBloodDonor()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException {
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet allBloodDonorResultSet =
             statement.executeQuery(bloodDonationStatisticsQueryBuilderDAO.selectBloodDonationStatisticsQuery())) {
      return activeBloodDonorCountHelper(allBloodDonorResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
