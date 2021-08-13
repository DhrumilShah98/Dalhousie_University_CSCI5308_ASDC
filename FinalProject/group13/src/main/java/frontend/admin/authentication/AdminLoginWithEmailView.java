package frontend.admin.authentication;

import backend.admin.login.controller.AdminLoginController;
import backend.admin.login.controller.AdminLoginControllerDAO;
import backend.admin.login.database.login.AdminQueryBuilder;
import backend.admin.login.database.login.AdminQueryBuilderDAO;
import backend.admin.login.exception.AdminException;
import backend.admin.login.model.AdminLogin;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class AdminLoginWithEmailView {
  private final DatabaseConnectionDAO databaseConnection;
  private final AdminQueryBuilderDAO
      adminQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public AdminLoginWithEmailView(final BloodBookPrinter printer,
                                 final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    adminQueryBuilder =
        AdminQueryBuilder.getInstance();
  }

  public final AdminLogin performAdminLoginWithEmail() {
    printer.printContent("Enter email(johndoe@gmail.com)");
    final String email = scanner.nextLine();

    printer.printContent("Enter password");
    final String password = scanner.nextLine();

    final AdminLoginControllerDAO adminLoginController =
        new AdminLoginController(
            databaseConnection,
            adminQueryBuilder);

    try {
      final AdminLogin admin = adminLoginController
          .loginWithEmail(email, password);

      if (admin != null) {
        printer.printContent("Logged in as admin.");
        return admin;
      } else {
        printer.printContent("Login failed.");
        return null;
      }
    } catch (AdminException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
      return null;
    }
  }
}