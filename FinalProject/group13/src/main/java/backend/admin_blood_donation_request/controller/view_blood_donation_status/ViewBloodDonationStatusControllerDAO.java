package backend.admin_blood_donation_request.controller.view_blood_donation_status;

import backend.admin_blood_donation_request.model.BloodDonationRequestStatus;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewBloodDonationStatusControllerDAO} provides a contract for the
 * viewing of blood donors status.
 */
public interface ViewBloodDonationStatusControllerDAO {

  /**
   * Performs view status list of blood donor.
   *
   * @return List of blood donor's status instance.
   *
   * @throws BloodDonationStatisticsException  when error occurs while
   * fetching blood donation statistics.
   *
   * @throws DatabaseConnectionException  when error occurs while connecting
   * to the database.
   */
  List<BloodDonationRequestStatus> viewBloodDonationStatus() throws BloodDonationStatisticsException, DatabaseConnectionException;
}
