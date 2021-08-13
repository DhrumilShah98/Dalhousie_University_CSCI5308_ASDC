package backend.admin_blood_receiver_request.database.view_blood_receiver_status;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

import static backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant.*;
import static backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant.STATUS_REQUEST;
import static backend.blood_receiver_request.database.BloodReceiverRequestDatabaseConstant.USER_ID_COLUMN;

/**
 * {@code ViewBloodReceiverStatusQueryBuilder} implements the
 * {@code ViewBloodReceiverStatusQueryBuilderDAO} to provide a concrete
 * implementation for the status viewing of blood receiver
 * This class is implemented using the Singleton Design Pattern.
 */
public class ViewBloodReceiverStatusQueryBuilder implements ViewBloodReceiverStatusQueryBuilderDAO {

    // Static instance of this class
    private static ViewBloodReceiverStatusQueryBuilder instance;

    /**
     * Constructs this {@code ViewBloodReceiverStatusQueryBuilder} instance.
     */
    private ViewBloodReceiverStatusQueryBuilder(){
        // Required empty private constructor
    }

    /**
     * Instantiates this {@code ViewBloodReceiverStatusQueryBuilder} class.
     * Lazy implementation instantiation.
     *
     * @return instance of this {@code ViewBloodReceiverStatusQueryBuilder} class.
     */
    public static ViewBloodReceiverStatusQueryBuilder getInstance(){
        if (instance == null) {
            instance = new ViewBloodReceiverStatusQueryBuilder();
        }
        return instance;
    }

    /**
     * Gets the query to view status of blood receiver.
     *
     * @return String query to select status of blood receiver.
     *
     * @throws DatabaseConnectionException when error occurs while connecting
     * to the database.
     *
     * @throws ReceiverStatisticsException when error occurs while
     * fetching blood receiver statistics.
     */
    @Override
    public String viewBloodReceiverStatusQuery() throws DatabaseConnectionException, ReceiverStatisticsException {
        return "SELECT " +
                BLOOD_RECEIVER_REQUEST_ID_COLUMN + ", " +
                USER_ID_COLUMN + ", " +
                DATE_REQUEST_COLUMN + ", " +
                STATUS_CHANGED_DATE_COLUMN + ", " +
                STATUS_COLUMN +
                " FROM " +
                BLOOD_RECEIVER_REQUEST_TABLE +
                " WHERE " +
                STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    }
}
