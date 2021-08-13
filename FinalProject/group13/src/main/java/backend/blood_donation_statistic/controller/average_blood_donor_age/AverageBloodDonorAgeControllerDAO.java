package backend.blood_donation_statistic.controller.average_blood_donor_age;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code AverageBloodDonorAgeControllerDAO} provides a contract for
 * calculating average blood donor age.
 */
public interface AverageBloodDonorAgeControllerDAO {

  /**
   * @return int average age of the blood donors.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating blood donation
   *                                          statistics.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  int viewAverageBloodDonorAge()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException;
}
