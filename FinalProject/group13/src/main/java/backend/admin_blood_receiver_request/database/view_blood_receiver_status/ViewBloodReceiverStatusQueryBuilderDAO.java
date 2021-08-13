package backend.admin_blood_receiver_request.database.view_blood_receiver_status;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

/**
 * {@code ViewBloodReceiverStatusQueryBuilderDAO} provides a contract for the
 * viewing of blood receiver status.
 */
public interface ViewBloodReceiverStatusQueryBuilderDAO {

    /**
     * Gets the query to view the status of blood receiver.
     *
     * @return String query to select status of blood receiver.
     *
     * @throws DatabaseConnectionException when error occurs while connecting
     * to the database.
     *
     * @throws ReceiverStatisticsException when error occurs while
     * fetching blood receiver statistics.
     */
    String viewBloodReceiverStatusQuery() throws DatabaseConnectionException, ReceiverStatisticsException;

}
