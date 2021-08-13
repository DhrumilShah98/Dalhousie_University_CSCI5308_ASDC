package frontend.user.features.all_blood_donor_request;

import backend.active_blood_donor_request.controller.view_active_blood_donor_request.ViewActiveBloodDonorRequestController;
import backend.active_blood_donor_request.database.view_active_blood_donor_request.ViewActiveBloodDonorRequestQueryBuilder;
import backend.active_blood_donor_request.database.view_active_blood_donor_request.ViewActiveBloodDonorRequestQueryBuilderDAO;
import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import backend.active_blood_donor_request.model.ActiveBloodDonorRequest;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.List;
import java.util.Scanner;

public class AllActiveBloodDonorRequestView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final ViewActiveBloodDonorRequestQueryBuilderDAO viewActiveBloodDonorRequestQueryBuilder;

  public AllActiveBloodDonorRequestView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.viewActiveBloodDonorRequestQueryBuilder =
        ViewActiveBloodDonorRequestQueryBuilder.getInstance();

  }

  private void allActiveBloodDonorRequests() {
    printer.printContent("Fill the requirement form");

    printer.printContent("Required Blood Group: (A+ | A- | B+ | B- | AB+ | AB- | O+ | O-)");
    final String bloodGroup = scanner.nextLine();

    printer.printContent("Required Location:");
    final String location = scanner.nextLine();

    printer.printContent("Submit (Yes/No)?");
    final String answer = scanner.nextLine();

    if (answer.equalsIgnoreCase("yes")) {
      printer.printContent("Searching for active donor requests.....");
      try {
        final ViewActiveBloodDonorRequestController viewActiveBloodDonorRequest =
            new ViewActiveBloodDonorRequestController(databaseConnection,
                viewActiveBloodDonorRequestQueryBuilder);
        final List<ActiveBloodDonorRequest> activeBloodDonorRequestList =
            viewActiveBloodDonorRequest.viewActiveDonorRequest(bloodGroup, location);
        for (ActiveBloodDonorRequest activeBloodDonorRequest : activeBloodDonorRequestList) {
          final String name = activeBloodDonorRequest.getFirstName() +
              activeBloodDonorRequest.getLastName();
          printer.printContent("Name: " + name);
          printer.printContent("Age: " + activeBloodDonorRequest.getAge());
          printer.printContent("Blood Group: " + activeBloodDonorRequest.getBloodGroup());
          printer.printContent("Email: " + activeBloodDonorRequest.getEmail());
          printer.printContent("Contact: " + activeBloodDonorRequest.getContactNumber());
          final String address = activeBloodDonorRequest.getAddressFirstLine() +
              ", " +
              activeBloodDonorRequest.getAddressStreet() +
              ", " +
              activeBloodDonorRequest.getAddressCity() +
              ", " +
              activeBloodDonorRequest.getAddressProvince() +
              ", " +
              activeBloodDonorRequest.getAddressZipCode() +
              ", " +
              activeBloodDonorRequest.getAddressCountry();
          printer.printContent("Address: " + address);
        }
      } catch (ActiveBloodDonorRequestException | DatabaseConnectionException e) {
        printer.printContent(e.toString());
      }
    } else {
      printer.printContent("Returning back.");
    }
  }

  public final void activeBloodDonorRequest() {
    printer.printScreenTitle("All Active Requests Of Blood Donors");
    printer.printContent("Hello ");

    while (true) {
      printer.printContent("1. Find compatible blood type donor.");
      printer.printContent("2. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          allActiveBloodDonorRequests();
          break;
        case "2":
          return;
        default:
          break;

      }
    }
  }
}
