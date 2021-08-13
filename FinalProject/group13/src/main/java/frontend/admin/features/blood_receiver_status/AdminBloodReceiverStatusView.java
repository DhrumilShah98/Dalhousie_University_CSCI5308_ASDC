package frontend.admin.features.blood_receiver_status;



import backend.admin_blood_receiver_request.controller.view_blood_receiver_status.ViewBloodReceiverStatusControllerDAO;
import backend.admin_blood_receiver_request.controller.update_blood_receiver_status.BloodReceiverFulfillRequestDAO;
import backend.admin_blood_receiver_request.exception.BloodReceiverRequestStatusException;
import backend.admin_blood_receiver_request.controller.update_blood_receiver_status.BloodReceiverFulfillRequest;
import backend.admin_blood_receiver_request.controller.view_blood_receiver_status.ViewBloodReceiverStatusController;
import backend.admin_blood_receiver_request.database.update_blood_receiver_status.BloodReceiverRequestStatusQueryBuilder;
import backend.admin_blood_receiver_request.database.update_blood_receiver_status.BloodReceiverRequestStatusQueryBuilderDAO;
import backend.admin_blood_receiver_request.database.view_blood_receiver_status.ViewBloodReceiverStatusQueryBuilder;
import backend.admin_blood_receiver_request.database.view_blood_receiver_status.ViewBloodReceiverStatusQueryBuilderDAO;
import backend.admin_blood_receiver_request.model.BloodReceiverRequestStatus;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.List;
import java.util.Scanner;

public class AdminBloodReceiverStatusView {
    private final BloodBookPrinter printer;
    private final Scanner scanner;
    private final DatabaseConnectionDAO databaseConnection;
    private final ViewBloodReceiverStatusQueryBuilderDAO viewBloodReceiverStatusQueryBuilder;
    private final BloodReceiverRequestStatusQueryBuilderDAO bloodReceiverRequestStatusQueryBuilder;

    public AdminBloodReceiverStatusView(BloodBookPrinter printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
        this.viewBloodReceiverStatusQueryBuilder = ViewBloodReceiverStatusQueryBuilder.getInstance();
        this.bloodReceiverRequestStatusQueryBuilder = BloodReceiverRequestStatusQueryBuilder.getInstance();
        this.databaseConnection = DatabaseConnection.getInstance();

    }

    private void viewReceiverStatus() {
        printer.printScreenTitle("Blood Donor Requests");

        final ViewBloodReceiverStatusControllerDAO viewBloodReceiverStatusController =

                new ViewBloodReceiverStatusController(databaseConnection, viewBloodReceiverStatusQueryBuilder);

        try {
            final List<BloodReceiverRequestStatus> bloodReceiverRequestStatusList =
                    viewBloodReceiverStatusController.viewBloodReceiverStatus();
            for (final BloodReceiverRequestStatus bloodReceiverRequestStatus : bloodReceiverRequestStatusList) {
                printer.printContent("Request Id: " + bloodReceiverRequestStatus.getRequestId());
              //  printer.printContent("User Id: " + bloodReceiverRequestStatus.getUserId());
                printer.printContent("Request Date: " + bloodReceiverRequestStatus.getRequestDate());
                printer.printContent("Status Change Date: " + bloodReceiverRequestStatus.getStatusChangeDate());
                printer.printContent("Request Status: " + bloodReceiverRequestStatus.getStatus());
            }
        } catch ( ReceiverStatisticsException | DatabaseConnectionException e ) {
            printer.printContent(e.toString());
        }
    }

    private void updateBloodReceiverStatus() {
        printer.printScreenTitle("Update Blood Donor Request Status");

        printer.printContent("Select a Request Id");
        final int requestId = scanner.nextInt();
        scanner.nextLine();

        printer.printContent("Enter the new status (fulfilled | rejected)");
        final String status = scanner.nextLine();


        final BloodReceiverFulfillRequestDAO bloodReceiverFulfillRequest =
                new BloodReceiverFulfillRequest(databaseConnection, bloodReceiverRequestStatusQueryBuilder);
        try {
            final boolean isBloodReceiverStatusUpdated =
                    bloodReceiverFulfillRequest.showBloodReceiverFulfillRequest(requestId, status);
            if (isBloodReceiverStatusUpdated) {
                printer.printContent("Blood receiver request " + requestId + " updated successfully to " + status);
            } else {
                printer.printContent("Failed to update the blood receiver status");
            }

        } catch ( BloodReceiverRequestStatusException | DatabaseConnectionException e ) {
            printer.printContent(e.toString());
        }
    }

    public final void bloodReceiverStatus() {
        printer.printScreenTitle("Fulfill | Reject blood donor requests");

        while (true) {
            printer.printContent("1. View all receiver requests.");
            printer.printContent("2. Update receiver request.");
            printer.printContent("3. Exit.");
            printer.printContent("Select an option:");
            final String input = scanner.nextLine();
            switch (input) {
                case "1":
                    viewReceiverStatus();
                    break;
                case "2":
                    updateBloodReceiverStatus();
                    break;
                case "3":
                    return;
                default:
                    break;
            }
        }
    }
}

