package backend.blood_donation_statistic.controller.number_of_active_donor;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code NumberOfActiveBloodDonorControllerDAO} provides a contract for
 * viewing number of active blood donors
 */
public interface NumberOfActiveBloodDonorControllerDAO {

  /**
   * @return int of the count of active blood donors.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating the number of
   *                                          active blood donors.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  int countActiveBloodDonor()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException;
}
