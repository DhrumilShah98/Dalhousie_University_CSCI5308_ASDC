package backend.blood_request_statistic.controller.highest_blood_type;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code FindHighestBloodTypeRequestedControllerDAO} provides a contract for the
 * finding highest requested blood type of all blood receiver requests.
 */
public interface FindHighestBloodTypeRequestedControllerDAO {

  /**
   * Finds highest requested blood type of all blood receiver requests.
   *
   * @return highest requested blood type.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   * @throws ReceiverStatisticsException if any error occurs while
   *                                     performing query in the database.
   */
  List<String> findHighestRequestedBloodGroup() throws
      DatabaseConnectionException, ReceiverStatisticsException;
}