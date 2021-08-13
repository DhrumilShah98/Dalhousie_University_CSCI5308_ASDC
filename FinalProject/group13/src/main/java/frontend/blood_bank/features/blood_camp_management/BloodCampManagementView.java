package frontend.blood_bank.features.blood_camp_management;

import backend.blood_bank_camp.controller.camp_organize.CampOrganizeController;
import backend.blood_bank_camp.controller.view_camplist.ViewCampListController;
import backend.blood_bank_camp.controller.view_camplist.ViewCampListControllerDAO;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilder;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilderDAO;
import backend.blood_bank_camp.exception.CampRegistrationException;
import backend.blood_bank_camp.model.Camp;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.List;
import java.util.Scanner;

public final class BloodCampManagementView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final CampQueryBuilderDAO campQueryBuilder;

  public BloodCampManagementView(final BloodBookPrinter printer,
                                 final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.campQueryBuilder =
        CampQueryBuilder.getInstance();
  }

  private void performOrganizeCamp() {
    printer.printContent("Enter Organizer name(Must be alphanumerics and spaces)");
    final String OrganizerName = scanner.nextLine();

    final int bloodBankId = Session.BLOOD_BANK.getBloodBankId();

    printer.printContent("Date of Camp(yyyy-MM-dd)");
    final String date = scanner.nextLine();

    printer.printContent("Time of Camp(HH:MM (24:00))");
    final String time = scanner.nextLine();

    printer.printContent("Available Capacity of beds in camp (Must be a number)");
    final int availableCapacity = Integer.parseInt(scanner.nextLine());

    printer.printContent("Enter Venue Address");
    final String venue = scanner.nextLine();

    printer.printContent("Enter address city");
    final String city = scanner.nextLine();

    printer.printContent("Enter contact number(902XXXXXXX)");
    final String contactNumber = scanner.nextLine();

    final Camp camp = new Camp(
        OrganizerName,
        bloodBankId,
        date,
        time,
        availableCapacity,
        venue,
        city,
        contactNumber);

    final CampOrganizeController campOrganize =
        new CampOrganizeController(
            databaseConnection,
            campQueryBuilder);
    try {
      final boolean isCampRegistered = campOrganize.register(camp);

      if (isCampRegistered) {
        printer.printContent("Camp registered successfully.");
      } else {
        printer.printContent("Camp registration failed.");
      }
    } catch (CampRegistrationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void viewAllCamp() {
    final ViewCampListControllerDAO viewCampListDAO =
        new ViewCampListController(databaseConnection, campQueryBuilder);

    try {
      final List<Camp> bloodCampList = viewCampListDAO.viewAllCamp();

      printer.printScreenTitle("All Blood Camp Registered");
      printer.printContent("\n");
      printer.printBeautifyContent("%-30s%-12s%-10s%-20s%-35s%-25s%-12s%n",
          "Organizer Name",
          "Date",
          "Time",
          "Available Capacity",
          "Venue",
          "City",
          "Contact Number");
      for (final Camp camp : bloodCampList) {
        printer.printBeautifyContent("%-30s%-12s%-10s%-20s%-35s%-25s%-12s%n",
            camp.getOrganizerName(),
            camp.getDate(),
            camp.getTime(),
            camp.getAvailableCapacity(),
            camp.getVenue(),
            camp.getCity(),
            camp.getContactNumber());
      }
    } catch (DatabaseConnectionException e) {
      System.out.println(e.getMessage());
    }
  }

  public final void manageBloodCamp() {
    while (true) {
      printer.printContent("1. Organize Blood Camp");
      printer.printContent("2. View All Registered Camp list");
      printer.printContent("3. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          performOrganizeCamp();
          break;
        case "2":
          viewAllCamp();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}