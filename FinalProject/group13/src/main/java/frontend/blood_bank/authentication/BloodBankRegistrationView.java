package frontend.blood_bank.authentication;

import backend.authentication.blood_bank.controller.registration.BloodBankRegistrationController;
import backend.authentication.blood_bank.controller.registration.BloodBankRegistrationControllerDAO;
import backend.authentication.blood_bank.database.registration.BloodBankRegistrationQueryBuilder;
import backend.authentication.blood_bank.database.registration.BloodBankRegistrationQueryBuilderDAO;
import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class BloodBankRegistrationView {
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodBankRegistrationQueryBuilderDAO
      bloodBankRegistrationQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public BloodBankRegistrationView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    bloodBankRegistrationQueryBuilder =
        BloodBankRegistrationQueryBuilder.getInstance();
  }

  public void performBloodBankRegistration() {
    printer.printContent("Enter blood bank name(Must be alphanumerics and spaces)");
    final String bloodBankName = scanner.nextLine();

    printer.printContent("Enter email(redcross@gmail.com)");
    final String email = scanner.nextLine();

    printer.printContent("Enter contact number(902XXXXXXX)");
    final String contactNumber = scanner.nextLine();

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

    printer.printContent("Enter password\n" +
        "(1 small letter, 1 capital letter, 1 special char, and 1 number and " +
        "min length 8 and max length 20)");
    final String password = scanner.nextLine();

    printer.printContent("SQ1: How many people started the organization?");
    final String securityQA1 = scanner.nextLine();
    final int securityQ1ID = 1;

    printer.printContent("SQ2: When was organization started (yyyy-MM-dd)?");
    final String securityQA2 = scanner.nextLine();
    final int securityQ2ID = 2;

    final BloodBank bloodBank = new BloodBank(
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

    final BloodBankRegistrationControllerDAO bloodBankRegistrationController =
        new BloodBankRegistrationController(
            databaseConnection,
            bloodBankRegistrationQueryBuilder);

    try {
      final boolean isBloodBankRegistered = bloodBankRegistrationController
          .register(
              bloodBank,
              securityQ1ID,
              securityQA1,
              securityQ2ID,
              securityQA2);

      if (isBloodBankRegistered) {
        printer.printContent("Blood bank " + bloodBank.getName() +
            "registered successfully.");
      } else {
        printer.printContent("Blood bank " + bloodBank.getName() +
            "registration failed.");
      }
    } catch (BloodBankAuthenticationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }
}