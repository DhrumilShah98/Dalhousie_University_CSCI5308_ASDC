package backend.blood_request_statistic.controller.average_age;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code FindAverageReceiverRequestAgeControllerDAO} provides a contract for the
 * finding average receiver age from all blood receiver request.
 */
public interface FindAverageReceiverRequestAgeControllerDAO {

  /**
   * Performs view average age of all blood receiver requests.
   *
   * @return all blood receiver request made by the user.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int viewReceiverAverageAge() throws DatabaseConnectionException, ReceiverStatisticsException;
}