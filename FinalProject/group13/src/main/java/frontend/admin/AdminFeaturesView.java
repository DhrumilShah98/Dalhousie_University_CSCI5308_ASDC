package frontend.admin;

import frontend.admin.features.blood_donation_status.AdminBloodDonationStatusView;
import frontend.admin.features.blood_receiver_status.AdminBloodReceiverStatusView;
import frontend.print.BloodBookPrinter;

import java.util.Scanner;

public class AdminFeaturesView {

  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public AdminFeaturesView(BloodBookPrinter printer, Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  public final void showAminFeatures() {
    while (true) {
      printer.printContent("1. Fulfill | Reject donors request.");
      printer.printContent("2. Fulfill | Reject receivers request.");
      printer.printContent("3. Logout.");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          final AdminBloodDonationStatusView bloodDonationStatusView =
              new AdminBloodDonationStatusView(printer, scanner);
          bloodDonationStatusView.bloodDonationStatus();
          break;
        case "2":
          final AdminBloodReceiverStatusView bloodReceiverStatusView=
                  new AdminBloodReceiverStatusView(printer, scanner);
              bloodReceiverStatusView.bloodReceiverStatus();
          break;
        case "3":
          return;
        default:
          break;
      }
    }
  }
}
