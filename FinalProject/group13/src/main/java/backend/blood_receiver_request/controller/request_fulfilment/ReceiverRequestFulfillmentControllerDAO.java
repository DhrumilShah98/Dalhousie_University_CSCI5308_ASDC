package backend.blood_receiver_request.controller.request_fulfilment;

import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import database.DatabaseConnectionException;

/**
 * {@code ReceiverRequestFulfillmentControllerDAO} provides a contract for the
 * blood receiver request fulfilled.
 */
public interface ReceiverRequestFulfillmentControllerDAO {

  /**
   * Performs blood receiver request fulfillment.
   *
   * @param userId user id of the blood receiver request.
   *
   * @return {@code true} if blood receiver request fulfilled successfully
   * otherwise {@code false}.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request fulfillment.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  boolean requestFulfilment(int userId) throws BloodReceiverRequestException, DatabaseConnectionException;
}
