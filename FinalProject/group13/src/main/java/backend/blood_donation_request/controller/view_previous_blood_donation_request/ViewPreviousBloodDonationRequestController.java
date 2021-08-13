package backend.blood_donation_request.controller.view_previous_blood_donation_request;

import backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ViewPreviousBloodDonationRequestController} implements the
 * {@code ViewPreviousBloodDonationRequestControllerDAO} to provide a concrete
 * implementation for viewing all previous blood donation request for this user.
 */
public final class ViewPreviousBloodDonationRequestController
    implements ViewPreviousBloodDonationRequestControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // View previous blood donation request query builder instance.
  private final ViewPreviousBloodDonationRequestQueryBuilderDAO
      viewPreviousBloodDonationRequestQueryBuilderDAO;

  /**
   * @param databaseConnectionDAO                           database
   *                                                        connection
   *                                                        instance.
   * @param viewPreviousBloodDonationRequestQueryBuilderDAO view previous
   *                                                        blood donation
   *                                                        request query
   *                                                        builder instance.
   */
  public ViewPreviousBloodDonationRequestController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final ViewPreviousBloodDonationRequestQueryBuilderDAO viewPreviousBloodDonationRequestQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.viewPreviousBloodDonationRequestQueryBuilderDAO =
        viewPreviousBloodDonationRequestQueryBuilderDAO;
  }

  /**
   * @param userId                 unique id of this user.
   * @param bloodDonationResultSet blood donation result set.
   *
   * @return List<BloodDonationRequest> of previous blood donation requests
   * of this user.
   *
   * @throws SQLException if an error occurs while viewing previous blood
   *                      donation requests.
   */
  private List<BloodDonationRequest> prepareBloodDonationRequestList(
      final int userId,
      final ResultSet bloodDonationResultSet)
      throws SQLException {
    final List<BloodDonationRequest> bloodDonationRequestList =
        new ArrayList<>();
    while (bloodDonationResultSet.next()) {
      final int requestId =
          bloodDonationResultSet.getInt(BloodDonationRequestDatabaseConstant.BLOOD_DONATION_REQUEST_ID_COLUMN);
      final String requestDate =
          bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.REQUEST_DATE_COLUMN);
      final String statusChangeDate =
          bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.STATUS_CHANGE_DATE_COLUMN);
      final String status =
          bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.STATUS_COLUMN);
      final String certificateId =
          bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.CERTIFICATE_ID_COLUMN);

      final BloodDonationRequest bloodDonationRequest =
          new BloodDonationRequest(
              requestId,
              userId,
              requestDate,
              statusChangeDate,
              status,
              certificateId);
      bloodDonationRequestList.add(bloodDonationRequest);
    }
    return bloodDonationRequestList;
  }

  /**
   * @param userId unique id of this user.
   *
   * @return List<BloodDonationRequest> of previous blood donation request of
   * this user.
   *
   * @throws BloodDonationRequestException if an error occurs while viewing
   *                                       previous blood donation requests of
   *                                       this user.
   * @throws DatabaseConnectionException   if an error occurs while connecting
   *                                       to database.
   */
  @Override
  public List<BloodDonationRequest> viewBloodDonationRequest(final int userId)
      throws BloodDonationRequestException,
      DatabaseConnectionException {

    if (userId < 0) {
      throw new BloodDonationRequestException("Invalid user id");
    }

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet bloodDonationResultSet =
             statement.executeQuery(viewPreviousBloodDonationRequestQueryBuilderDAO.selectPreviousBloodDonationQuery(userId))) {

      return prepareBloodDonationRequestList(userId, bloodDonationResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
