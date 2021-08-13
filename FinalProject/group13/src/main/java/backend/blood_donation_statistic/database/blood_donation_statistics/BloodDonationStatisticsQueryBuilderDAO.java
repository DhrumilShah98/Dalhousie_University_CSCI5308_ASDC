package backend.blood_donation_statistic.database.blood_donation_statistics;

/**
 * {@code BloodDonationStatisticsQueryBuilderDAO} provides a contract
 * for retrieving blood donation request and user tables.
 */
public interface BloodDonationStatisticsQueryBuilderDAO {

  /**
   * Gets the query to select blood donation statistic.
   *
   * @return string query to select blood donation statistic.
   */
  String selectBloodDonationStatisticsQuery();
}
