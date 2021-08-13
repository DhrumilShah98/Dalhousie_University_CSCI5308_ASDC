package frontend.user.features.all_blood_receiver_request;

import backend.active_blood_receiver_request.controller.ViewAllActiveReceiverRequestController;
import backend.active_blood_receiver_request.database.ActiveReceiverRequestQueryBuilder;
import backend.active_blood_receiver_request.database.ActiveReceiverRequestQueryBuilderDAO;
import backend.active_blood_receiver_request.exception.ActiveReceiverRequestException;
import backend.active_blood_receiver_request.model.ActiveReceiverRequest;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.List;
import java.util.Scanner;

public class AllActiveBloodReceiverRequestView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final ActiveReceiverRequestQueryBuilderDAO activeReceiverRequestQueryBuilder;

  public AllActiveBloodReceiverRequestView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.activeReceiverRequestQueryBuilder =
        ActiveReceiverRequestQueryBuilder.getInstance();

  }

  private void allActiveBloodReceiverRequests() {
    printer.printContent("Fill the requirement form");

    printer.printContent("Required Blood Group: (A+ | A- | B+ | B- | AB+ | AB- | O+ | O-)");
    final String bloodGroup = scanner.nextLine();

    printer.printContent("Required Location:");
    final String location = scanner.nextLine();

    printer.printContent("Submit (Yes/No)?");
    final String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      printer.printContent("Searching for active receiver requests.....");
      try {
        final ViewAllActiveReceiverRequestController viewAllActiveReceiverRequestController =
            new ViewAllActiveReceiverRequestController(databaseConnection,
                activeReceiverRequestQueryBuilder);
        final List<ActiveReceiverRequest> activeBloodReceiverRequestList =
            viewAllActiveReceiverRequestController.viewActiveReceiverRequest(bloodGroup, location);
        for (ActiveReceiverRequest activeReceiverRequest : activeBloodReceiverRequestList) {
          final String name = activeReceiverRequest.getFirstName() +
              activeReceiverRequest.getLastName();
          printer.printContent("Name: " + name);
          printer.printContent("Age: " + activeReceiverRequest.getAge());
          printer.printContent("Blood Group: " + activeReceiverRequest.getBloodGroup());
          printer.printContent("Email: " + activeReceiverRequest.getEmail());
          printer.printContent("Contact: " + activeReceiverRequest.getContactNumber());
          final String address = activeReceiverRequest.getAddressFirstLine() +
              ", " +
              activeReceiverRequest.getAddressStreet() +
              ", " +
              activeReceiverRequest.getAddressCity() +
              ", " +
              activeReceiverRequest.getAddressProvince() +
              ", " +
              activeReceiverRequest.getAddressZipCode() +
              ", " +
              activeReceiverRequest.getAddressCountry();
          printer.printContent("Address: " + address);
        }
      } catch (DatabaseConnectionException | ActiveReceiverRequestException e) {
        printer.printContent(e.toString());
      }
    } else {
      printer.printContent("Returning back.");
    }
  }

  public final void activeBloodReceiverRequest() {
    printer.printScreenTitle("All Active Requests Of Blood Receiver");
    printer.printContent("Hello ");

    while (true) {
      printer.printContent("1. Find compatible blood type receiver.");
      printer.printContent("2. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          allActiveBloodReceiverRequests();
          break;
        case "2":
          return;
        default:
          break;
      }
    }
  }
}
