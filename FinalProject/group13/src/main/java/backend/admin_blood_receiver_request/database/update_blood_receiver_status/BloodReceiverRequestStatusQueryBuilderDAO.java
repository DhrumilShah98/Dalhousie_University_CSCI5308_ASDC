package backend.admin_blood_receiver_request.database.update_blood_receiver_status;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code BloodReceiverRequestStatusQueryBuilderDAO} provides a contract for the
 *  update of blood receiver status.
 */
public interface BloodReceiverRequestStatusQueryBuilderDAO {

  /**
   * Gets the query to fulfill the status of blood receiver.
   *
   * @param requestID request id to fulfill status of blood receiver.
   *
   * @return String query to fulfill status of blood receiver.
   *
   * @throws ReceiverStatisticsException when error occurs while
   * fetching blood receiver statistics.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  String fulfilledBloodReceiverRequestStatus(int requestID) throws ReceiverStatisticsException, DatabaseConnectionException;

  /**
   * Gets the query to reject the status of blood receiver.
   *
   * @param requestID request id to reject status of blood receiver.
   *
   * @return String query to reject status of blood receiver.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  String rejectedBloodReceiverRequestStatus(int requestID) throws ReceiverStatisticsException, DatabaseConnectionException;
}
