package backend.blood_receiver_request.controller.view_receiver_request;

import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewAllReceiverRequestDAO} provides a contract for the
 * viewing all blood receiver request.
 */
public interface ViewAllReceiverRequestControllerDAO {

  /**
   * Performs view all blood receiver requests.
   *
   * @param userId user id of the blood receiver request.
   *
   * @return all blood receiver request made by the user.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request fulfillment.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  List<BloodReceiverRequest> viewBloodReceiverRequest(int userId) throws BloodReceiverRequestException, DatabaseConnectionException;
}