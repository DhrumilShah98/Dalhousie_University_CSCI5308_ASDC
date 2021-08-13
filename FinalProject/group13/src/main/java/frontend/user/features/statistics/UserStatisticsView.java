package frontend.user.features.statistics;

import backend.blood_donation_statistic.controller.average_blood_donor_age.AverageBloodDonorAgeController;
import backend.blood_donation_statistic.controller.average_blood_donor_age.AverageBloodDonorAgeControllerDAO;
import backend.blood_donation_statistic.controller.blood_donor_classification.ClassifyBloodDonorByAgeGroupController;
import backend.blood_donation_statistic.controller.blood_donor_classification.ClassifyBloodDonorByAgeGroupControllerDAO;
import backend.blood_donation_statistic.controller.highest_blood_type_donated.HighestBloodTypeDonatedController;
import backend.blood_donation_statistic.controller.highest_blood_type_donated.HighestBloodTypeDonatedControllerDAO;
import backend.blood_donation_statistic.controller.number_of_active_donor.NumberOfActiveBloodDonorController;
import backend.blood_donation_statistic.controller.number_of_active_donor.NumberOfActiveBloodDonorControllerDAO;
import backend.blood_donation_statistic.controller.yearly_blood_donation.YearlyBloodDonationController;
import backend.blood_donation_statistic.controller.yearly_blood_donation.YearlyBloodDonationControllerDAO;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilder;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilderDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.controller.average_age.FindAverageReceiverRequestAgeController;
import backend.blood_request_statistic.controller.average_age.FindAverageReceiverRequestAgeControllerDAO;
import backend.blood_request_statistic.controller.classification_on_age.CountReceiverRequestByAgeGroupController;
import backend.blood_request_statistic.controller.classification_on_age.CountReceiverRequestByAgeGroupControllerDAO;
import backend.blood_request_statistic.controller.highest_blood_type.FindHighestBloodTypeRequestedController;
import backend.blood_request_statistic.controller.highest_blood_type.FindHighestBloodTypeRequestedControllerDAO;
import backend.blood_request_statistic.controller.number_of_active_receiver.ActiveReceiverNumberController;
import backend.blood_request_statistic.controller.number_of_active_receiver.ActiveReceiverNumberControllerDAO;
import backend.blood_request_statistic.database.AllStatisticsQueryBuilder;
import backend.blood_request_statistic.database.AllStatisticsQueryBuilderDAO;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import frontend.print.BloodBookPrinter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserStatisticsView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilder;
  private final AllStatisticsQueryBuilderDAO allStatisticsQueryBuilder;

  public UserStatisticsView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.bloodDonationStatisticsQueryBuilder =
        BloodDonationStatisticsQueryBuilder.getInstance();
    this.allStatisticsQueryBuilder =
        AllStatisticsQueryBuilder.getInstance();
  }

  private void activeBloodDonors() {
    final NumberOfActiveBloodDonorControllerDAO numberOfActiveBloodDonor =
        new NumberOfActiveBloodDonorController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    try {
      final int activeBloodDonors = numberOfActiveBloodDonor.countActiveBloodDonor();
      printer.printContent("Number of active blood donors are: " + activeBloodDonors);
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void activeBloodReceivers() {
    final ActiveReceiverNumberControllerDAO activeReceiverNumber =
        new ActiveReceiverNumberController(databaseConnection, allStatisticsQueryBuilder);
    final int activeBloodReceivers;
    try {
      activeBloodReceivers = activeReceiverNumber.viewActiveReceiverNumber();
      printer.printContent("Number of active blood receivers are: " + activeBloodReceivers);
    } catch (DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void averageBloodDonorAge() {
    final AverageBloodDonorAgeControllerDAO averageBloodDonorAge =
        new AverageBloodDonorAgeController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    try {
      final int averageAge = averageBloodDonorAge.viewAverageBloodDonorAge();
      printer.printContent("Average blood donor age is: " + averageAge);
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void averageBloodReceiverAge() {
    final FindAverageReceiverRequestAgeControllerDAO findAverageReceiverRequestAge =
        new FindAverageReceiverRequestAgeController(databaseConnection, allStatisticsQueryBuilder);
    try {
      final int averageAge = findAverageReceiverRequestAge.viewReceiverAverageAge();
      printer.printContent("Average blood receiver age is: " + averageAge);
    } catch (DatabaseConnectionException | ReceiverStatisticsException e) {
      printer.printContent(e.toString());
    }
  }

  private void highestBloodTypeDonated() {
    final HighestBloodTypeDonatedControllerDAO highestBloodTypeDonated =
        new HighestBloodTypeDonatedController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    try {
      final List<String> highestBloodDonated = highestBloodTypeDonated.findHighestDonatedBloodGroup();
      printer.printContent("List of highest blood type donated:");
      for (String bloodType : highestBloodDonated) {
        printer.printContent("Blood type: " + bloodType);
      }
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void highestBloodTypeRequested() {
    final FindHighestBloodTypeRequestedControllerDAO findHighestBloodTypeRequested =
        new FindHighestBloodTypeRequestedController(databaseConnection, allStatisticsQueryBuilder);
    try {
      final List<String> highestBloodRequested = findHighestBloodTypeRequested.findHighestRequestedBloodGroup();
      printer.printContent("List of highest blood type requested is: ");
      for (String bloodType : highestBloodRequested) {
        printer.printContent("Blood type: " + bloodType);
      }
    } catch (DatabaseConnectionException | ReceiverStatisticsException e) {
      printer.printContent(e.toString());
    }
  }

  private void yearlyBloodDonations() {
    final YearlyBloodDonationControllerDAO yearlyBloodDonation =
        new YearlyBloodDonationController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    try {
      final long yearlyBloodDonated = yearlyBloodDonation.yearlyBloodDonations();
      printer.printContent("Number of blood donations in a year: " + yearlyBloodDonated);
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void classifyBloodDonorByAge() {
    final ClassifyBloodDonorByAgeGroupControllerDAO classifyBloodDonorByAgeGroup =
        new ClassifyBloodDonorByAgeGroupController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    try {
      final Map<String, Float> classifyBloodDonor = classifyBloodDonorByAgeGroup.bloodDonorAgeClassification();
      printer.printContent("Blood donor age classification is:");
      for (Map.Entry<String, Float> entry : classifyBloodDonor.entrySet()) {
        printer.printContent(entry.getKey() + "  " + entry.getValue());
      }
    } catch (BloodDonationStatisticsException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void classifyBloodReceiverByAge() {
    final CountReceiverRequestByAgeGroupControllerDAO countReceiverRequestByAgeGroup =
        new CountReceiverRequestByAgeGroupController(databaseConnection, allStatisticsQueryBuilder);
    try {
      final Map<String, Float> classifyBloodReceiver = countReceiverRequestByAgeGroup.viewReceiverAgeClassification();
      printer.printContent("Blood receiver age classification is:");
      for (Map.Entry<String, Float> entry : classifyBloodReceiver.entrySet()) {
        printer.printContent(entry.getKey() + "  " + entry.getValue());
      }
    } catch (DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  public final void fetchStatistics() {
    printer.printScreenTitle("User Statistics");
    while (true) {
      printer.printContent("1. How many active donors?");
      printer.printContent("2. How many active receivers?");
      printer.printContent("3. Average blood donor age?");
      printer.printContent("4. Average blood receiver age?");
      printer.printContent("5. Highest blood type donated?");
      printer.printContent("6. Highest blood type requested?");
      printer.printContent("7. Yearly blood donations?");
      printer.printContent("8. Blood donor age group classification?");
      printer.printContent("9. Blood receiver age group classification?");
      printer.printContent("10. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          activeBloodDonors();
          break;
        case "2":
          activeBloodReceivers();
          break;
        case "3":
          averageBloodDonorAge();
          break;
        case "4":
          averageBloodReceiverAge();
          break;
        case "5":
          highestBloodTypeDonated();
          break;
        case "6":
          highestBloodTypeRequested();
          break;
        case "7":
          yearlyBloodDonations();
          break;
        case "8":
          classifyBloodDonorByAge();
          break;
        case "9":
          classifyBloodReceiverByAge();
          break;
        case "10":
          return;
        default:
          break;
      }
    }
  }
}
