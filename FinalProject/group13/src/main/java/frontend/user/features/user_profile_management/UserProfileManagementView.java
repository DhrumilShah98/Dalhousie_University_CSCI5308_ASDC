package frontend.user.features.user_profile_management;

import backend.authentication.user.model.User;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.user_profile_management.controller.UserProfile;
import backend.user_profile_management.controller.UserProfileDAO;
import backend.user_profile_management.database.UserProfileQueryBuilder;
import backend.user_profile_management.database.UserProfileQueryBuilderDAO;
import backend.user_profile_management.exception.UserProfileException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.Scanner;

public final class UserProfileManagementView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO
      databaseConnection;
  private final UserProfileQueryBuilderDAO
      userProfileQueryBuilder;

  public UserProfileManagementView(final BloodBookPrinter printer,
                                   final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.userProfileQueryBuilder =
        UserProfileQueryBuilder.getInstance();
  }

  public final void manageMyProfile() {
    printer.printScreenTitle("User Profile Management");
    printer.printContent("\n");

    printer.printContent("Change first name (Type \"No\" to keep the same).");
    final String firstName = scanner.nextLine();

    printer.printContent("Change last name (Type \"No\" to keep the same).");
    final String lastName = scanner.nextLine();

    printer.printContent("Change address first line (Type \"No\" to " +
        "keep the same).");
    final String addressFirstLine = scanner.nextLine();

    printer.printContent("Change address street (Type \"No\" to keep the" +
        " same).");
    final String addressStreet = scanner.nextLine();

    printer.printContent("Change address city (Type \"No\" to keep the same).");
    final String addressCity = scanner.nextLine();

    printer.printContent("Change address province (Type \"No\" to keep the " +
        "same).");
    final String addressProvince = scanner.nextLine();

    printer.printContent("Change address zip code (Type \"No\" to keep the " +
        "same).");
    final String addressZipCode = scanner.nextLine();

    printer.printContent("Change address country (Type \"No\" to keep the " +
        "same).");
    final String addressCountry = scanner.nextLine();

    final String finalFirstName =
        (firstName.equalsIgnoreCase("No")) ?
            Session.USER.getFirstName() :
            firstName;
    final String finalLastName =
        (lastName.equalsIgnoreCase("No")) ?
            Session.USER.getLastName() :
            lastName;
    final String finalAddressFirstLine =
        (addressFirstLine.equalsIgnoreCase("No")) ?
            Session.USER.getAddressFirstLine() :
            addressFirstLine;
    final String finalAddressStreet =
        (addressStreet.equalsIgnoreCase("No")) ?
            Session.USER.getAddressStreet() :
            addressStreet;
    final String finalAddressCity =
        (addressCity.equalsIgnoreCase("No")) ?
            Session.USER.getAddressCity() :
            addressCity;
    final String finalAddressProvince =
        (addressProvince.equalsIgnoreCase("No")) ?
            Session.USER.getAddressProvince() :
            addressProvince;
    final String finalAddressZipCode =
        (addressZipCode.equalsIgnoreCase("No")) ?
            Session.USER.getAddressZipCode() :
            addressZipCode;
    final String finalAddressCountry =
        (addressCountry.equalsIgnoreCase("No")) ?
            Session.USER.getAddressCountry() :
            addressCountry;

    final User user = new User(
        Session.USER.getUserId(),
        finalFirstName,
        finalLastName,
        Session.USER.getGender(),
        Session.USER.getDateOfBirth(),
        Session.USER.getEmail(),
        Session.USER.getPassword(),
        Session.USER.getContactNumber(),
        Session.USER.getBloodGroup(),
        finalAddressFirstLine,
        finalAddressStreet,
        finalAddressCity,
        finalAddressProvince,
        finalAddressZipCode,
        finalAddressCountry,
        true
    );

    try {
      final UserProfileDAO userProfile =
          new UserProfile(databaseConnection,
              userProfileQueryBuilder);

      final boolean isDetailsUpdated = userProfile
          .update(user);

      if (isDetailsUpdated) {
        printer.printContent("User updated successfully.");
        Session.USER = user;
      } else {
        printer.printContent("User update failed.");
      }
    } catch (DatabaseConnectionException | UserProfileException e) {
      printer.printContent(e.toString());
    }
  }
}