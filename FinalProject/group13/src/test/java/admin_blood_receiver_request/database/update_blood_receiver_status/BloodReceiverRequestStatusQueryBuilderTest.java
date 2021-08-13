package admin_blood_receiver_request.database.update_blood_receiver_status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static backend.admin_blood_receiver_request.database.BloodReceiverRequestStatusDatabaseConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import backend.admin_blood_receiver_request.database.update_blood_receiver_status.BloodReceiverRequestStatusQueryBuilder;

import java.time.LocalDate;

class BloodReceiverRequestStatusQueryBuilderTest {

  @Test
  void fulfilledBloodReceiverRequestStatus() {
    final int requestID = 5;
    final String STATUS_REQUEST = "request";
    final String STATUS_FULFILLED = "fulfilled";
    final BloodReceiverRequestStatusQueryBuilder bloodReceiverRequestStatusQueryBuilder = BloodReceiverRequestStatusQueryBuilder.getInstance();
    final String actualUpdateQuery =
        bloodReceiverRequestStatusQueryBuilder.fulfilledBloodReceiverRequestStatus(requestID);
    final String expectedUpdateQuery = "UPDATE " +
        BLOOD_RECEIVER_REQUEST_TABLE+ " SET " +
        STATUS_COLUMN + " = \"" + STATUS_FULFILLED + "\", " +
        STATUS_CHANGED_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_RECEIVER_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery,
        "Incorrect Update Blood receiver Query");
  }

  @Test
  void rejectedBloodReceiverRequestStatus() {
    final int requestID = 5;
    final String STATUS_REQUEST = "request";
    final String STATUS_REJECTED = "rejected";
    final BloodReceiverRequestStatusQueryBuilder bloodReceiverRequestStatusQueryBuilder = BloodReceiverRequestStatusQueryBuilder.getInstance();
    final String actualUpdateQuery =
        bloodReceiverRequestStatusQueryBuilder.rejectedBloodReceiverRequestStatus(requestID);
    final String expectedUpdateQuery = "UPDATE " +
        BLOOD_RECEIVER_REQUEST_TABLE+ " SET " +
        STATUS_COLUMN + " = \"" + STATUS_REJECTED + "\", " +
        STATUS_CHANGED_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_RECEIVER_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery,
        "Incorrect Update Blood receiver Query");

  }
}