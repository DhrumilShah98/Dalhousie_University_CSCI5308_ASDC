package backend.blood_donation_request.controller.view_previous_blood_donation_request;

import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewPreviousBloodDonationRequestControllerDAO} provides a contract for
 * viewing all previous blood donation requests of this user.
 */
public interface ViewPreviousBloodDonationRequestControllerDAO {

  /**
   * @param userId unique id of this user.
   *
   * @return List<BloodDonationRequest> of all the previous blood donations
   * of this user.
   *
   * @throws BloodDonationRequestException if any error occurs while viewing
   *                                       all previous blood donations of
   *                                       this user.
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   */
  List<BloodDonationRequest> viewBloodDonationRequest(final int userId)
      throws BloodDonationRequestException,
      DatabaseConnectionException;
}
