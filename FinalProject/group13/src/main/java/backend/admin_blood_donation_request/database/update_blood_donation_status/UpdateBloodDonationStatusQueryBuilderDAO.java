package backend.admin_blood_donation_request.database.update_blood_donation_status;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code UpdateBloodDonationStatusQueryBuilderDAO} provides a contract for the
 *  update of blood donors status.
 */
public interface UpdateBloodDonationStatusQueryBuilderDAO {

  /**
   * Gets the query to fulfill the status of blood donor.
   *
   * @param requestID request id to fulfill status of blood donor.
   *
   * @return String query to fulfill status of blood donor.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  String fulfilledBloodDonationRequestStatus(int requestID) throws BloodDonationStatisticsException, DatabaseConnectionException;

  /**
   * Gets the query to update the status of blood donor.
   *
   * @param requestID request id to reject status of blood donor.
   *
   * @return String query to reject status of blood donor.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  String rejectedBloodDonationRequestStatus(int requestID) throws BloodDonationStatisticsException, DatabaseConnectionException;
}
