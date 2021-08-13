package backend.blood_receiver_request.controller.request_fulfilment;

import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilderDAO;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code ReceiverRequestFulfillmentController} implements the
 * {@code ReceiverRequestFulfillmentControllerDAO} to provide a concrete
 * implementation for the fulfillment of blood receiver request.
 */
public final class ReceiverRequestFulfillmentController implements ReceiverRequestFulfillmentControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood receiver request query builder instance.
  private final BloodReceiverRequestQueryBuilderDAO
      bloodReceiverRequestQueryBuilderDAO;

  /**
   * Constructs this {@code ReceiverRequestFulfillmentController} instance.
   *
   * @param databaseConnectionDAO               database connection instance.
   * @param bloodReceiverRequestQueryBuilderDAO blood receiver request
   *                                            query builder instance.
   */
  public ReceiverRequestFulfillmentController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodReceiverRequestQueryBuilderDAO bloodReceiverRequestQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.bloodReceiverRequestQueryBuilderDAO = bloodReceiverRequestQueryBuilderDAO;
  }

  /**
   * Performs blood receiver request fulfillment.
   *
   * @param userId user id of the blood receiver request.
   *
   * @return {@code true} if blood receiver request fulfilled successfully
   * otherwise {@code false}.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request fulfillment.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  @Override
  public boolean requestFulfilment(int userId) throws BloodReceiverRequestException, DatabaseConnectionException {

    if (userId < 0) {
      throw new BloodReceiverRequestException("Invalid user id");
    }
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      final int bloodReceiverRowUpdated = statement.executeUpdate(bloodReceiverRequestQueryBuilderDAO.updateRequestStatusQuery(userId));

      if (bloodReceiverRowUpdated > 0) {
        return true;
      } else {
        throw new BloodReceiverRequestException("Error while updating the blood receiver request status");
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}