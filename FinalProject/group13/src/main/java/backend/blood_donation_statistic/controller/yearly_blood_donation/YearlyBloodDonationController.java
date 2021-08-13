package backend.blood_donation_statistic.controller.yearly_blood_donation;

import backend.blood_donation_statistic.database.BloodDonationStatisticsDatabaseConstant;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilderDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

/**
 * {@code YearlyBloodDonationController} implements the
 * {@code YearlyBloodDonationControllerDAO} to provide a concrete
 * implementation for calculating number of yearly blood donations.
 */
public class YearlyBloodDonationController
    implements YearlyBloodDonationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood donation statistics query builder instance.
  private final BloodDonationStatisticsQueryBuilderDAO
      bloodDonationStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code AverageBloodDonorAgeController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodDonationStatisticsQueryBuilderDAO blood donation statistics
   *                                               query builder instance.
   */
  public YearlyBloodDonationController(
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
   * @return long of the number of yearly blood donations.
   *
   * @throws SQLException if any error occurs while running the query.
   */
  private long yearlyBloodDonationsHelper(
      final ResultSet allBloodDonorResultSet)
      throws SQLException {
    int yearlyBloodDonationsCount = 0;
    final String statusFulfilled =
        BloodDonationStatisticsDatabaseConstant.STATUS_FULFILLED;
    while (allBloodDonorResultSet.next()) {
      final String status =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.STATUS_COLUMN);
      final String fulfillmentDate =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.STATUS_CHANGE_DATE_COLUMN);
      final long period = getPeriod(fulfillmentDate);
      if (status.equals(statusFulfilled) && period <= 12) {
        yearlyBloodDonationsCount++;
      }
    }
    return yearlyBloodDonationsCount;
  }

  /**
   * @param fulfillmentDate the date of blood donation request fulfillment.
   *
   * @return long period between current date and fulfillment date.
   */
  private long getPeriod(final String fulfillmentDate) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate fulfillDate = LocalDate.parse(fulfillmentDate);
    Period period = Period.between(fulfillDate, currentDate);
    return Math.abs(period.toTotalMonths());
  }

  /**
   * @return long year of the number of yearly blood donations.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public long yearlyBloodDonations()
      throws DatabaseConnectionException {
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet allBloodDonorResultSet =
             statement.executeQuery(bloodDonationStatisticsQueryBuilderDAO.selectBloodDonationStatisticsQuery())) {
      return yearlyBloodDonationsHelper(allBloodDonorResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
