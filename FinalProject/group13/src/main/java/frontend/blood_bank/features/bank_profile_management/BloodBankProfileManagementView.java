package frontend.blood_bank.features.bank_profile_management;

import backend.authentication.blood_bank.model.BloodBank;
import backend.blood_bank_profile_management.controller.BloodBankProfileController;
import backend.blood_bank_profile_management.controller.BloodBankProfileControllerDAO;
import backend.blood_bank_profile_management.database.update_blood_bank_profile_details.UpdateBloodBankProfileDetailsQueryBuilder;
import backend.blood_bank_profile_management.database.update_blood_bank_profile_details.UpdateBloodBankProfileDetailsQueryBuilderDAO;
import backend.blood_bank_profile_management.exception.BloodBankProfileException;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.Scanner;

public final class BloodBankProfileManagementView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO
      databaseConnection;
  private final UpdateBloodBankProfileDetailsQueryBuilderDAO
      updateBloodBankProfileDetailsQueryBuilder;

  public BloodBankProfileManagementView(final BloodBookPrinter printer,
                                        final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.updateBloodBankProfileDetailsQueryBuilder =
        UpdateBloodBankProfileDetailsQueryBuilder.getInstance();
  }

  public final void manageBloodBankProfile() {
    printer.printScreenTitle("Blood bank Profile Management");
    printer.printContent("\n");
    printer.printContent("1. Update blood bank name");
    printer.printContent("2. Keep the bank name as it");
    printer.printContent("Select an option:");
    final String input = scanner.nextLine();
    String bloodBankName = Session.BLOOD_BANK.getName();
    switch (input) {
      case "1":
        printer.printContent("Enter Blood Bank name");
        bloodBankName = scanner.nextLine();
        break;
      case "2":
        bloodBankName = Session.BLOOD_BANK.getName();
        break;
      default:
        break;
    }
    printer.printContent("Enter address first line");
    final String addressFirstLine = scanner.nextLine();

    printer.printContent("Enter address street");
    final String addressStreet = scanner.nextLine();

    printer.printContent("Enter address city");
    final String addressCity = scanner.nextLine();

    printer.printContent("Enter address province");
    final String addressProvince = scanner.nextLine();

    printer.printContent("Enter address zip code");
    final String addressZipCode = scanner.nextLine();

    printer.printContent("Enter address country");
    final String addressCountry = scanner.nextLine();

    final int bloodBankId = Session.BLOOD_BANK.getBloodBankId();
    final String email = Session.BLOOD_BANK.getEmail();
    final String password = Session.BLOOD_BANK.getPassword();
    final String contactNumber = Session.BLOOD_BANK.getContactNumber();


    final BloodBank bloodBank = new BloodBank(
        bloodBankId,
        bloodBankName,
        email,
        password,
        contactNumber,
        addressFirstLine,
        addressStreet,
        addressCity,
        addressProvince,
        addressZipCode,
        addressCountry,
        true
    );

    try {
      final BloodBankProfileControllerDAO bloodBankProfile =
          new BloodBankProfileController(databaseConnection,
              updateBloodBankProfileDetailsQueryBuilder);

      final boolean isDetailsUpdated = bloodBankProfile
          .updateBloodBankProfileDetails(bloodBank);

      if (isDetailsUpdated) {
        printer.printContent("Blood bank details updated successfully.");
      } else {
        printer.printContent("Blood bank details updating failed.");
      }
    } catch (DatabaseConnectionException | BloodBankProfileException e) {
      printer.printContent(e.toString());
    }
  }
}