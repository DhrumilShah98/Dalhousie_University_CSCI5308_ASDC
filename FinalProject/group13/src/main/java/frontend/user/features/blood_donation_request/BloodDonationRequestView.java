package frontend.user.features.blood_donation_request;

import backend.blood_donation_request.controller.new_blood_donation_request.NewBloodDonationRequestController;
import backend.blood_donation_request.controller.new_blood_donation_request.NewBloodDonationRequestControllerDAO;
import backend.blood_donation_request.controller.update_blood_donation_request_status.UpdateBloodDonationRequestController;
import backend.blood_donation_request.controller.update_blood_donation_request_status.UpdateBloodDonationRequestControllerDAO;
import backend.blood_donation_request.controller.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestController;
import backend.blood_donation_request.controller.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestControllerDAO;
import backend.blood_donation_request.database.new_blood_donation_request.NewBloodDonationRequestQueryBuilder;
import backend.blood_donation_request.database.new_blood_donation_request.NewBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.database.update_blood_donation_request_status.UpdateBloodDonationRequestStatusQueryBuilder;
import backend.blood_donation_request.database.update_blood_donation_request_status.UpdateBloodDonationRequestStatusQueryBuilderDAO;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilder;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public final class BloodDonationRequestView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final NewBloodDonationRequestQueryBuilderDAO newBloodDonationRequestQueryBuilder;
  private final ViewPreviousBloodDonationRequestQueryBuilderDAO viewPreviousBloodDonationRequestQueryBuilder;
  private final UpdateBloodDonationRequestStatusQueryBuilderDAO updateBloodDonationRequestStatusQueryBuilder;

  public BloodDonationRequestView(final BloodBookPrinter printer, final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.newBloodDonationRequestQueryBuilder =
        NewBloodDonationRequestQueryBuilder.getInstance();
    this.viewPreviousBloodDonationRequestQueryBuilder =
        ViewPreviousBloodDonationRequestQueryBuilder.getInstance();
    this.updateBloodDonationRequestStatusQueryBuilder =
        UpdateBloodDonationRequestStatusQueryBuilder.getInstance();
  }

  private void createNewBloodDonationRequest() {
    printer.printScreenTitle("New Blood Donation Request");

    printer.printContent("Do you have any symptoms from cold | flu | sore throat | cold sore | stomach bug | any other infection? (Yes | No)");
    final String question1 = scanner.nextLine();

    printer.printContent("Have you donated blood in the last 2 months? (Yes | No)");
    final String question2 = scanner.nextLine();

    printer.printContent("Have you done any body piercing | tattoo in the last 6 months? (Yes | No)");
    final String question3 = scanner.nextLine();

    printer.printContent("Are you going through any medications - Hormonal Medications? (Yes | No)");
    final String question4 = scanner.nextLine();

    printer.printContent("Have you gone through any dialysis treatment? (Yes | No)");
    final String question5 = scanner.nextLine();

    printer.printContent("Do you have BP? (Yes | No)");
    final String question6 = scanner.nextLine();

    printer.printContent("Do you have Diabetes? (Yes | No)");
    final String question7 = scanner.nextLine();

    printer.printContent("Do you have HIV? (Yes | No)");
    final String question8 = scanner.nextLine();

    if (question1.equalsIgnoreCase("Yes") ||
        question2.equalsIgnoreCase("Yes") ||
        question3.equalsIgnoreCase("Yes") ||
        question4.equalsIgnoreCase("Yes") ||
        question5.equalsIgnoreCase("Yes") ||
        question6.equalsIgnoreCase("Yes") ||
        question7.equalsIgnoreCase("Yes") ||
        question8.equalsIgnoreCase("Yes")) {
      printer.printContent("Cannot donate blood.");
    }

    final BloodDonationRequest bloodDonationRequest =
        new BloodDonationRequest(Session.USER.getUserId(),
            String.valueOf(LocalDate.now()),
            String.valueOf(LocalDate.now()),
            "active",
            "");

    final NewBloodDonationRequestControllerDAO newBloodDonationRequest =
        new NewBloodDonationRequestController(databaseConnection,
            newBloodDonationRequestQueryBuilder,
            viewPreviousBloodDonationRequestQueryBuilder);

    try {
      final boolean isNewBloodDonationRequestCreated =
          newBloodDonationRequest.createNewRequest(Session.USER, bloodDonationRequest);

      if (isNewBloodDonationRequestCreated) {
        printer.printContent("New blood donation request created successfully.");
      } else {
        printer.printContent("Failed to created a new blood donation request.");
      }
    } catch (DatabaseConnectionException | BloodDonationRequestException e) {
      printer.printContent(e.toString());
    }
  }

  private void viewPreviousBloodDonationRequest() {
    final ViewPreviousBloodDonationRequestControllerDAO viewPreviousBloodDonationRequest =
        new ViewPreviousBloodDonationRequestController(databaseConnection, viewPreviousBloodDonationRequestQueryBuilder);
    try {
      final List<BloodDonationRequest> bloodDonationRequestList =
          viewPreviousBloodDonationRequest.viewBloodDonationRequest(Session.USER.getUserId());
      printer.printScreenTitle("View All Blood Donation Requests");
      for (final BloodDonationRequest bloodDonationRequest : bloodDonationRequestList) {
        printer.printContent("Request Id: " + bloodDonationRequest.getRequestId());
        printer.printContent("Request Date: " + bloodDonationRequest.getRequestDate());
        printer.printContent("Status Change Date: " + bloodDonationRequest.getStatusChangeDate());
        printer.printContent("Request Status: " + bloodDonationRequest.getStatus());
        printer.printContent("Certificate Id: " + bloodDonationRequest.getCertificateId());
      }
    } catch (DatabaseConnectionException | BloodDonationRequestException e) {
      printer.printContent(e.toString());
    }
  }

  private void requestForBloodDonationRequestFulfillment() {
    final UpdateBloodDonationRequestControllerDAO updateBloodDonationRequest =
        new UpdateBloodDonationRequestController(databaseConnection, updateBloodDonationRequestStatusQueryBuilder);
    try {
      final boolean isBloodDonationRequestStatusUpdated =
          updateBloodDonationRequest.requestFulfilment(Session.USER.getUserId());
      if (isBloodDonationRequestStatusUpdated) {
        printer.printContent("Blood donation fulfillment request sent successfully.\n" +
            "You will get a call for verification.\n" +
            "Thank you for donating blood!");
      } else {
        printer.printContent("Error in blood donation request fulfillment.");
      }
    } catch (DatabaseConnectionException | BloodDonationRequestException e) {
      printer.printContent(e.toString());
    }
  }

  private void bloodDonationFulfillment() {
    printer.printScreenTitle("Request for Blood Donation Request Fulfillment");


    while (true) {
      printer.printContent("1. Request for fulfillment.");
      printer.printContent("2. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          requestForBloodDonationRequestFulfillment();
          break;
        case "2":
          return;
        default:
          break;
      }
    }
  }

  public final void performBloodDonationRequest() {
    printer.printScreenTitle("Blood Donor Requests");
    while (true) {
      printer.printContent("1. Create a new blood donation request.");
      printer.printContent("2. View all previous blood donation requests.");
      printer.printContent("3. Request blood donation fulfillment.");
      printer.printContent("4. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          createNewBloodDonationRequest();
          break;
        case "2":
          viewPreviousBloodDonationRequest();
          break;
        case "3":
          bloodDonationFulfillment();
          break;
        case "4":
          return;
        default:
          break;
      }
    }
  }
}
