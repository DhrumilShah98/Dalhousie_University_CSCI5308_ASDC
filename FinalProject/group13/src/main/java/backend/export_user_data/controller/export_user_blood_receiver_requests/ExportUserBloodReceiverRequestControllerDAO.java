package backend.export_user_data.controller.export_user_blood_receiver_requests;

import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;

/**
 * {@code ExportUserBloodReceiverRequestControllerDAO} provides a contract for
 * exporting user receiver request data.
 */
public interface ExportUserBloodReceiverRequestControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   * @throws BloodReceiverRequestException if any error occurs while fetching
   *                                       blood receiver requests.
   * @throws ExportUserDataException       if any error occurs while exporting
   *                                       user data.
   */
  boolean exportUserBloodReceiverRequest(final String fileName)
      throws DatabaseConnectionException,
      BloodReceiverRequestException,
      ExportUserDataException;
}
