package backend.blood_donation_statistic.controller.yearly_blood_donation;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code YearlyBloodDonationControllerDAO} provides a contract for
 * calculating yearly blood donations.
 */
public interface YearlyBloodDonationControllerDAO {

  /**
   * @return long number of yearly blood donations.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating number of yearly
   *                                          blood donations.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  long yearlyBloodDonations()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException;
}
