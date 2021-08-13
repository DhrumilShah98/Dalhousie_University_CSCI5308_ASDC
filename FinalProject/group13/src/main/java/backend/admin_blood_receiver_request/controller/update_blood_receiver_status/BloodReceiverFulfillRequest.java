package backend.admin_blood_receiver_request.controller.update_blood_receiver_status;

import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import backend.admin_blood_receiver_request.database.update_blood_receiver_status.BloodReceiverRequestStatusQueryBuilderDAO;
import backend.admin_blood_receiver_request.exception.BloodReceiverRequestStatusException;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code BloodReceiverFulfillRequest} implements the
 * {@code BloodReceiverFulfillRequestDAO} to provide a concrete
 * implementation for the status update of blood receiver.
 */

public class BloodReceiverFulfillRequest implements BloodReceiverFulfillRequestDAO {
    private final DatabaseConnectionDAO databaseConnectionDAO;
    private final BloodReceiverRequestStatusQueryBuilderDAO bloodReceiverRequestStatusQueryBuilderDAO;

    public BloodReceiverFulfillRequest( DatabaseConnectionDAO databaseConnectionDAO, BloodReceiverRequestStatusQueryBuilderDAO bloodReceiverRequestStatusQueryBuilderDAO) {

        // Database connection instance.
        this.databaseConnectionDAO = databaseConnectionDAO;

        // Update query of updating status with builder instance.
        this.bloodReceiverRequestStatusQueryBuilderDAO = bloodReceiverRequestStatusQueryBuilderDAO;
    }


    /**
     * Performs update status of blood receiver.
     *
     * @param requestID request id of blood receiver.
     *
     * @param status status of blood receiver.
     *
     * @return blood donation status instance of blood receiver status.
     *
     * @throws BloodReceiverRequestStatusException when error occurs while
     * updating status of blood receiver.
     *
     * @throws DatabaseConnectionException when error occurs while connecting
     * to the database.
     */
    @Override
    public boolean showBloodReceiverFulfillRequest(int requestID,String status)  throws BloodReceiverRequestStatusException, DatabaseConnectionException {
        if (requestID < 0) {
            throw new BloodReceiverRequestStatusException("Invalid request id");
        }

        if (status.trim().isEmpty()) {
            throw new BloodReceiverRequestStatusException("Invalid status value passed");
        }

        try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
             final Statement statement = connection.createStatement()) {
            // Status will be updated to fulfilled
            if (status.equals("fulfilled")) {
                final int bloodReceiverRowUpdated = statement.executeUpdate(bloodReceiverRequestStatusQueryBuilderDAO.fulfilledBloodReceiverRequestStatus(requestID));

                if (bloodReceiverRowUpdated > 0) {
                    return true;
                } else {
                    throw new BloodReceiverRequestStatusException("Error while updating the blood donation request status");
                }
            } else {
                // Status will be updated to rejected
                final int bloodReceiverRowUpdated = statement.executeUpdate(bloodReceiverRequestStatusQueryBuilderDAO.rejectedBloodReceiverRequestStatus(requestID));

                if (bloodReceiverRowUpdated > 0) {
                    return true;
                } else {
                    throw new BloodReceiverRequestStatusException("Error while updating the blood donation request status");
                }
            }
        } catch ( SQLException  | ReceiverStatisticsException e) {
            throw new DatabaseConnectionException(e.getMessage(), e);
        }
    }
    }

