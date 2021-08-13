package backend.blood_donation_statistic.controller.highest_blood_type_donated;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code HighestBloodTypeDonatedControllerDAO} provides a contract for
 * calculating highest blood type donated.
 */
public interface HighestBloodTypeDonatedControllerDAO {

  /**
   * @return List<String> of highest blood type donated.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating highest blood
   *                                          type donated.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  List<String> findHighestDonatedBloodGroup()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException;
}
