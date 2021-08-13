package frontend.blood_bank.authentication;

import backend.authentication.blood_bank.controller.login_with_email.BloodBankLoginWithEmailController;
import backend.authentication.blood_bank.controller.login_with_email.BloodBankLoginWithEmailControllerDAO;
import backend.authentication.blood_bank.database.login_with_email.BloodBankLoginWithEmailQueryBuilder;
import backend.authentication.blood_bank.database.login_with_email.BloodBankLoginWithEmailQueryBuilderDAO;
import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class BloodBankLoginWithEmailView {
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodBankLoginWithEmailQueryBuilderDAO
      bloodBankLoginWithEmailQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public BloodBankLoginWithEmailView(final BloodBookPrinter printer,
                                     final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    bloodBankLoginWithEmailQueryBuilder =
        BloodBankLoginWithEmailQueryBuilder.getInstance();
  }

  public final BloodBank performBloodBankLoginWithEmail() {
    printer.printContent("Enter email(redcross@gmail.com)");
    final String email = scanner.nextLine();

    printer.printContent("Enter password");
    final String password = scanner.nextLine();

    final BloodBankLoginWithEmailControllerDAO bloodBankWithEmailController =
        new BloodBankLoginWithEmailController(
            databaseConnection,
            bloodBankLoginWithEmailQueryBuilder);

    try {
      final BloodBank bloodBank = bloodBankWithEmailController
          .loginWithEmail(email, password);

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