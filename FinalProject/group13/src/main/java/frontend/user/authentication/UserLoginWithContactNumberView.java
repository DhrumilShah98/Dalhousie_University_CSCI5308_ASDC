package frontend.user.authentication;

import backend.authentication.user.controller.login_with_contact_number.UserLoginWithContactNumberController;
import backend.authentication.user.controller.login_with_contact_number.UserLoginWithContactNumberControllerDAO;
import backend.authentication.user.database.login_with_contact_number.UserLoginWithContactNumberQueryBuilder;
import backend.authentication.user.database.login_with_contact_number.UserLoginWithContactNumberQueryBuilderDAO;
import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class UserLoginWithContactNumberView {
  private final DatabaseConnectionDAO databaseConnection;
  private final UserLoginWithContactNumberQueryBuilderDAO
      userLoginWithContactNumberQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public UserLoginWithContactNumberView(final BloodBookPrinter printer,
                                        final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    userLoginWithContactNumberQueryBuilder =
        UserLoginWithContactNumberQueryBuilder.getInstance();
  }

  public final User performUserLoginWithContactNumber() {
    printer.printContent("Enter contact number(902XXXXXXX)");
    final String contactNumber = scanner.nextLine();

    printer.printContent("Enter password");
    final String password = scanner.nextLine();

    final UserLoginWithContactNumberControllerDAO
        userLoginWithContactNumberController =
        new UserLoginWithContactNumberController(
            databaseConnection,
            userLoginWithContactNumberQueryBuilder);

    try {
      final User user = userLoginWithContactNumberController
          .loginWithContactNumber(contactNumber, password);

      if (user != null) {
        printer.printContent("Logged in as: " + user.getFirstName());
        return user;
      } else {
        printer.printContent("Login failed.");
        return null;
      }
    } catch (UserAuthenticationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
      return null;
    }
  }
}