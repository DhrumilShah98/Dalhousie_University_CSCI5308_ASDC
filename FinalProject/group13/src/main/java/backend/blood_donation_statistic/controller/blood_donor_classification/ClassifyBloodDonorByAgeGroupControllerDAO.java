package backend.blood_donation_statistic.controller.blood_donor_classification;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

import java.util.Map;

/**
 * {@code ClassifyBloodDonorByAgeGroupControllerDAO} provides a contract for
 * classifying blood donors by age.
 */
public interface ClassifyBloodDonorByAgeGroupControllerDAO {

  /**
   * @return Map<String, Float> of blood donor age group classification.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          classifying blood donors by
   *                                          age group.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  Map<String, Float> bloodDonorAgeClassification()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException;
}
