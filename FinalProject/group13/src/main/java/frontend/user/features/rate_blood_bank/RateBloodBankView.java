package frontend.user.features.rate_blood_bank;

import backend.blood_bank_rating_by_user.controller.BloodBankRatingController;
import backend.blood_bank_rating_by_user.controller.BloodBankRatingControllerDAO;
import backend.blood_bank_rating_by_user.database.BloodBankRatingQueryBuilder;
import backend.blood_bank_rating_by_user.database.BloodBankRatingQueryBuilderDAO;
import backend.blood_bank_rating_by_user.exception.BloodBankRatingException;
import backend.blood_bank_view_all.controller.BloodBankViewController;
import backend.blood_bank_view_all.controller.BloodBankViewControllerDAO;
import backend.blood_bank_view_all.database.BloodBankViewQueryBuilder;
import backend.blood_bank_view_all.database.BloodBankViewQueryBuilderDAO;
import backend.blood_bank_view_all.model.BloodBankView;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.List;
import java.util.Scanner;

public final class RateBloodBankView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public RateBloodBankView(final BloodBookPrinter printer,
                           final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  private void viewAllBloodBanks() {
    final DatabaseConnectionDAO databaseConnection =
        DatabaseConnection.getInstance();
    final BloodBankViewQueryBuilderDAO bloodBankViewQueryBuilder =
        BloodBankViewQueryBuilder.getInstance();

    final BloodBankViewControllerDAO bloodBankViewController =
        new BloodBankViewController(databaseConnection,
            bloodBankViewQueryBuilder);

    try {
      final List<BloodBankView> allBloodBanks =
          bloodBankViewController.getAllBloodBanks();

      if (allBloodBanks != null) {
        printer.printBeautifyContent("%-15s%-40s%n",
            "Blood Bank Id",
            "Blood Bank Name");
        for (final BloodBankView bloodBankView : allBloodBanks) {
          printer.printBeautifyContent("%-15s%-40s%n",
              bloodBankView.getBloodBankId(),
              bloodBankView.getName());
        }
      } else {
        printer.printContent("No blood banks found.");
      }
    } catch (DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void addRating() {
    printer.printContent("Enter blood bank id.");
    final int bloodBankId = scanner.nextInt();
    scanner.nextLine();

    printer.printContent("Enter comment (Alphanumeric)");
    final String comment = scanner.nextLine();

    printer.printContent("Stars (0 - 5 | Multiple of 0.5)");
    final float star = scanner.nextFloat();
    scanner.nextLine();

    final DatabaseConnectionDAO databaseConnection =
        DatabaseConnection.getInstance();
    final BloodBankRatingQueryBuilderDAO bloodBankRatingQueryBuilder =
        BloodBankRatingQueryBuilder.getInstance();

    final BloodBankRatingControllerDAO bloodBankRatingController =
        new BloodBankRatingController(databaseConnection,
            bloodBankRatingQueryBuilder);

    try {
      final boolean isCommentInserted = bloodBankRatingController.insertBloodBankRating(bloodBankId,
          Session.USER.getUserId(),
          Session.USER.getDateOfBirth(),
          comment,
          star);

      if (isCommentInserted) {
        printer.printContent("Comment inserted successfully.");
      } else {
        printer.printContent("Failed to insert comment");
      }
    } catch (BloodBankRatingException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  public final void performRating() {
    printer.printScreenTitle("Rate Blood Bank");
    while (true) {
      printer.printContent("1. View all blood banks");
      printer.printContent("2. Rate blood bank");
      printer.printContent("3. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          viewAllBloodBanks();
          break;
        case "2":
          addRating();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}
