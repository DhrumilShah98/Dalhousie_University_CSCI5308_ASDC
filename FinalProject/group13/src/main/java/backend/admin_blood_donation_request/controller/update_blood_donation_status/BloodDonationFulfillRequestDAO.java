package backend.admin_blood_donation_request.controller.update_blood_donation_status;

import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import database.DatabaseConnectionException;

/**
 * {@code BloodDonationFulfillRequestDAO} provides a contract for the
 * update of blood donors status.
 */
public interface BloodDonationFulfillRequestDAO {

  /**
   * performs update status of blood donor.
   *
   * @param requestID request id of blood donor.
   *
   * @param status status of blood donor.
   *
   * @return status of blood donor's updated instance.
   *
   * @throws BloodDonationRequestStatusException when error occurs while
   * updating the status of blood donor.
   *
   * @throws DatabaseConnectionException  when error occurs while connecting
   * to the database.
   */
  boolean showBloodDonationFulfillRequest(int requestID, String status) throws
      BloodDonationRequestStatusException, DatabaseConnectionException;

}
