package backend.active_blood_donor_request.controller.view_active_blood_donor_request;

import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import backend.active_blood_donor_request.model.ActiveBloodDonorRequest;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewActiveBloodDonorRequestControllerDAO} provides a contract for
 * viewing all active blood donation requests.
 */
public interface ViewActiveBloodDonorRequestControllerDAO {

  /**
   * @param bloodGroup of this user.
   * @param location   of this user.
   *
   * @return List<ActiveBloodDonorRequest> of active blood donation requests.
   *
   * @throws ActiveBloodDonorRequestException if any error occurs while
   *                                          fetching all active blood
   *                                          donation requests.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  List<ActiveBloodDonorRequest> viewActiveDonorRequest(final String bloodGroup,
                                                       final String location)
      throws ActiveBloodDonorRequestException,
      DatabaseConnectionException;
}
