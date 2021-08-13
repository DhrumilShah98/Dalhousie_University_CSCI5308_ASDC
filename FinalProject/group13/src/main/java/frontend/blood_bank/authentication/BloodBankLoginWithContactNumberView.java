package frontend.blood_bank.authentication;

import backend.authentication.blood_bank.controller.login_with_contact_number.BloodBankLoginWithContactNumberController;
import backend.authentication.blood_bank.controller.login_with_contact_number.BloodBankLoginWithContactNumberControllerDAO;
import backend.authentication.blood_bank.database.login_with_contact_number.BloodBankLoginWithContactNumberQueryBuilder;
import backend.authentication.blood_bank.database.login_with_contact_number.BloodBankLoginWithContactNumberQueryBuilderDAO;
import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class BloodBankLoginWithContactNumberView {
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodBankLoginWithContactNumberQueryBuilderDAO bloodBankLoginWithContactNumberQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public BloodBankLoginWithContactNumberView(final BloodBookPrinter printer,
                                             final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    bloodBankLoginWithContactNumberQueryBuilder =
        BloodBankLoginWithContactNumberQueryBuilder.getInstance();
  }

  public final BloodBank performBloodBankLoginWithEmail() {
    printer.printContent("Enter contact number(902XXXXXXX)");
    final String contactNumber = scanner.nextLine();

    printer.printContent("Enter password");
    final String password = scanner.nextLine();

    final BloodBankLoginWithContactNumberControllerDAO
        bloodBankLoginWithContactNumberController =
        new BloodBankLoginWithContactNumberController(
            databaseConnection,
            bloodBankLoginWithContactNumberQueryBuilder);

    try {
      final BloodBank bloodBank = bloodBankLoginWithContactNumberController
          .loginWithContactNumber(contactNumber, password);

      if (bloodBank != null) {
        printer.printContent("Logged in as: " + bloodBank.getName());
        return bloodBank;
      } else {
        printer.printContent("Login failed.");
        return null;
      }
    } catch (BloodBankAuthenticationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
      return null;
    }
  }
}
