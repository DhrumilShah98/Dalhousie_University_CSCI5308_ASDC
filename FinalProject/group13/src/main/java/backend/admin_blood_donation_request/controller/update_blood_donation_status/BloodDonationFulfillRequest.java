package backend.admin_blood_donation_request.controller.update_blood_donation_status;

import backend.admin_blood_donation_request.database.update_blood_donation_status.UpdateBloodDonationStatusQueryBuilderDAO;
import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code BloodDonationFulfillRequest} implements the
 * {@code BloodDonationFulfillRequestDAO} to provide a concrete
 * implementation for the status update of blood donor
 */
public class BloodDonationFulfillRequest implements BloodDonationFulfillRequestDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Update query of updating status with builder instance.
  private final UpdateBloodDonationStatusQueryBuilderDAO updateBloodDonationStatusQueryBuilderDAO;

  /**
   * Constructs this {@code BloodDonationFulfillRequest} instance.
   *
   * @param databaseConnectionDAO database connection instance.
   * @param updateBloodDonationStatusQueryBuilderDAO Select query with update
   *                                                  status with query builder
   *                                                  instance.
   */
  public BloodDonationFulfillRequest(DatabaseConnectionDAO databaseConnectionDAO, UpdateBloodDonationStatusQueryBuilderDAO updateBloodDonationStatusQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.updateBloodDonationStatusQueryBuilderDAO = updateBloodDonationStatusQueryBuilderDAO;
  }

  /**
   * Performs update status of blood donor.
   *
   * @param requestID request id of blood donor.
   *
   * @param status status of blood donor.
   *
   * @return blood donation status instance of blood donors status.
   *
   * @throws BloodDonationRequestStatusException when error occurs while
   * updating status of blood donor.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  @Override
  public boolean showBloodDonationFulfillRequest(int requestID, String status) throws BloodDonationRequestStatusException, DatabaseConnectionException {
    if (requestID < 0) {
      throw new BloodDonationRequestStatusException("Invalid request id");
    }

    if (status.trim().isEmpty()) {
      throw new BloodDonationRequestStatusException("Invalid status value passed");
    }

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      // Status will be updated to fulfilled
      if (status.equals("fulfilled")) {
        final int bloodDonationRowUpdated = statement.executeUpdate(updateBloodDonationStatusQueryBuilderDAO.fulfilledBloodDonationRequestStatus(requestID));

        if (bloodDonationRowUpdated > 0) {
          return true;
        } else {
          throw new BloodDonationRequestStatusException("Error while updating the blood donation request status");
        }
      } else {
        // Status will be updated to rejected
        final int bloodDonationRowUpdated = statement.executeUpdate(updateBloodDonationStatusQueryBuilderDAO.rejectedBloodDonationRequestStatus(requestID));

        if (bloodDonationRowUpdated > 0) {
          return true;
        } else {
          throw new BloodDonationRequestStatusException("Error while updating the blood donation request status");
        }
      }
    } catch (SQLException | BloodDonationStatisticsException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}

