package backend.admin_blood_donation_request.database.update_blood_donation_status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static backend.admin_blood_donation_request.database.BloodDonationRequestStatusDatabaseConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import backend.admin_blood_donation_request.database.update_blood_donation_status.UpdateBloodDonationStatusQueryBuilder;

import java.time.LocalDate;

class UpdateBloodDonationStatusQueryBuilderTest {

  @Test
  void fulfilledBloodDonationRequestStatus() {
    final int requestID = 5;
    final String STATUS_REQUEST = "request";
    final String STATUS_FULFILLED = "fulfilled";
    final UpdateBloodDonationStatusQueryBuilder updateBloodDonationStatusQueryBuilderTest = UpdateBloodDonationStatusQueryBuilder.getInstance();
    final String actualUpdateQuery =
        updateBloodDonationStatusQueryBuilderTest.fulfilledBloodDonationRequestStatus(requestID);
    final String expectedUpdateQuery = "UPDATE " +
        BLOOD_DONATION_REQUEST_TABLE + " SET " +
        STATUS_COLUMN + " = \"" + STATUS_FULFILLED + "\", " +
        STATUS_CHANGE_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_DONATION_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
        Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery, "Incorrect Update Blood Donation Query");
  }


  @Test
  void rejectedBloodDonationRequestStatus() {
    final int requestID = 5;
    final String STATUS_REQUEST = "request";
    final String STATUS_REJECTED = "fulfilled";
    final UpdateBloodDonationStatusQueryBuilder updateBloodDonationStatusQueryBuilderTest = UpdateBloodDonationStatusQueryBuilder.getInstance();
    final String actualUpdateQuery =
        updateBloodDonationStatusQueryBuilderTest.fulfilledBloodDonationRequestStatus(requestID);
    final String expectedUpdateQuery = "UPDATE " +
        BLOOD_DONATION_REQUEST_TABLE + " SET " +
        STATUS_COLUMN + " = \"" + STATUS_REJECTED + "\", " +
        STATUS_CHANGE_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_DONATION_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
    Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery, "Incorrect Update Blood Donation Query");
  }
}