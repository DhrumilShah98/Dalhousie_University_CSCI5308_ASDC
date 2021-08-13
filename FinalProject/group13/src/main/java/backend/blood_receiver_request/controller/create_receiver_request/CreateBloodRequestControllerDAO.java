package backend.blood_receiver_request.controller.create_receiver_request;

import backend.authentication.user.model.User;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import database.DatabaseConnectionException;

/**
 * {@code CreateBloodRequestControllerDAO} provides a contract for the
 * blood receiver requests.
 */
public interface CreateBloodRequestControllerDAO {

  /**
   * Performs blood receiver request.
   *
   * @param user                 user of the blood receiver request.
   * @param bloodReceiverRequest blood receiver request tp insert in the table.
   *
   * @return {@code true} if blood receiver requested successfully otherwise
   * {@code false}.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  boolean createRequest(User user, BloodReceiverRequest bloodReceiverRequest)
      throws BloodReceiverRequestException, DatabaseConnectionException;
}
