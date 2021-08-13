package backend.export_user_data.controller.export_user_statistics;

import backend.blood_donation_statistic.controller.average_blood_donor_age.AverageBloodDonorAgeControllerDAO;
import backend.blood_donation_statistic.controller.blood_donor_classification.ClassifyBloodDonorByAgeGroupControllerDAO;
import backend.blood_donation_statistic.controller.highest_blood_type_donated.HighestBloodTypeDonatedControllerDAO;
import backend.blood_donation_statistic.controller.number_of_active_donor.NumberOfActiveBloodDonorControllerDAO;
import backend.blood_donation_statistic.controller.yearly_blood_donation.YearlyBloodDonationControllerDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.controller.average_age.FindAverageReceiverRequestAgeControllerDAO;
import backend.blood_request_statistic.controller.classification_on_age.CountReceiverRequestByAgeGroupControllerDAO;
import backend.blood_request_statistic.controller.highest_blood_type.FindHighestBloodTypeRequestedControllerDAO;
import backend.blood_request_statistic.controller.number_of_active_receiver.ActiveReceiverNumberControllerDAO;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * {@code ExportUserStatisticsController} implements the
 * {@code ExportUserStatisticsControllerDAO} to provide a concrete
 * implementation for exporting user statistics data.
 */
public class ExportUserStatisticsController
    implements ExportUserStatisticsControllerDAO {

  // Average blood donor age instance.
  private final AverageBloodDonorAgeControllerDAO
      averageBloodDonorAgeControllerDAO;

  // Classify blood donor by age instance.
  private final ClassifyBloodDonorByAgeGroupControllerDAO
      classifyBloodDonorByAgeGroupControllerDAO;

  // Highest blood type donated instance.
  private final HighestBloodTypeDonatedControllerDAO
      highestBloodTypeDonatedControllerDAO;

  // Number of active blood donor instance.
  private final NumberOfActiveBloodDonorControllerDAO
      numberOfActiveBloodDonorControllerDAO;

  // Yearly blood donation instance.
  private final YearlyBloodDonationControllerDAO
      yearlyBloodDonationControllerDAO;

  // Average receiver request age instance.
  private final FindAverageReceiverRequestAgeControllerDAO
      findAverageReceiverRequestAgeControllerDAO;

  // Count receiver request instance.
  private final CountReceiverRequestByAgeGroupControllerDAO
      countReceiverRequestByAgeGroupControllerDAO;

  // Highest blood type requested instance.
  private final FindHighestBloodTypeRequestedControllerDAO
      findHighestBloodTypeRequestedControllerDAO;

  // Number of active receiver instance.
  private final ActiveReceiverNumberControllerDAO
      activeReceiverNumberControllerDAO;

  /**
   * @param averageBloodDonorAgeControllerDAO         average blood donor age
   *                                                  instance.
   * @param classifyBloodDonorByAgeGroupControllerDAO classify blood donor by
   *                                                  age instance.
   * @param highestBloodTypeDonatedControllerDAO      highest blood type
   *                                                  donated instance.
   * @param numberOfActiveBloodDonorControllerDAO     number of active blood
   *                                                  donor instance.
   * @param yearlyBloodDonationControllerDAO          yearly blood donation
   *                                                  instance.
   * @param findAverageReceiverRequestAgeControllerDAO          average receiver request
   *                                                  age instance.
   * @param countReceiverRequestByAgeGroupControllerDAO         count receiver request
   *                                                  instance.
   * @param findHighestBloodTypeRequestedControllerDAO          highest blood type
   *                                                  requested instance.
   * @param activeReceiverNumberControllerDAO                   number of active
   *                                                  receiver instance.
   */
  public ExportUserStatisticsController(
      final AverageBloodDonorAgeControllerDAO
          averageBloodDonorAgeControllerDAO,
      final ClassifyBloodDonorByAgeGroupControllerDAO
          classifyBloodDonorByAgeGroupControllerDAO,
      final HighestBloodTypeDonatedControllerDAO
          highestBloodTypeDonatedControllerDAO,
      final NumberOfActiveBloodDonorControllerDAO
          numberOfActiveBloodDonorControllerDAO,
      final YearlyBloodDonationControllerDAO
          yearlyBloodDonationControllerDAO,
      final FindAverageReceiverRequestAgeControllerDAO
          findAverageReceiverRequestAgeControllerDAO,
      final CountReceiverRequestByAgeGroupControllerDAO
          countReceiverRequestByAgeGroupControllerDAO,
      final FindHighestBloodTypeRequestedControllerDAO
          findHighestBloodTypeRequestedControllerDAO,
      final ActiveReceiverNumberControllerDAO
          activeReceiverNumberControllerDAO) {
    this.averageBloodDonorAgeControllerDAO =
        averageBloodDonorAgeControllerDAO;
    this.classifyBloodDonorByAgeGroupControllerDAO =
        classifyBloodDonorByAgeGroupControllerDAO;
    this.highestBloodTypeDonatedControllerDAO =
        highestBloodTypeDonatedControllerDAO;
    this.numberOfActiveBloodDonorControllerDAO =
        numberOfActiveBloodDonorControllerDAO;
    this.yearlyBloodDonationControllerDAO =
        yearlyBloodDonationControllerDAO;
    this.findAverageReceiverRequestAgeControllerDAO =
        findAverageReceiverRequestAgeControllerDAO;
    this.countReceiverRequestByAgeGroupControllerDAO =
        countReceiverRequestByAgeGroupControllerDAO;
    this.findHighestBloodTypeRequestedControllerDAO =
        findHighestBloodTypeRequestedControllerDAO;
    this.activeReceiverNumberControllerDAO =
        activeReceiverNumberControllerDAO;
  }

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws ExportUserDataException          if any error occurs while
   *                                          exporting user data.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting
   *                                          to the database.
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          retrieving blood donation
   *                                          statistics.
   * @throws ReceiverStatisticsException      if any error occurs while
   *                                          retrieving receiver statistics.
   */
  @Override
  public boolean exportUserStatisticsData(final String fileName)
      throws ExportUserDataException,
      DatabaseConnectionException,
      BloodDonationStatisticsException,
      ReceiverStatisticsException {
    final File file = new File(fileName);
    final int averageBloodDonorAge =
        averageBloodDonorAgeControllerDAO.viewAverageBloodDonorAge();
    final Map<String, Float> bloodDonorAgeClassification =
        classifyBloodDonorByAgeGroupControllerDAO.bloodDonorAgeClassification();
    final List<String> highestBloodTypeDonated =
        highestBloodTypeDonatedControllerDAO.findHighestDonatedBloodGroup();
    final int countActiveBloodDonors =
        numberOfActiveBloodDonorControllerDAO.countActiveBloodDonor();
    final long yearlyBloodDonations =
        yearlyBloodDonationControllerDAO.yearlyBloodDonations();
    final int averageBloodReceiverAge =
        findAverageReceiverRequestAgeControllerDAO.viewReceiverAverageAge();
    final Map<String, Float> bloodReceiverAgeClassification =
        countReceiverRequestByAgeGroupControllerDAO.viewReceiverAgeClassification();
    final List<String> highestBloodTypeRequested =
        findHighestBloodTypeRequestedControllerDAO.findHighestRequestedBloodGroup();
    final int countActiveBloodReceivers =
        activeReceiverNumberControllerDAO.viewActiveReceiverNumber();

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========User Statistics========\n\n");

      fileWriter.write("The average blood donor age:\n");
      fileWriter.write(averageBloodDonorAge + "\n");

      fileWriter.write("Blood donor age classification:\n");
      for (Map.Entry<String, Float> entry : bloodDonorAgeClassification.entrySet()) {
        fileWriter.write(entry.getKey() + "  " + entry.getValue() + "\n");
      }

      fileWriter.write("List of highest blood type donated:\n");
      for (String bloodType : highestBloodTypeDonated) {
        fileWriter.write(bloodType + "\n");
      }

      fileWriter.write("Number of active blood donors:\n");
      fileWriter.write(countActiveBloodDonors + "\n");

      fileWriter.write("Number of blood donations in a year:\n");
      fileWriter.write(yearlyBloodDonations + "\n");

      fileWriter.write("The average blood receiver age:\n");
      fileWriter.write(averageBloodReceiverAge + "\n");

      fileWriter.write("Blood receiver age classification:\n");
      for (Map.Entry<String, Float> entry : bloodReceiverAgeClassification.entrySet()) {
        fileWriter.write(entry.getKey() + "  " + entry.getValue() + "\n");
      }

      fileWriter.write("List of highest blood type requested:\n");
      for (String bloodType : highestBloodTypeRequested) {
        fileWriter.write(bloodType + "\n");
      }

      fileWriter.write("Number of active blood receivers:\n");
      fileWriter.write(countActiveBloodReceivers + "\n");

      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportUserDataException(e.getMessage());
    }
  }
}
