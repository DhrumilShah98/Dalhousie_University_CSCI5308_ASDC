package backend.export_user_data.controller.export_user_statistics;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;
import backend.export_user_data.exception.ExportUserDataException;

/**
 * {@code ExportUserStatisticsControllerDAO} provides a contract for
 * exporting user statistics data.
 */
public interface ExportUserStatisticsControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws ExportUserDataException          if any error occurs while
   *                                          exporting user data.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          retrieving blood donation
   *                                          statistics.
   * @throws ReceiverStatisticsException      if any error occurs while
   *                                          retrieving receiver statistics.
   */
  boolean exportUserStatisticsData(final String fileName)
      throws ExportUserDataException,
      DatabaseConnectionException,
      BloodDonationStatisticsException,
      ReceiverStatisticsException;
}
