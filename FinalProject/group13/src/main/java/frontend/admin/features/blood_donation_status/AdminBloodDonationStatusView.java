package frontend.admin.features.blood_donation_status;

import backend.admin_blood_donation_request.controller.update_blood_donation_status.BloodDonationFulfillRequest;
import backend.admin_blood_donation_request.controller.update_blood_donation_status.BloodDonationFulfillRequestDAO;
import backend.admin_blood_donation_request.controller.view_blood_donation_status.ViewBloodDonationStatusController;
import backend.admin_blood_donation_request.controller.view_blood_donation_status.ViewBloodDonationStatusControllerDAO;
import backend.admin_blood_donation_request.database.update_blood_donation_status.UpdateBloodDonationStatusQueryBuilder;
import backend.admin_blood_donation_request.database.update_blood_donation_status.UpdateBloodDonationStatusQueryBuilderDAO;
import backend.admin_blood_donation_request.database.view_blood_donation_status.ViewBloodDonationStatusQueryBuilder;
import backend.admin_blood_donation_request.database.view_blood_donation_status.ViewBloodDonationStatusQueryBuilderDAO;
import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import backend.admin_blood_donation_request.model.BloodDonationRequestStatus;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.List;
import java.util.Scanner;

public class AdminBloodDonationStatusView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final ViewBloodDonationStatusQueryBuilderDAO viewBloodDonationStatusQueryBuilder;
  private final UpdateBloodDonationStatusQueryBuilderDAO updateBloodDonationStatusQueryBuilder;

  public AdminBloodDonationStatusView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.viewBloodDonationStatusQueryBuilder =
        ViewBloodDonationStatusQueryBuilder.getInstance();
    this.updateBloodDonationStatusQueryBuilder =
        UpdateBloodDonationStatusQueryBuilder.getInstance();
  }

  private void viewBloodDonationStatus() {
    printer.printScreenTitle("Blood Donor Requests");

    final ViewBloodDonationStatusControllerDAO viewBloodDonationStatusController =
        new ViewBloodDonationStatusController(databaseConnection, viewBloodDonationStatusQueryBuilder);
    try {
      final List<BloodDonationRequestStatus> bloodDonationRequestStatusList =
          viewBloodDonationStatusController.viewBloodDonationStatus();
      for (final BloodDonationRequestStatus bloodDonationRequestStatus : bloodDonationRequestStatusList) {
        printer.printContent("Request Id: " + bloodDonationRequestStatus.getRequestId());
        printer.printContent("User Id: " + bloodDonationRequestStatus.getUserId());
        printer.printContent("Request Date: " + bloodDonationRequestStatus.getRequestDate());
        printer.printContent("Status Change Date: " + bloodDonationRequestStatus.getStatusChangeDate());
        printer.printContent("Request Status: " + bloodDonationRequestStatus.getStatus());
      }
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void updateBloodDonationStatus() {
    printer.printScreenTitle("Update Blood Donor Request Status");

    printer.printContent("Select a Request Id");
    final int requestId = scanner.nextInt();
    scanner.nextLine();

    printer.printContent("Enter the new status (fulfilled | rejected)");
    final String status = scanner.nextLine();

    final BloodDonationFulfillRequestDAO bloodDonationFulfillRequest =
        new BloodDonationFulfillRequest(databaseConnection, updateBloodDonationStatusQueryBuilder);
    try {
      final boolean isBloodDonationStatusUpdated =
          bloodDonationFulfillRequest.showBloodDonationFulfillRequest(requestId, status);
      if (isBloodDonationStatusUpdated) {
        printer.printContent("Blood donation request " + requestId + " updated successfully to " + status);
      } else {
        printer.printContent("Failed to update the blood donation status");
      }
    } catch (BloodDonationRequestStatusException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  public final void bloodDonationStatus() {
    printer.printScreenTitle("Fulfill | Reject blood donor requests");

    while (true) {
      printer.printContent("1. View all donor requests.");
      printer.printContent("2. Update donor request.");
      printer.printContent("3. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          viewBloodDonationStatus();
          break;
        case "2":
          updateBloodDonationStatus();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}
