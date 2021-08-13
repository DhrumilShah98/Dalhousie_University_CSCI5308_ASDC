package backend.admin_blood_receiver_request.database.update_blood_receiver_status;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

import java.time.LocalDate;

import static backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant.*;
import static backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant.BLOOD_RECEIVER_REQUEST_ID_COLUMN;

/**
 * {@code BloodReceiverRequestStatusQueryBuilder} implements the
 * {@code BloodReceiverRequestStatusQueryBuilderDAO} to provide a concrete
 * implementation for the status update of blood receiver
 * This class is implemented using the Singleton Design Pattern.
 */
public class BloodReceiverRequestStatusQueryBuilder implements BloodReceiverRequestStatusQueryBuilderDAO {

    // Static instance of this class
    private static BloodReceiverRequestStatusQueryBuilder instance;

    /**
     *  Constructs this {@code BloodReceiverRequestStatusQueryBuilder} instance.
     */
    private BloodReceiverRequestStatusQueryBuilder(){
        // Required empty private constructor
    }

    /**
     * Instantiates this {@code BloodReceiverRequestStatusQueryBuilder} class.
     * Lazy implementation instantiation.
     *
     * @return instance of this {@code BloodReceiverRequestStatusQueryBuilder} class.
     * */
    public static BloodReceiverRequestStatusQueryBuilder getInstance(){
        if (instance == null) {
            instance = new BloodReceiverRequestStatusQueryBuilder();
        }
        return instance;

    }

    /**
     * Gets the query to update status of blood receiver.
     *
     * @param requestID request id of blood receiver.
     *
     * @return String query to update status of blood receiver to
     * fulfilled from requested
     */
    @Override
    public String fulfilledBloodReceiverRequestStatus(int requestID)  {
        return "UPDATE " +
                BLOOD_RECEIVER_REQUEST_TABLE+ " SET " +
                STATUS_COLUMN + " = \"" + STATUS_FULFILLED + "\", " +
                STATUS_CHANGED_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
                BLOOD_RECEIVER_REQUEST_ID_COLUMN + " = " + requestID +
                " AND " +
                STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    }

    /**
     * Gets the query to update status of blood receiver.
     *
     * @param requestID request id of blood receiver.
     *
     * @return String query to update status of blood receiver to
     * rejected from requested
     */
    @Override
    public String rejectedBloodReceiverRequestStatus(int requestID)  {
        return "UPDATE " +
                BLOOD_RECEIVER_REQUEST_TABLE+ " SET " +
                STATUS_COLUMN + " = \"" + STATUS_REJECTED + "\", " +
                STATUS_CHANGED_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
                BLOOD_RECEIVER_REQUEST_ID_COLUMN + " = " + requestID +
                " AND " +
                STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    }
}
