package backend.blood_donation_request.controller.new_blood_donation_request;

import backend.authentication.user.model.User;
import backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant;
import backend.blood_donation_request.database.new_blood_donation_request.NewBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilderDAO;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import backend.blood_donation_request.model.BloodDonationRequest;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

/**
 * {@code NewBloodDonationRequestController} implements the
 * {@code NewBloodDonationRequestControllerDAO} to provide a concrete
 * implementation for creating a new blood donation request.
 */
public final class NewBloodDonationRequestController
    implements NewBloodDonationRequestControllerDAO {

  // String constant for status request.
  private static final String STATUS_REQUEST = "request";

  // String constant for status active.
  private static final String STATUS_ACTIVE = "active";

  // String constant for status fulfilled.
  private static final String STATUS_FULFILLED = "fulfilled";

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // New blood bank donation request query builder instance.
  private final NewBloodDonationRequestQueryBuilderDAO
      newBloodDonationRequestQueryBuilderDAO;

  // View previous blood donation request query builder instance.
  private final ViewPreviousBloodDonationRequestQueryBuilderDAO
      viewPreviousBloodDonationRequestQueryBuilderDAO;

  /**
   * Constructs this {@code NewBloodDonationRequestController} instance.
   *
   * @param databaseConnectionDAO                           database connection
   *                                                        instance.
   * @param newBloodDonationRequestQueryBuilderDAO          new blood donation
   *                                                        request query
   *                                                        builder instance.
   * @param viewPreviousBloodDonationRequestQueryBuilderDAO view previous
   *                                                        blood donation
   *                                                        request query
   *                                                        builder instance.
   */
  public NewBloodDonationRequestController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final NewBloodDonationRequestQueryBuilderDAO newBloodDonationRequestQueryBuilderDAO,
      final ViewPreviousBloodDonationRequestQueryBuilderDAO viewPreviousBloodDonationRequestQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.newBloodDonationRequestQueryBuilderDAO =
        newBloodDonationRequestQueryBuilderDAO;
    this.viewPreviousBloodDonationRequestQueryBuilderDAO =
        viewPreviousBloodDonationRequestQueryBuilderDAO;
  }

  /**
   * @param userId user id of this user.
   *
   * @return boolean true/false if any latest blood donation request is found.
   *
   * @throws DatabaseConnectionException if any error occurs while connecting
   *                                     to database
   */
  private boolean checkLatestRequest(final int userId)
      throws DatabaseConnectionException {

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet bloodDonationResultSet =
             statement.executeQuery(viewPreviousBloodDonationRequestQueryBuilderDAO.selectPreviousBloodDonationQuery(userId))) {

      if (bloodDonationResultSet.next()) {
        final String status =
            bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.STATUS_COLUMN);
        final String statusChangeDate =
            bloodDonationResultSet.getString(BloodDonationRequestDatabaseConstant.STATUS_CHANGE_DATE_COLUMN);
        final LocalDate currentDate = LocalDate.now();
        LocalDate statusDate = LocalDate.parse(statusChangeDate);
        Period period = Period.between(statusDate, currentDate);
        int days = Math.abs(period.getDays());
        if (status.equals(STATUS_ACTIVE)) {
          return true;
        } else if (status.equals(STATUS_REQUEST)) {
          return true;
        } else
          return status.equals(STATUS_FULFILLED) && days < 60;
      } else {
        return false;
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * @param user                 user object.
   * @param bloodDonationRequest bloodDonationRequest object.
   *
   * @return boolean true/false if new blood donation request is made or not.
   *
   * @throws BloodDonationRequestException if any error occurs while creating
   *                                       a new blood donation request.
   * @throws DatabaseConnectionException   if any error occurs while connecting
   *                                       to the database.
   */
  @Override
  public boolean createNewRequest(User user,
                                  BloodDonationRequest bloodDonationRequest)
      throws BloodDonationRequestException, DatabaseConnectionException {

    // User object null.
    if (user == null) {
      throw new BloodDonationRequestException("Null user.");
    }

    // Blood donation request object null.
    if (bloodDonationRequest == null) {
      throw new BloodDonationRequestException("Null bloodDonationRequest.");
    }

    if (checkLatestRequest(user.getUserId())) {
      throw new BloodDonationRequestException("New blood donation request cannot be created.");
    } else {
      bloodDonationRequest.setUserId(user.getUserId());
      bloodDonationRequest.setRequestDate(bloodDonationRequest.getRequestDate());
      bloodDonationRequest.setStatusChangeDate(bloodDonationRequest.getStatusChangeDate());
      bloodDonationRequest.setStatus("active");
      bloodDonationRequest.setCertificateId("");

      ResultSet generatedKeysResultSet = null;
      try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
           final Statement statement = connection.createStatement()) {
        final String insertBloodDonationRequestQuery =
            newBloodDonationRequestQueryBuilderDAO.insertNewBloodDonationRequestQuery(bloodDonationRequest);
        final int requestRowInserted =
            statement.executeUpdate(insertBloodDonationRequestQuery,
                Statement.RETURN_GENERATED_KEYS);

        if (requestRowInserted > 0) {
          generatedKeysResultSet = statement.getGeneratedKeys();
          if (generatedKeysResultSet.next()) {
            final int requestId = generatedKeysResultSet.getInt(1);
          }
          generatedKeysResultSet.close();
          return true;
        }
        return false;
      } catch (SQLException e) {
        if (generatedKeysResultSet != null) {
          try {
            generatedKeysResultSet.close();
          } catch (SQLException e1) {
            throw new DatabaseConnectionException(e.getMessage(), e1);
          }
        }
        throw new DatabaseConnectionException(e.getMessage(), e);
      }
    }
  }
}
