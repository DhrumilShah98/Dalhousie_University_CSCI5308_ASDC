package frontend;

import backend.admin.login.model.AdminLogin;
import backend.authentication.blood_bank.model.BloodBank;
import backend.authentication.user.model.User;
import frontend.admin.AdminFeaturesView;
import frontend.admin.authentication.AdminLoginWithEmailView;
import frontend.blood_bank.authentication.BloodBankLoginWithContactNumberView;
import frontend.blood_bank.authentication.BloodBankLoginWithEmailView;
import frontend.blood_bank.authentication.BloodBankRegistrationView;
import frontend.blood_bank.features.BloodBankFeaturesView;
import frontend.user.authentication.UserLoginWithContactNumberView;
import frontend.user.authentication.UserLoginWithEmailView;
import frontend.user.authentication.UserRegistrationView;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;
import frontend.user.features.UserFeaturesView;

import java.util.Scanner;

public final class BloodBookEntry {

  private void userRegistration(final BloodBookPrinter printer,
                                final Scanner scanner) {
    final UserRegistrationView userRegistrationView =
        new UserRegistrationView(printer, scanner);
    userRegistrationView.performUserRegistration();
  }

  private User userLoginWithEmail(final BloodBookPrinter printer,
                                  final Scanner scanner) {
    final UserLoginWithEmailView userLoginWithEmailView =
        new UserLoginWithEmailView(printer, scanner);
    return userLoginWithEmailView.performUserLoginWithEmail();
  }

  private User userLoginWithContactNumber(final BloodBookPrinter printer,
                                          final Scanner scanner) {
    final UserLoginWithContactNumberView userLoginWithContactNumberView =
        new UserLoginWithContactNumberView(printer, scanner);
    return userLoginWithContactNumberView.performUserLoginWithContactNumber();
  }

  private void bloodBankRegistration(final BloodBookPrinter printer,
                                     final Scanner scanner) {
    final BloodBankRegistrationView bloodBankRegistrationView =
        new BloodBankRegistrationView(printer, scanner);
    bloodBankRegistrationView.performBloodBankRegistration();
  }

  private BloodBank bloodBankLoginWithEmail(final BloodBookPrinter printer,
                                            final Scanner scanner) {
    final BloodBankLoginWithEmailView bloodBankLoginWithEmailView =
        new BloodBankLoginWithEmailView(printer, scanner);
    return bloodBankLoginWithEmailView.performBloodBankLoginWithEmail();
  }

  private BloodBank bloodBankLoginWithContactNumber(final BloodBookPrinter printer,
                                                    final Scanner scanner) {
    final BloodBankLoginWithContactNumberView bloodBankLoginWithContactNumberView =
        new BloodBankLoginWithContactNumberView(printer, scanner);
    return bloodBankLoginWithContactNumberView.performBloodBankLoginWithEmail();
  }

  private AdminLogin adminLoginWithEmail(final BloodBookPrinter printer,
                                         final Scanner scanner) {
    final AdminLoginWithEmailView adminLoginWithEmailView =
        new AdminLoginWithEmailView(printer, scanner);
    return adminLoginWithEmailView.performAdminLoginWithEmail();
  }

  private void showBloodBankFeatures(final BloodBookPrinter printer,
                                     final Scanner scanner) {
    final BloodBankFeaturesView bloodBankFeaturesView =
        new BloodBankFeaturesView(printer, scanner);
    bloodBankFeaturesView.showBloodBankFeatures();
  }

  private void showUserFeatures(final BloodBookPrinter printer,
                                final Scanner scanner) {
    final UserFeaturesView userFeaturesView =
        new UserFeaturesView(printer, scanner);
    userFeaturesView.showUserFeatures();
  }

  private void showAdminFeatures(final BloodBookPrinter printer,
                                final Scanner scanner) {
    final AdminFeaturesView adminFeaturesView =
        new AdminFeaturesView(printer, scanner);
    adminFeaturesView.showAminFeatures();
  }

  public static void main(String[] args) {
    final BloodBookEntry bloodBook = new BloodBookEntry();
    final BloodBookPrinter printer = BloodBookPrinter.getInstance();
    final Scanner scanner = new Scanner(System.in);
    Session.clearSession();

    printer.printScreenTitle("Welcome to Blood Book\n" +
        "Blood Bank Management System\n" +
        "Select an option to begin with");

    while (true) {
      printer.printContent("1. User registration.");
      printer.printContent("2. User login with email.");
      printer.printContent("3. User login with contact number.");
      printer.printContent("4. Blood bank registration");
      printer.printContent("5. Blood bank login with email.");
      printer.printContent("6. Blood bank login with contact number.");
      printer.printContent("7. Admin login.");
      printer.printContent("8. Exit.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();

      switch (input) {
        case "1":
          bloodBook.userRegistration(printer, scanner);
          break;
        case "2":
          Session.USER = bloodBook.userLoginWithEmail(printer, scanner);
          if (Session.USER != null) {
            bloodBook.showUserFeatures(printer, scanner);
          }
          break;
        case "3":
          Session.USER = bloodBook.userLoginWithContactNumber(printer, scanner);
          if (Session.USER != null) {
            bloodBook.showUserFeatures(printer, scanner);
          }
          break;
        case "4":
          bloodBook.bloodBankRegistration(printer, scanner);
          break;
        case "5":
          Session.BLOOD_BANK = bloodBook.bloodBankLoginWithEmail(printer, scanner);
          if (Session.BLOOD_BANK != null) {
            bloodBook.showBloodBankFeatures(printer, scanner);
          }
          break;
        case "6":
          Session.BLOOD_BANK = bloodBook.bloodBankLoginWithContactNumber(printer, scanner);
          if (Session.BLOOD_BANK != null) {
            bloodBook.showBloodBankFeatures(printer, scanner);
          }
          break;
        case "7":
          Session.ADMIN = bloodBook.adminLoginWithEmail(printer, scanner);
          if (Session.ADMIN != null) {
            bloodBook.showAdminFeatures(printer, scanner);
          }
          break;
        case "8":
          Session.clearSession();
          System.exit(0);
        default:
          break;
      }
    }
  }
}