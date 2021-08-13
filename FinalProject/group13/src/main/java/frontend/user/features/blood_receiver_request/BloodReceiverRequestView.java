package frontend.user.features.blood_receiver_request;

import backend.blood_receiver_request.controller.create_receiver_request.CreateBloodRequestController;
import backend.blood_receiver_request.controller.create_receiver_request.CreateBloodRequestControllerDAO;
import backend.blood_receiver_request.controller.request_fulfilment.ReceiverRequestFulfillmentController;
import backend.blood_receiver_request.controller.view_receiver_request.ViewAllReceiverRequestController;
import backend.blood_receiver_request.controller.view_receiver_request.ViewAllReceiverRequestControllerDAO;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilder;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilderDAO;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BloodReceiverRequestView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodReceiverRequestQueryBuilderDAO bloodReceiverRequestQueryBuilder;

  public BloodReceiverRequestView(final BloodBookPrinter printer, final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.bloodReceiverRequestQueryBuilder =
        BloodReceiverRequestQueryBuilder.getInstance();
  }

  private void createNewBloodReceiverRequest() {
    printer.printScreenTitle("New Blood Receiver Request");

    printer.printContent("Enter the Blood Group Needed");
    final String bloodGroup = scanner.nextLine();

    printer.printContent("Enter Blood Quantity Needed (Ex. 5)");
    final String quantity = scanner.nextLine();

    final BloodReceiverRequest bloodReceiverRequest =
        new BloodReceiverRequest(Session.USER.getUserId(),
            bloodGroup,
            quantity,
            String.valueOf(LocalDate.now()),
            "active",
            String.valueOf(LocalDate.now())
        );

    final CreateBloodRequestControllerDAO createBloodRequest =
        new CreateBloodRequestController(databaseConnection, bloodReceiverRequestQueryBuilder);


    try {
      final boolean isNewBloodReceiverRequestCreated =
          createBloodRequest.createRequest(Session.USER, bloodReceiverRequest);

      if (isNewBloodReceiverRequestCreated) {
        printer.printContent("New blood receiver request created successfully.");
      } else {
        printer.printContent("Failed to created a new blood receiver request.");
      }
    } catch (DatabaseConnectionException | BloodReceiverRequestException e) {
      printer.printContent(e.toString());
    }
  }

  private void viewPreviousBloodReceiveRequest() {
    final ViewAllReceiverRequestControllerDAO viewAllReceiverRequest =
        new ViewAllReceiverRequestController(databaseConnection, bloodReceiverRequestQueryBuilder);
    try {
      final List<BloodReceiverRequest> bloodReceiverRequestList =
          viewAllReceiverRequest.viewBloodReceiverRequest(Session.USER.getUserId());
      printer.printScreenTitle("View All Blood Receiver Requests");
      for (final BloodReceiverRequest bloodReceiverRequest : bloodReceiverRequestList) {
        printer.printContent("Request Id: " + bloodReceiverRequest.getRequestId());
        printer.printContent("Blood Group: " + bloodReceiverRequest.getBloodGroup());
        printer.printContent("Quantity: " + bloodReceiverRequest.getQuantity());
        printer.printContent("Date of Request: " + bloodReceiverRequest.getDateOfRequest());
        printer.printContent("Status: " + bloodReceiverRequest.getStatus());
        printer.printContent("Status Change Date: " + bloodReceiverRequest.getStatusChangeDate());
      }
    } catch (DatabaseConnectionException | BloodReceiverRequestException e) {
      printer.printContent(e.toString());
    }
  }

  private void requestForBloodReceiverRequestFulfillment() {
    printer.printScreenTitle("Request for Blood Receive Request Fulfillment");

    final ReceiverRequestFulfillmentController receiverRequestFulfillment =
        new ReceiverRequestFulfillmentController(databaseConnection, bloodReceiverRequestQueryBuilder);
    try {
      final boolean isBloodReceiverRequestStatusUpdated =
          receiverRequestFulfillment.requestFulfilment(Session.USER.getUserId());
      if (isBloodReceiverRequestStatusUpdated) {
        printer.printContent("Blood received fulfillment request sent successfully.\n" +
            "You will get a call for verification.");
      } else {
        printer.printContent("Error in blood receive request fulfillment.");
      }
    } catch (DatabaseConnectionException | BloodReceiverRequestException e) {
      printer.printContent(e.toString());
    }
  }

  public final void performBloodReceiveRequest() {
    printer.printScreenTitle("Blood Receive Requests");
    while (true) {
      printer.printContent("1. Create a new blood receive request.");
      printer.printContent("2. View all previous blood receive requests.");
      printer.printContent("3. Request blood receive request fulfillment.");
      printer.printContent("4. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          createNewBloodReceiverRequest();
          break;
        case "2":
          viewPreviousBloodReceiveRequest();
          break;
        case "3":
          requestForBloodReceiverRequestFulfillment();
          break;
        case "4":
          return;
        default:
          break;
      }
    }
  }
}
