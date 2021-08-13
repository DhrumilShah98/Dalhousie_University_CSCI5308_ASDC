package frontend.user.authentication;

import backend.authentication.user.controller.login_with_email.UserLoginWithEmailController;
import backend.authentication.user.controller.login_with_email.UserLoginWithEmailControllerDAO;
import backend.authentication.user.database.login_with_email.UserLoginWithEmailQueryBuilder;
import backend.authentication.user.database.login_with_email.UserLoginWithEmailQueryBuilderDAO;
import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class UserLoginWithEmailView {
  private final DatabaseConnectionDAO databaseConnection;
  private final UserLoginWithEmailQueryBuilderDAO
      userLoginWithEmailQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public UserLoginWithEmailView(final BloodBookPrinter printer,
                                final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    userLoginWithEmailQueryBuilder =
        UserLoginWithEmailQueryBuilder.getInstance();
  }

  public final User performUserLoginWithEmail() {
    printer.printContent("Enter email(johndoe@gmail.com)");
    final String email = scanner.nextLine();

    printer.printContent("Enter password");
    final String password = scanner.nextLine();

    final UserLoginWithEmailControllerDAO userLoginWithEmailController =
        new UserLoginWithEmailController(
            databaseConnection,
            userLoginWithEmailQueryBuilder);

    try {
      final User user = userLoginWithEmailController
          .loginWithEmail(email, password);

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
