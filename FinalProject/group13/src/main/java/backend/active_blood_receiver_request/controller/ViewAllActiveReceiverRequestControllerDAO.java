package backend.active_blood_receiver_request.controller;

import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import backend.active_blood_receiver_request.exception.ActiveReceiverRequestException;
import backend.active_blood_receiver_request.model.ActiveReceiverRequest;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewAllActiveReceiverRequestControllerDAO} provides a contract for
 * viewing all active blood receiver requests.
 */
public interface ViewAllActiveReceiverRequestControllerDAO {

  /**
   * @param bloodGroup of this user.
   * @param location   of this user.
   *
   * @return List<ActiveReceiverRequest> of active blood donation requests.
   *
   * @throws ActiveReceiverRequestException if any error occurs while
   *                                          fetching all active blood
   *                                          receiver requests.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  List<ActiveReceiverRequest> viewActiveReceiverRequest(String bloodGroup, String location)
      throws ActiveReceiverRequestException, DatabaseConnectionException;
}