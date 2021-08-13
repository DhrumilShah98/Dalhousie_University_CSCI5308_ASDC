package backend.admin_blood_receiver_request.controller.view_blood_receiver_status;

import backend.admin_blood_receiver_request.model.BloodReceiverRequestStatus;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewBloodReceiverStatusControllerDAO} provides a contract for the
 * viewing of blood receiver status.
 */
public interface ViewBloodReceiverStatusControllerDAO {

   /**
    * Performs view status list of blood receiver.
    *
    * @return List of blood receiver's status instance.
    *
    * @throws ReceiverStatisticsException  when error occurs while
    * fetching blood receiver statistics.
    *
    * @throws DatabaseConnectionException  when error occurs while connecting
    * to the database.
    */
   List<BloodReceiverRequestStatus> viewBloodReceiverStatus() throws ReceiverStatisticsException, DatabaseConnectionException;
}

