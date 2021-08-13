package backend.blood_donation_request.controller.update_blood_donation_request_status;

import backend.blood_donation_request.exception.BloodDonationRequestException;
import database.DatabaseConnectionException;

/**
 * {@code UpdateBloodDonationRequestControllerDAO} provides a contract for
 * updating a blood donation request's status.
 */
public interface UpdateBloodDonationRequestControllerDAO {

  /**
   * @param userId unique id of this user.
   *
   * @return boolean true/false if status is updated or not.
   *
   * @throws BloodDonationRequestException if any error occurs while updating
   *                                       blood donation request status.
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   */
  boolean requestFulfilment(int userId) throws BloodDonationRequestException, DatabaseConnectionException;
}
