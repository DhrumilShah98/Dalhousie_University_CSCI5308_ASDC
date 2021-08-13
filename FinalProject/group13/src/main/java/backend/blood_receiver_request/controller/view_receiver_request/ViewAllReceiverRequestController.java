package backend.blood_receiver_request.controller.view_receiver_request;


import backend.blood_receiver_request.database.BloodReceiverRequestDatabaseConstant;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilderDAO;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ViewAllReceiverRequestController} implements the
 * {@code ViewAllReceiverRequestControllerDAO} to provide a concrete
 * implementation for the view all blood receiver requests.
 */
public final class ViewAllReceiverRequestController implements ViewAllReceiverRequestControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood receiver request query builder instance.
  private final BloodReceiverRequestQueryBuilderDAO
      bloodReceiverRequestQueryBuilderDAO;

  /**
   * Constructs this {@code ViewAllReceiverRequestController} instance.
   *
   * @param databaseConnectionDAO               database connection instance.
   * @param bloodReceiverRequestQueryBuilderDAO blood receiver request
   *                                            query builder instance.
   */
  public ViewAllReceiverRequestController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodReceiverRequestQueryBuilderDAO bloodReceiverRequestQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.bloodReceiverRequestQueryBuilderDAO = bloodReceiverRequestQueryBuilderDAO;
  }

  /**
   * @param userId                 user id to view all receive request of that user.
   * @param bloodReceiverResultSet blood receiver result set to make a list
   *
   * @return blood receiver request list
   *
   * @throws SQLException if any error occurs while
   *                      performing query to the database.
   */
  public List<BloodReceiverRequest> prepareBloodReceiverRequestList(final int userId, final ResultSet bloodReceiverResultSet) throws SQLException {
    final List<BloodReceiverRequest> bloodReceiverRequestList = new ArrayList<>();
    while (bloodReceiverResultSet.next()) {
      bloodReceiverResultSet.getInt(BloodReceiverRequestDatabaseConstant.REQUEST_ID_COLUMN);
      final String bloodGroup = bloodReceiverResultSet.getString(BloodReceiverRequestDatabaseConstant.BLOOD_GROUP_COLUMN);
      final String quantity = bloodReceiverResultSet.getString(BloodReceiverRequestDatabaseConstant.QUANTITY_COLUMN);
      final String dateRequest = bloodReceiverResultSet.getString(BloodReceiverRequestDatabaseConstant.DATE_REQUEST_COLUMN);
      final String status = bloodReceiverResultSet.getString(BloodReceiverRequestDatabaseConstant.STATUS_COLUMN);
      final String statusChangeDate = bloodReceiverResultSet.getString(BloodReceiverRequestDatabaseConstant.STATUS_CHANGED_COLUMN);

      final BloodReceiverRequest bloodReceiverRequest = new BloodReceiverRequest(userId, bloodGroup, quantity, dateRequest, status, statusChangeDate);
      bloodReceiverRequestList.add(bloodReceiverRequest);
    }
    return bloodReceiverRequestList;
  }

  /**
   * Performs view all blood receiver requests.
   *
   * @param userId user id of the blood receiver request.
   *
   * @return all blood receiver request made by the user.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request fulfillment.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  @Override
  public List<BloodReceiverRequest> viewBloodReceiverRequest(int userId) throws BloodReceiverRequestException, DatabaseConnectionException {

    if (userId < 0) {
      throw new BloodReceiverRequestException("Invalid user id");
    }
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet bloodReceiverResultSet = statement.executeQuery(bloodReceiverRequestQueryBuilderDAO.selectBloodReceiverQuery(userId))) {
      return prepareBloodReceiverRequestList(userId, bloodReceiverResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
