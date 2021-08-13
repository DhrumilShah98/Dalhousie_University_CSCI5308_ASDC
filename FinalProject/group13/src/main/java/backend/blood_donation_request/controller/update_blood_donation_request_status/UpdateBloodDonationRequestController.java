package backend.blood_donation_request.controller.update_blood_donation_request_status;

import backend.blood_donation_request.database.update_blood_donation_request_status.UpdateBloodDonationRequestStatusQueryBuilderDAO;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code UpdateBloodDonationRequestController} implements the
 * {@code UpdateBloodDonationRequestControllerDAO} to provide a concrete
 * implementation for updating the status of a blood donation request.
 */
public final class UpdateBloodDonationRequestController implements UpdateBloodDonationRequestControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Update blood donation request status query builder instance.
  private final UpdateBloodDonationRequestStatusQueryBuilderDAO
      updateBloodDonationRequestStatusQueryBuilderDAO;

  /**
   * Constructs this {@code UpdateBloodDonationRequestController} instance.
   *
   * @param databaseConnectionDAO                           database connection
   *                                                        instance.
   * @param updateBloodDonationRequestStatusQueryBuilderDAO update blood
   *                                                        donation request
   *                                                        status query
   *                                                        builder instance.
   */
  public UpdateBloodDonationRequestController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final UpdateBloodDonationRequestStatusQueryBuilderDAO updateBloodDonationRequestStatusQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.updateBloodDonationRequestStatusQueryBuilderDAO =
        updateBloodDonationRequestStatusQueryBuilderDAO;
  }

  /**
   * @param userId unique id of this user.
   *
   * @return boolean true/false if request is sent for fulfillment.
   *
   * @throws BloodDonationRequestException if any error occurs while updating
   *                                       blood donation request status
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to database
   */
  @Override
  public boolean requestFulfilment(final int userId)
      throws BloodDonationRequestException,
      DatabaseConnectionException {

    if (userId < 0) {
      throw new BloodDonationRequestException("Invalid user id");
    }
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      final int bloodDonationRowUpdated =
          statement.executeUpdate(updateBloodDonationRequestStatusQueryBuilderDAO.updateBloodDonationRequestStatusQuery(userId));
      if (bloodDonationRowUpdated > 0) {
        return true;
      } else {
        throw new BloodDonationRequestException("Error while updating the blood donation request status");
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
