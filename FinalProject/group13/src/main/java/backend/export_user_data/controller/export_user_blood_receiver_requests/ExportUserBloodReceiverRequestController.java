package backend.export_user_data.controller.export_user_blood_receiver_requests;

import backend.blood_receiver_request.controller.view_receiver_request.ViewAllReceiverRequestController;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;
import frontend.session.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * {@code ExportUserBloodReceiverRequestController} implements the
 * {@code ExportUserBloodReceiverRequestControllerDAO} to provide a concrete
 * implementation for exporting user blood receiver data.
 */
public class ExportUserBloodReceiverRequestController
    implements ExportUserBloodReceiverRequestControllerDAO {

  // View previous blood receiver requests instance.
  private final ViewAllReceiverRequestController
      viewAllReceiverRequest;

  /**
   * Constructs this {@code ExportUserBloodReceiverRequestController} instance.
   *
   * @param viewAllReceiverRequest view previous blood receiver
   *                               requests instance.
   */
  public ExportUserBloodReceiverRequestController(
      final ViewAllReceiverRequestController
          viewAllReceiverRequest) {
    this.viewAllReceiverRequest =
        viewAllReceiverRequest;
  }

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
  @Override
  public boolean exportUserBloodReceiverRequest(String fileName) throws DatabaseConnectionException, BloodReceiverRequestException, ExportUserDataException {
    final File file = new File(fileName);
    final List<BloodReceiverRequest> bloodReceiverRequestList = viewAllReceiverRequest.viewBloodReceiverRequest(Session.USER.getUserId());

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========User Blood Receiver Requests========\n\n");

      fileWriter.write("Previous blood receive requests:\n");
      for (BloodReceiverRequest bloodReceiverRequest : bloodReceiverRequestList) {
        fileWriter.write("User ID: " + bloodReceiverRequest.getUserId() + "\n");
        fileWriter.write("Blood Group: " + bloodReceiverRequest.getBloodGroup() + "\n");
        fileWriter.write("Quantity: " + bloodReceiverRequest.getQuantity() + "\n");
        fileWriter.write("Request Date: " + bloodReceiverRequest.getDateOfRequest() + "\n");
        fileWriter.write("Status Change Date: " + bloodReceiverRequest.getStatusChangeDate() + "\n");
        fileWriter.write("Status: " + bloodReceiverRequest.getStatus() + "\n");
      }

      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportUserDataException(e.getMessage());
    }
  }
}
