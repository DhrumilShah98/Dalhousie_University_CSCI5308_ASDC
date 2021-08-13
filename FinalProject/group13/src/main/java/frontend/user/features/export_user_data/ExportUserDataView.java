package frontend.user.features.export_user_data;

import backend.blood_donation_request.controller.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestController;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilder;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.exception.BloodDonationRequestException;
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
import backend.blood_receiver_request.controller.view_receiver_request.ViewAllReceiverRequestController;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilder;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilderDAO;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
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
import backend.export_user_data.controller.export_user_blood_donation_requests.ExportUserBloodDonationRequestController;
import backend.export_user_data.controller.export_user_blood_donation_requests.ExportUserBloodDonationRequestControllerDAO;
import backend.export_user_data.controller.export_user_blood_receiver_requests.ExportUserBloodReceiverRequestController;
import backend.export_user_data.controller.export_user_blood_receiver_requests.ExportUserBloodReceiverRequestControllerDAO;
import backend.export_user_data.controller.export_user_statistics.ExportUserStatisticsController;
import backend.export_user_data.controller.export_user_statistics.ExportUserStatisticsControllerDAO;
import backend.export_user_data.exception.ExportUserDataException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public class ExportUserDataView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final DatabaseConnectionDAO databaseConnection;
  private final AverageBloodDonorAgeControllerDAO averageBloodDonorAgeControllerDAO;
  private final ClassifyBloodDonorByAgeGroupControllerDAO classifyBloodDonorByAgeGroupControllerDAO;
  private final HighestBloodTypeDonatedControllerDAO highestBloodTypeDonatedControllerDAO;
  private final NumberOfActiveBloodDonorControllerDAO numberOfActiveBloodDonorControllerDAO;
  private final YearlyBloodDonationControllerDAO yearlyBloodDonationControllerDAO;
  private final FindAverageReceiverRequestAgeControllerDAO findAverageReceiverRequestAgeControllerDAO;
  private final CountReceiverRequestByAgeGroupControllerDAO countReceiverRequestByAgeGroupControllerDAO;
  private final FindHighestBloodTypeRequestedControllerDAO findHighestBloodTypeRequestedControllerDAO;
  private final ActiveReceiverNumberControllerDAO activeReceiverNumberControllerDAO;
  private final BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilder;
  private final AllStatisticsQueryBuilderDAO allStatisticsQueryBuilder;
  private final ViewPreviousBloodDonationRequestController viewPreviousBloodDonationRequest;
  private final ViewPreviousBloodDonationRequestQueryBuilderDAO viewPreviousBloodDonationRequestQueryBuilder;
  private final ViewAllReceiverRequestController viewAllReceiverRequest;
  private final BloodReceiverRequestQueryBuilderDAO bloodReceiverRequestQueryBuilder;


  public ExportUserDataView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.bloodDonationStatisticsQueryBuilder =
        BloodDonationStatisticsQueryBuilder.getInstance();
    this.allStatisticsQueryBuilder =
        AllStatisticsQueryBuilder.getInstance();
    this.viewPreviousBloodDonationRequestQueryBuilder =
        ViewPreviousBloodDonationRequestQueryBuilder.getInstance();
    this.bloodReceiverRequestQueryBuilder =
        BloodReceiverRequestQueryBuilder.getInstance();
    this.viewAllReceiverRequest =
        new ViewAllReceiverRequestController(databaseConnection, bloodReceiverRequestQueryBuilder);
    this.viewPreviousBloodDonationRequest =
        new ViewPreviousBloodDonationRequestController(databaseConnection, viewPreviousBloodDonationRequestQueryBuilder);
    this.averageBloodDonorAgeControllerDAO =
        new AverageBloodDonorAgeController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    this.classifyBloodDonorByAgeGroupControllerDAO =
        new ClassifyBloodDonorByAgeGroupController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    this.highestBloodTypeDonatedControllerDAO =
        new HighestBloodTypeDonatedController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    this.numberOfActiveBloodDonorControllerDAO =
        new NumberOfActiveBloodDonorController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    this.yearlyBloodDonationControllerDAO =
        new YearlyBloodDonationController(databaseConnection, bloodDonationStatisticsQueryBuilder);
    this.findAverageReceiverRequestAgeControllerDAO =
        new FindAverageReceiverRequestAgeController(databaseConnection, allStatisticsQueryBuilder);
    this.countReceiverRequestByAgeGroupControllerDAO =
        new CountReceiverRequestByAgeGroupController(databaseConnection, allStatisticsQueryBuilder);
    this.findHighestBloodTypeRequestedControllerDAO =
        new FindHighestBloodTypeRequestedController(databaseConnection, allStatisticsQueryBuilder);
    this.activeReceiverNumberControllerDAO =
        new ActiveReceiverNumberController(databaseConnection, allStatisticsQueryBuilder);
  }

  private void exportUserStatisticsData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportUserStatisticsControllerDAO exportUserStatistics =
        new ExportUserStatisticsController(averageBloodDonorAgeControllerDAO,
            classifyBloodDonorByAgeGroupControllerDAO,
            highestBloodTypeDonatedControllerDAO,
            numberOfActiveBloodDonorControllerDAO,
            yearlyBloodDonationControllerDAO,
            findAverageReceiverRequestAgeControllerDAO,
            countReceiverRequestByAgeGroupControllerDAO,
            findHighestBloodTypeRequestedControllerDAO,
            activeReceiverNumberControllerDAO);
    try {
      printer.printContent("Preparing data export....");
      final boolean dataExported = exportUserStatistics.exportUserStatisticsData(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (ExportUserDataException | DatabaseConnectionException | BloodDonationStatisticsException | ReceiverStatisticsException e) {
      printer.printContent(e.toString());
    }
  }

  private void exportUserBloodDonationData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportUserBloodDonationRequestControllerDAO exportUserBloodDonationRequest =
        new ExportUserBloodDonationRequestController(viewPreviousBloodDonationRequest);
    try {
      printer.printContent("Preparing data export....");
      final boolean dataExported = exportUserBloodDonationRequest.exportUserBloodDonationRequest(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (DatabaseConnectionException | BloodDonationRequestException | ExportUserDataException e) {
      printer.printContent(e.toString());
    }
  }

  private void exportUserBloodReceiverData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportUserBloodReceiverRequestControllerDAO exportUserBloodReceiverRequest =
        new ExportUserBloodReceiverRequestController(viewAllReceiverRequest);
    printer.printContent("Preparing data export....");
    try {
      final boolean dataExported = exportUserBloodReceiverRequest.exportUserBloodReceiverRequest(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (DatabaseConnectionException | BloodReceiverRequestException | ExportUserDataException e) {
      printer.printContent(e.toString());
    }
  }

  public final void exportData() {
    printer.printScreenTitle("Export User Data");
    while (true) {
      printer.printContent("1. User statistics data export.");
      printer.printContent("2. User blood donation requests data export.");
      printer.printContent("3. User blood receive requests data export.");
      printer.printContent("4. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          exportUserStatisticsData();
          break;
        case "2":
          exportUserBloodDonationData();
          break;
        case "3":
          exportUserBloodReceiverData();
          break;
        case "4":
          return;
        default:
          break;
      }
    }
  }
}
