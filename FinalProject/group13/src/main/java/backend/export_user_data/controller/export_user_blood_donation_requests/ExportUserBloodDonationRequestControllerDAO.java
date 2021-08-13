package backend.export_user_data.controller.export_user_blood_donation_requests;

import backend.blood_donation_request.exception.BloodDonationRequestException;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;

/**
 * {@code ExportUserBloodDonationRequestControllerDAO} provides a contract for
 * exporting user donation request data.
 */
public interface ExportUserBloodDonationRequestControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   * @throws BloodDonationRequestException if any error occurs while fetching
   *                                       blood donation requests.
   * @throws ExportUserDataException       if any error occurs while exporting
   *                                       user data.
   */
  boolean exportUserBloodDonationRequest(final String fileName)
      throws DatabaseConnectionException,
      BloodDonationRequestException,
      ExportUserDataException;
}
