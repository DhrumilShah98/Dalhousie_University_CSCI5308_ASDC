package backend.blood_request_statistic.controller.classification_on_age;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

import java.util.Map;

/**
 * {@code CountReceiverRequestByAgeGroupControllerDAO} provides a contract for the
 * finding age group classification of all blood receiver requests.
 */
public interface CountReceiverRequestByAgeGroupControllerDAO {

  /**
   * Performs view age group classification of all blood receiver requests.
   *
   * @return age group classification of all blood receiver requests.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  Map<String, Float> viewReceiverAgeClassification() throws DatabaseConnectionException;
}
