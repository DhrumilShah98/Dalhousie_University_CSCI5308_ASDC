package backend.admin_blood_donation_request.controller.view_blood_donation_status;

import backend.admin_blood_donation_request.database.BloodDonationRequestStatusDatabaseConstant;
import backend.admin_blood_donation_request.database.view_blood_donation_status.ViewBloodDonationStatusQueryBuilderDAO;
import backend.admin_blood_donation_request.model.BloodDonationRequestStatus;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ViewBloodDonationStatusController} implements the
 * {@code ViewBloodDonationStatusControllerDAO} to provide a concrete
 * implementation for the status viewing of blood donor
 */
public class ViewBloodDonationStatusController implements ViewBloodDonationStatusControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Select query of view status with builder instance.
  private final ViewBloodDonationStatusQueryBuilderDAO viewBloodDonationStatusQueryBuilderDAO;

  /**
   *  Constructs this {@code ViewBloodDonationStatusController} instance.
   *
   * @param databaseConnectionDAO database connection instance.
   * @param viewBloodDonationStatusQueryBuilderDAO Select query with view
   *                                               status with query builder
   *                                               instance.
   */
  public ViewBloodDonationStatusController(DatabaseConnectionDAO databaseConnectionDAO, ViewBloodDonationStatusQueryBuilderDAO viewBloodDonationStatusQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.viewBloodDonationStatusQueryBuilderDAO = viewBloodDonationStatusQueryBuilderDAO;
  }

  /**
   * Preparing list to view status of blood donors.
   *
   * @param bloodDonationStatusResultSet result set of list of status.
   *
   * @return blood donation status instance of blood donors status.
   *
   * @throws SQLException if any error occurs while viewing status of blood
   * donor.
   */
  private List<BloodDonationRequestStatus> prepareBloodDonationStatusList(ResultSet bloodDonationStatusResultSet) throws SQLException {
    final List<BloodDonationRequestStatus> bloodDonationRequestStatusList = new ArrayList<>();
    while (bloodDonationStatusResultSet.next()) {
      final int requestId = bloodDonationStatusResultSet.getInt(BloodDonationRequestStatusDatabaseConstant.BLOOD_DONATION_REQUEST_ID_COLUMN);
      final int userId = bloodDonationStatusResultSet.getInt(BloodDonationRequestStatusDatabaseConstant.USER_ID_COLUMN);
      final String requestDate = bloodDonationStatusResultSet.getString(BloodDonationRequestStatusDatabaseConstant.REQUEST_DATE_COLUMN);
      final String statusChangeDate = bloodDonationStatusResultSet.getString(BloodDonationRequestStatusDatabaseConstant.STATUS_CHANGE_DATE_COLUMN);
      final String status = bloodDonationStatusResultSet.getString(BloodDonationRequestStatusDatabaseConstant.STATUS_COLUMN);

      final BloodDonationRequestStatus bloodDonationRequestStatus = new BloodDonationRequestStatus(
          requestId,
          userId,
          requestDate,
          statusChangeDate,
          status);
      bloodDonationRequestStatusList.add(bloodDonationRequestStatus);
    }
    return bloodDonationRequestStatusList;
  }

  /**
   * Performs list to view status of blood donors.
   *
   * @return blood donation status instance of blood donors status.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   *
   * @throws DatabaseConnectionException when error occurs while connecting
   * to the database.
   */
  @Override
  public List<BloodDonationRequestStatus> viewBloodDonationStatus() throws BloodDonationStatisticsException, DatabaseConnectionException {
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet bloodDonationStatusResultSet = statement.executeQuery(viewBloodDonationStatusQueryBuilderDAO.viewBloodDonationStatusQuery())) {
      return prepareBloodDonationStatusList(bloodDonationStatusResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
