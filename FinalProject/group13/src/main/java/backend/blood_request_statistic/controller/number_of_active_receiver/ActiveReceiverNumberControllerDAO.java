package backend.blood_request_statistic.controller.number_of_active_receiver;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code FindHighestBloodTypeRequestedControllerDAO} provides a contract for the
 * finding view active receiver number from all blood receiver requests.
 */
public interface ActiveReceiverNumberControllerDAO {

  /**
   * Performs view active receiver number from all blood receiver requests.
   *
   * @return count of all blood receiver requests.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int viewActiveReceiverNumber() throws DatabaseConnectionException;
}