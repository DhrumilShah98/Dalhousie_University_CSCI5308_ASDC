package frontend.blood_bank.features;

import frontend.blood_bank.features.bank_profile_management.BloodBankProfileManagementView;
import frontend.blood_bank.features.blood_bank_statistics.BloodBankRatingAnalysisStatisticsView;
import frontend.blood_bank.features.blood_stock_management.BloodStockManagementView;
import frontend.blood_bank.features.blood_camp_management.BloodCampManagementView;
import frontend.blood_bank.features.export_data.ExportBloodBankDataView;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public final class BloodBankFeaturesView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public BloodBankFeaturesView(final BloodBookPrinter printer,
                               final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  public final void showBloodBankFeatures() {
    while (true) {
      printer.printContent("1. Blood Stock Management");
      printer.printContent("2. Blood Camps details");
      printer.printContent("3. Blood Bank Profile Management");
      printer.printContent("4. Blood Bank Rating Analysis");
      printer.printContent("5. Export Blood Bank Data");
      printer.printContent("6. Logout");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          final BloodStockManagementView bloodStockManagementView =
              new BloodStockManagementView(printer, scanner);
          bloodStockManagementView.manageBloodStock();
          break;
        case "2":
          final BloodCampManagementView bloodCampManagementView =
              new BloodCampManagementView(printer, scanner);
          bloodCampManagementView.manageBloodCamp();
          break;
        case "3":
          final BloodBankProfileManagementView bloodBankProfileManagementView =
              new BloodBankProfileManagementView(printer, scanner);
          bloodBankProfileManagementView.manageBloodBankProfile();
          break;
        case "4":
          final BloodBankRatingAnalysisStatisticsView
              bloodBankRatingAnalysisStatisticsView =
              new BloodBankRatingAnalysisStatisticsView(printer, scanner);
          bloodBankRatingAnalysisStatisticsView.bloodBankRatingAnalysis();
          break;
        case "5":
          final ExportBloodBankDataView exportBloodBankDataView =
              new ExportBloodBankDataView(printer, scanner);
          exportBloodBankDataView.exportBloodBankData();
          return;
        case "6":
          return;
        default:
          break;
      }
    }
  }
}