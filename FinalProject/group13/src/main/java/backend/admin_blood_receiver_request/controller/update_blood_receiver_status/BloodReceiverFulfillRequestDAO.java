package backend.admin_blood_receiver_request.controller.update_blood_receiver_status;

import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import backend.admin_blood_receiver_request.exception.BloodReceiverRequestStatusException;
import database.DatabaseConnectionException;

/**
 * {@code BloodReceiverFulfillRequestDAO} provides a contract for the
 * update of blood receiver status.
 */
public interface BloodReceiverFulfillRequestDAO {

    /**
     * performs update status of blood receiver.
     *
     * @param requestID request id of blood receiver.
     *
     * @param status status of blood receiver.
     *
     * @return status of blood receiver's updated instance.
     *
     * @throws BloodReceiverRequestStatusException when error occurs while
     * updating the status of blood receiver.
     *
     * @throws DatabaseConnectionException  when error occurs while connecting
     * to the database.
     */
    boolean showBloodReceiverFulfillRequest(int requestID, String status) throws
            BloodReceiverRequestStatusException, DatabaseConnectionException;
}
