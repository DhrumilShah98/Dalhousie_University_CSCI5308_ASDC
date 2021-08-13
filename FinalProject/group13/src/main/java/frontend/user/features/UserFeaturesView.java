package frontend.user.features;

import frontend.common.demographic_information.DemographicInformationView;
import frontend.print.BloodBookPrinter;
import frontend.user.features.all_blood_donor_request.AllActiveBloodDonorRequestView;
import frontend.user.features.all_blood_receiver_request.AllActiveBloodReceiverRequestView;
import frontend.user.features.blood_donation_request.BloodDonationRequestView;
import frontend.user.features.blood_receiver_request.BloodReceiverRequestView;
import frontend.user.features.export_user_data.ExportUserDataView;
import frontend.user.features.rate_blood_bank.RateBloodBankView;
import frontend.user.features.statistics.UserStatisticsView;
import frontend.user.features.user_profile_management.UserProfileManagementView;
import frontend.user.features.vaccination.VaccinationView;

import java.util.Scanner;

public final class UserFeaturesView {
  private final BloodBookPrinter printer;
  private final Scanner scanner;

  public UserFeaturesView(final BloodBookPrinter printer,
                          final Scanner scanner) {
    this.printer = printer;
    this.scanner = scanner;
  }

  public final void showUserFeatures() {
    printer.printScreenTitle("All User Features");
    while (true) {
      printer.printContent("1. Rate blood bank");
      printer.printContent("2. Blood donation request");
      printer.printContent("3. Blood receiver request");
      printer.printContent("4. All Blood Donor Requests");
      printer.printContent("5. All Blood Receiver Requests");
      printer.printContent("6. User Statistics");
      printer.printContent("7. User Profile Management");
      printer.printContent("8. User Vaccination Registration/View");
      printer.printContent("9. Export User Data");
      printer.printContent("10. Demographic Information");
      printer.printContent("11. Logout");
      printer.printContent("Select an option:");
      final String input = scanner.nextLine();
      switch (input) {
        case "1":
          final RateBloodBankView rateBloodBankView =
              new RateBloodBankView(printer, scanner);
          rateBloodBankView.performRating();
          break;
        case "2":
          final BloodDonationRequestView bloodDonationRequestView =
              new BloodDonationRequestView(printer, scanner);
          bloodDonationRequestView.performBloodDonationRequest();
          break;
        case "3":
          final BloodReceiverRequestView bloodReceiverRequestView =
              new BloodReceiverRequestView(printer, scanner);
          bloodReceiverRequestView.performBloodReceiveRequest();
          break;
        case "4":
          final AllActiveBloodDonorRequestView allActiveBloodDonorRequestView =
              new AllActiveBloodDonorRequestView(printer, scanner);
          allActiveBloodDonorRequestView.activeBloodDonorRequest();
          break;
        case "5":
          final AllActiveBloodReceiverRequestView allActiveBloodReceiverRequestView =
              new AllActiveBloodReceiverRequestView(printer, scanner);
          allActiveBloodReceiverRequestView.activeBloodReceiverRequest();
          break;
        case "6":
          final UserStatisticsView userStatisticsView =
              new UserStatisticsView(printer, scanner);
          userStatisticsView.fetchStatistics();
          break;
        case "7":
          final UserProfileManagementView userProfileManagementView =
              new UserProfileManagementView(printer, scanner);
          userProfileManagementView.manageMyProfile();
          break;
        case "8":
          final VaccinationView vaccinationView =
              new VaccinationView(printer, scanner);
          vaccinationView.scheduleVaccination();
          break;
        case "9":
          final ExportUserDataView exportUserDataView =
              new ExportUserDataView(printer, scanner);
          exportUserDataView.exportData();
          break;
        case "10":
          final DemographicInformationView demographicInformationView =
              new DemographicInformationView(printer, scanner);
          demographicInformationView.getDemographicInformation();
          break;
        case "11":
          return;
        default:
          break;
      }
    }
  }
}