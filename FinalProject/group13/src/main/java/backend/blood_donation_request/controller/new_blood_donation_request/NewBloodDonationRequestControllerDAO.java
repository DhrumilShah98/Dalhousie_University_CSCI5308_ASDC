package backend.blood_donation_request.controller.new_blood_donation_request;

import backend.authentication.user.model.User;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnectionException;

/**
 * {@code NewBloodDonationRequestControllerDAO} provides a contract for
 * creating a new blood donation request.
 */
public interface NewBloodDonationRequestControllerDAO {

  /**
   * @param user                 user object.
   * @param bloodDonationRequest bloodDonationRequest object.
   *
   * @return true/false returned for new blood donation request.
   *
   * @throws BloodDonationRequestException if any error occurs while creating
   *                                       new blood donation request.
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   */
  boolean createNewRequest(final User user,
                           final BloodDonationRequest bloodDonationRequest)
      throws BloodDonationRequestException, DatabaseConnectionException;
}
