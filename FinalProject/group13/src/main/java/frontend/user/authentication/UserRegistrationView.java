package frontend.user.authentication;

import backend.authentication.user.controller.registration.UserRegistrationController;
import backend.authentication.user.controller.registration.UserRegistrationControllerDAO;
import backend.authentication.user.database.registration.UserRegistrationQueryBuilder;
import backend.authentication.user.database.registration.UserRegistrationQueryBuilderDAO;
import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class UserRegistrationView {
  private final DatabaseConnectionDAO databaseConnection;
  private final UserRegistrationQueryBuilderDAO userRegistrationQueryBuilder;
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public UserRegistrationView(final BloodBookPrinter printer,
                              final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    databaseConnection =
        DatabaseConnection.getInstance();
    userRegistrationQueryBuilder =
        UserRegistrationQueryBuilder.getInstance();
  }

  public final void performUserRegistration() {
    printer.printContent("Enter first name(Must be alphabets and spaces)");
    final String firstName = scanner.nextLine();

    printer.printContent("Enter last name(Must be alphabets)");
    final String lastName = scanner.nextLine();

    printer.printContent("Enter gender(M | F | O)");
    final String gender = scanner.nextLine();

    printer.printContent("Date of birth(yyyy-MM-dd)");
    final String dateOfBirth = scanner.nextLine();

    printer.printContent("Enter email(johndoe@gmail.com)");
    final String email = scanner.nextLine();

    printer.printContent("Enter contact number(902XXXXXXX)");
    final String contactNumber = scanner.nextLine();

    printer.printContent("Enter blood group\n" +
        "(A+ | A- | B+ | B- | AB+ | AB- | O+ | O-)");
    final String bloodGroup = scanner.nextLine();

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

    printer.printContent("SQ1: Who is your childhood hero?");
    final String securityQA1 = scanner.nextLine();
    final int securityQ1ID = 4;

    printer.printContent("SQ2: What is the country of your ultimate dream " +
        "vacation");
    final String securityQA2 = scanner.nextLine();
    final int securityQ2ID = 3;

    final User user = new User(
        firstName,
        lastName,
        gender,
        dateOfBirth,
        email,
        password,
        contactNumber,
        bloodGroup,
        addressFirstLine,
        addressStreet,
        addressCity,
        addressProvince,
        addressZipCode,
        addressCountry,
        true
    );

    final UserRegistrationControllerDAO userRegistrationController =
        new UserRegistrationController(
            databaseConnection,
            userRegistrationQueryBuilder);

    try {
      final boolean isUserRegistered = userRegistrationController.register(
          user,
          securityQ1ID,
          securityQA1,
          securityQ2ID,
          securityQA2);

      if (isUserRegistered) {
        printer.printContent("User " + user.getFirstName() + " registered " +
            "successfully.");
      } else {
        printer.printContent("User " + user.getFirstName() + " registration " +
            "failed.");
      }
    } catch (UserAuthenticationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }
}