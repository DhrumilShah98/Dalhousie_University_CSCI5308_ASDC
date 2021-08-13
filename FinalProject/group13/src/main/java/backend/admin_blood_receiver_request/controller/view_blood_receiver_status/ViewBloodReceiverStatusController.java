package backend.admin_blood_receiver_request.controller.view_blood_receiver_status;

import backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant;
import backend.admin_blood_receiver_request.database.view_blood_receiver_status.ViewBloodReceiverStatusQueryBuilderDAO;
import backend.admin_blood_receiver_request.model.BloodReceiverRequestStatus;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * {@code ViewBloodReceiverStatusController} implements the
 * {@code ViewBloodReceiverStatusControllerDAO} to provide a concrete
 * implementation for the status viewing of blood receiver
 */
public class ViewBloodReceiverStatusController implements ViewBloodReceiverStatusControllerDAO {

    // Database connection instance.
    private final DatabaseConnectionDAO databaseConnectionDAO;

    // Select query of view status with builder instance.
    private  final ViewBloodReceiverStatusQueryBuilderDAO viewBloodReceiverStatusQueryBuilderDAO;

    /**
     *  Constructs this {@code ViewBloodReceiverStatusController} instance.
     *
     * @param databaseConnectionDAO database connection instance.
     * @param viewBloodReceiverStatusQueryBuilderDAO Select query with view
     *                                               status with query builder
     *                                               instance.
     */
    public ViewBloodReceiverStatusController(DatabaseConnectionDAO databaseConnectionDAO, ViewBloodReceiverStatusQueryBuilderDAO viewBloodReceiverStatusQueryBuilderDAO) {
        this.databaseConnectionDAO = databaseConnectionDAO;
        this.viewBloodReceiverStatusQueryBuilderDAO = viewBloodReceiverStatusQueryBuilderDAO;
    }

    /**
     * Preparing list to view status of blood receiver.
     *
     * @param bloodReceiverStatusResultSet result set of list of status.
     *
     * @return blood receiver status instance of blood receiver status.
     *
     * @throws SQLException if any error occurs while viewing status of blood
     * receiver.
     */
    private List<BloodReceiverRequestStatus> prepareBloodReceiverStatusList(ResultSet bloodReceiverStatusResultSet) throws SQLException{
        final List<BloodReceiverRequestStatus> bloodReceiverRequestStatusList = new ArrayList<>();
        while (bloodReceiverStatusResultSet.next()) {
            final int requestId = bloodReceiverStatusResultSet.getInt(BloodReceiverRequestStatusDatabaseConstant.BLOOD_RECEIVER_REQUEST_ID_COLUMN);
            final int userId = bloodReceiverStatusResultSet.getInt(BloodReceiverRequestStatusDatabaseConstant.USER_ID_COLUMN);
            final String requestDate = bloodReceiverStatusResultSet.getString(BloodReceiverRequestStatusDatabaseConstant.DATE_REQUEST_COLUMN);
            final String statusChangeDate = bloodReceiverStatusResultSet.getString(BloodReceiverRequestStatusDatabaseConstant.STATUS_CHANGED_DATE_COLUMN);
            final String status = bloodReceiverStatusResultSet.getString(BloodReceiverRequestStatusDatabaseConstant.STATUS_COLUMN);

            final BloodReceiverRequestStatus bloodReceiverRequestStatus =
                    new BloodReceiverRequestStatus(requestId, userId, requestDate, statusChangeDate, status);
            bloodReceiverRequestStatusList.add(bloodReceiverRequestStatus);
    }

        return bloodReceiverRequestStatusList;
}

    /**
     * Performs list to view status of blood receiver.
     *
     * @return blood receiver status instance of blood receiver status.
     *
     * @throws ReceiverStatisticsException when error occurs while
     * fetching blood receiver statistics.
     *
     * @throws DatabaseConnectionException when error occurs while connecting
     * to the database.
     */
    @Override
    public List<BloodReceiverRequestStatus> viewBloodReceiverStatus() throws ReceiverStatisticsException, DatabaseConnectionException {

        try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
             final Statement statement = connection.createStatement();
             final ResultSet bloodReceiverStatusResultSet = statement.executeQuery(viewBloodReceiverStatusQueryBuilderDAO.viewBloodReceiverStatusQuery())) {
            return prepareBloodReceiverStatusList(bloodReceiverStatusResultSet);
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e.getMessage(), e);
        }
    }

}
