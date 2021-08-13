package backend.admin_blood_donation_request.database.view_blood_donation_status;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code ViewBloodDonationStatusQueryBuilderDAO} provides a contract for the
 * viewing of blood donors status.
 */
public interface ViewBloodDonationStatusQueryBuilderDAO {

  /**
   * Gets the query to view the status of blood donor.
   *
   * @return String query to select status of blood donor.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   */
  String viewBloodDonationStatusQuery() throws DatabaseConnectionException, BloodDonationStatisticsException;
}
