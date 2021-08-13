package frontend.blood_bank.features.export_data;

import backend.blood_bank_camp.controller.view_camplist.ViewCampListController;
import backend.blood_bank_camp.controller.view_camplist.ViewCampListControllerDAO;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilder;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilderDAO;
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
import backend.blood_stock_management.controller.view_blood_stock.ViewBloodStockController;
import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilder;
import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import backend.export_blood_bank_data.controller.export_blood_bank_camps_data.ExportBloodBankCampsController;
import backend.export_blood_bank_data.controller.export_blood_bank_camps_data.ExportBloodBankCampsControllerDAO;
import backend.export_blood_bank_data.controller.export_blood_bank_rating_data.ExportBloodBankRatingDataController;
import backend.export_blood_bank_data.controller.export_blood_bank_rating_data.ExportBloodBankRatingDataControllerDAO;
import database.DatabaseConnection;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.controller.export_blood_bank_stock_data.ExportBloodBankStockDataController;
import backend.export_blood_bank_data.controller.export_blood_bank_stock_data.ExportBloodBankStockDataControllerDAO;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public class ExportBloodBankDataView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;
  private final ViewBloodStockController viewBloodStockController;
  private final DatabaseConnectionDAO databaseConnection;
  private final ViewBloodStockQueryBuilderDAO viewBloodStockQueryBuilder;
  private final ViewCampListController viewCampListController;
  private final CampQueryBuilderDAO campQueryBuilder;
  private final BloodBankRatingAnalysisAgeGroupController bloodBankRatingAnalysisAgeGroupController;
  private final BloodBankRatingAnalysisLocationController bloodBankRatingAnalysisLocationController;
  private final BloodBankRatingAnalysisAgeGroupQueryBuilderDAO bloodBankRatingAnalysisAgeGroupQueryBuilder;
  private final BloodBankRatingAnalysisLocationQueryBuilderDAO bloodBankRatingAnalysisLocationQueryBuilder;

  public ExportBloodBankDataView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
    this.databaseConnection =
        DatabaseConnection.getInstance();
    this.viewBloodStockQueryBuilder =
        ViewBloodStockQueryBuilder.getInstance();
    this.campQueryBuilder =
        CampQueryBuilder.getInstance();
    this.bloodBankRatingAnalysisLocationQueryBuilder =
        BloodBankRatingAnalysisLocationQueryBuilder.getInstance();
    this.bloodBankRatingAnalysisAgeGroupQueryBuilder =
        BloodBankRatingAnalysisAgeGroupQueryBuilder.getInstance();
    this.bloodBankRatingAnalysisAgeGroupController =
        new BloodBankRatingAnalysisAgeGroupController(databaseConnection, bloodBankRatingAnalysisAgeGroupQueryBuilder);
    this.bloodBankRatingAnalysisLocationController =
        new BloodBankRatingAnalysisLocationController(databaseConnection, bloodBankRatingAnalysisLocationQueryBuilder);
    this.viewBloodStockController =
        new ViewBloodStockController(databaseConnection, viewBloodStockQueryBuilder);
    this.viewCampListController =
        new ViewCampListController(databaseConnection, campQueryBuilder);
  }

  private void exportRatingsData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportBloodBankRatingDataControllerDAO exportBloodBankRatingDataController =
        new ExportBloodBankRatingDataController(bloodBankRatingAnalysisAgeGroupController, bloodBankRatingAnalysisLocationController);
    printer.printContent("Preparing data export....");
    try {
      final boolean dataExported =
          exportBloodBankRatingDataController.exportBloodBankRatingData(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (BloodBankRatingAnalysisAgeGroupException | ExportBloodBankDataException | BloodBankRatingAnalysisLocationException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void exportCampsData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportBloodBankCampsControllerDAO exportBloodBankCampsController =
        new ExportBloodBankCampsController(viewCampListController);
    printer.printContent("Preparing data export....");
    try {
      final boolean dataExported =
          exportBloodBankCampsController.exportBloodBankCampsOrganizedData(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (ExportBloodBankDataException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  private void exportBloodStockData() {
    printer.printContent("Enter a filename.");
    final String fileName = scanner.nextLine();

    final ExportBloodBankStockDataControllerDAO exportBloodBankStockData =
        new ExportBloodBankStockDataController(viewBloodStockController);
    printer.printContent("Preparing data export....");
    try {
      final boolean dataExported = exportBloodBankStockData.exportBloodStockData(fileName);
      if (dataExported) {
        printer.printContent("Exporting data to file " + fileName);
        printer.printContent("Data exported successfully.");
      } else {
        printer.printContent("Data export failed.");
      }
    } catch (ExportBloodBankDataException | BloodStockException | DatabaseConnectionException e) {
      printer.printContent(e.toString());
    }
  }

  public final void exportBloodBankData() {
    printer.printScreenTitle("Export Blood Bank Data");
    while (true) {
      printer.printContent("1. Blood stock | inventory data export.");
      printer.printContent("2. Blood camps organized data export.");
      printer.printContent("3. Blood bank ratings data export.");
      printer.printContent("4. Exit");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          exportBloodStockData();
          break;
        case "2":
          exportCampsData();
          break;
        case "3":
          exportRatingsData();
          break;
        case "4":
          return;
        default:
          break;
      }
    }
  }
}
