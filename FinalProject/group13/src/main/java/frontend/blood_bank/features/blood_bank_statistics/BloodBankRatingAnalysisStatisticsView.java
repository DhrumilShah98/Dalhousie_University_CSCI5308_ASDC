package frontend.blood_bank.features.blood_bank_statistics;

import backend.blood_bank_rating_analysis.analysis_by_age_group.controller.BloodBankRatingAnalysisAgeGroupController;
import backend.blood_bank_rating_analysis.analysis_by_age_group.controller.BloodBankRatingAnalysisAgeGroupControllerDAO;
import backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupQueryBuilder;
import backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupQueryBuilderDAO;
import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import backend.blood_bank_rating_analysis.analysis_by_location.controller.BloodBankRatingAnalysisLocationController;
import backend.blood_bank_rating_analysis.analysis_by_location.controller.BloodBankRatingAnalysisLocationControllerDAO;
import backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationQueryBuilder;
import backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationQueryBuilderDAO;
import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import backend.blood_bank_rating_analysis.analysis_by_location.model.BloodBankRatingAnalysisLocation;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;
import frontend.session.Session;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class BloodBankRatingAnalysisStatisticsView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodBankRatingAnalysisAgeGroupQueryBuilderDAO
      bloodBankRatingAnalysisAgeGroupQueryBuilder;
  private final BloodBankRatingAnalysisLocationQueryBuilderDAO
      bloodBankRatingAnalysisLocationQueryBuilder;

  public BloodBankRatingAnalysisStatisticsView(final BloodBookPrinter printer,
                                               final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.bloodBankRatingAnalysisAgeGroupQueryBuilder =
        BloodBankRatingAnalysisAgeGroupQueryBuilder.getInstance();
    this.bloodBankRatingAnalysisLocationQueryBuilder =
        BloodBankRatingAnalysisLocationQueryBuilder.getInstance();

  }

  private void performAnalysisAgeGroup() {
    try {
      final BloodBankRatingAnalysisAgeGroupControllerDAO
          bloodBankRatingAnalysisAgeGroupControllerDAO =
          new BloodBankRatingAnalysisAgeGroupController(databaseConnection,
              bloodBankRatingAnalysisAgeGroupQueryBuilder);
      final Map<String, Float> bloodBankRatingsMap =
          bloodBankRatingAnalysisAgeGroupControllerDAO
              .getBloodBankRatings(Session.BLOOD_BANK.getBloodBankId());

      printer.printScreenTitle("Blood Bank Analysis by Age Group");
      printer.printContent("\n");
      printer.printBeautifyContent("%-15s%-15s%n",
          "Age Group", "Rating");

      for (Map.Entry<String, Float> bloodBankRating :
          bloodBankRatingsMap.entrySet()) {
        printer.printBeautifyContent("%-15s%-15s%n",
            bloodBankRating.getKey(),
            bloodBankRating.getValue());
      }
      printer.printContent("\n");
    } catch (DatabaseConnectionException e) {
      System.out.println(e.getMessage());
    }
  }

  private void performAnalysisLocation() {
    try {
      final BloodBankRatingAnalysisLocationControllerDAO
          bloodBankRatingAnalysisLocationControllerDAO =
          new BloodBankRatingAnalysisLocationController(databaseConnection,
              bloodBankRatingAnalysisLocationQueryBuilder);
      final List<BloodBankRatingAnalysisLocation> bloodBankRatingLocationList =
          bloodBankRatingAnalysisLocationControllerDAO.getBloodBankRatings();

      printer.printScreenTitle("Blood Bank Analysis by Location");
      printer.printContent("\n");
      printer.printContent("Blood bank name: " + Session.BLOOD_BANK.getName());
      printer.printBeautifyContent("%-25s%-25s%-25s%-20s%-15s%n",
          "Blood Bank Name",
          "Address Province",
          "Relative Rating Score",
          "Province Rank",
          "Country Rank");
      for (final BloodBankRatingAnalysisLocation bloodBankRatingAnalysisLocation :
          bloodBankRatingLocationList) {
        if (Session.BLOOD_BANK.getBloodBankId() == bloodBankRatingAnalysisLocation.getBloodBankId()) {
          printer.printBeautifyContent("%-25s%-25s%-25s%-20s%-15s%n",
              bloodBankRatingAnalysisLocation.getBloodBankName(),
              bloodBankRatingAnalysisLocation.getAddressProvince(),
              bloodBankRatingAnalysisLocation.getStar(),
              bloodBankRatingAnalysisLocation.getAddressProvinceRank(),
              bloodBankRatingAnalysisLocation.getTotalRank());
        } else {
          printer.printBeautifyContent("%-25s%-25s%-25s%-20s%-15s%n",
              bloodBankRatingAnalysisLocation.getBloodBankName(),
              bloodBankRatingAnalysisLocation.getAddressProvince(),
              bloodBankRatingAnalysisLocation.getStar(),
              bloodBankRatingAnalysisLocation.getAddressProvinceRank(),
              bloodBankRatingAnalysisLocation.getTotalRank());
        }
      }
    } catch (DatabaseConnectionException e) {
      System.out.println(e.getMessage());
    }
  }

  public final void bloodBankRatingAnalysis() {
    while (true) {
      printer.printContent("1. Blood Bank Analysis by Age Group.");
      printer.printContent("2. Blood Bank Analysis by Location.");
      printer.printContent("3. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          performAnalysisAgeGroup();
          break;
        case "2":
          performAnalysisLocation();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}