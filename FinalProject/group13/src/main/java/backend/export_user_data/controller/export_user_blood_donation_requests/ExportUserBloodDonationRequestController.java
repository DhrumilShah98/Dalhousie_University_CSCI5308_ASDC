package backend.export_user_data.controller.export_user_blood_donation_requests;

import backend.blood_donation_request.controller.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestController;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;
import frontend.session.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * {@code ExportUserBloodDonationRequestController} implements the
 * {@code ExportUserBloodDonationRequestControllerDAO} to provide a concrete
 * implementation for exporting user blood donation data.
 */
public class ExportUserBloodDonationRequestController
    implements ExportUserBloodDonationRequestControllerDAO {

  // View previous blood donation requests instance.
  private final ViewPreviousBloodDonationRequestController
      viewPreviousBloodDonationRequest;

  /**
   * Constructs this {@code ExportUserBloodDonationRequestController} instance.
   *
   * @param viewPreviousBloodDonationRequest view previous blood donation
   *                                         requests instance.
   */
  public ExportUserBloodDonationRequestController(
      final ViewPreviousBloodDonationRequestController
          viewPreviousBloodDonationRequest) {
    this.viewPreviousBloodDonationRequest =
        viewPreviousBloodDonationRequest;
  }

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
  @Override
  public boolean exportUserBloodDonationRequest(final String fileName)
      throws DatabaseConnectionException,
      BloodDonationRequestException,
      ExportUserDataException {
    final File file = new File(fileName);
    final List<BloodDonationRequest> bloodDonationRequests =
        viewPreviousBloodDonationRequest.viewBloodDonationRequest(Session.USER.getUserId());

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========User Blood Donation Requests========\n\n");

      fileWriter.write("Previous blood donation requests:\n");
      for (BloodDonationRequest bloodDonationRequest : bloodDonationRequests) {
        fileWriter.write("Request Id: " + bloodDonationRequest.getRequestId() + "\n");
        fileWriter.write("UserId: " + bloodDonationRequest.getUserId() + "\n");
        fileWriter.write("Request Date: " + bloodDonationRequest.getRequestDate() + "\n");
        fileWriter.write("Status Change Date: " + bloodDonationRequest.getStatusChangeDate() + "\n");
        fileWriter.write("Status: " + bloodDonationRequest.getStatus() + "\n");
        fileWriter.write("Certificate Id: " + bloodDonationRequest.getCertificateId() + "\n");
      }
      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportUserDataException(e.getMessage());
    }
  }
}
