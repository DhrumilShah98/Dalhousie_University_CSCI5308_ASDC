package blood_receiver_request.database;

import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilder;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("User Blood Receiver request builder test suite")
public class BloodReceiverRequestQueryBuilderTest {

  @Test
  @DisplayName("User Blood Receiver request database validation")
  public void insertBloodReceiverRequest() {
    final BloodReceiverRequest bloodReceiverRequest = new BloodReceiverRequest(3,
        "A+",
        "2",
        String.valueOf(LocalDate.now()),
        "active",
        String.valueOf(LocalDate.now()));
    final BloodReceiverRequestQueryBuilder receiverRequest = BloodReceiverRequestQueryBuilder.getInstance();
    final String actualInsertRequestQuery = BloodReceiverRequestQueryBuilder.getInstance().insertBloodReceiverRequest(bloodReceiverRequest);
    final String expectedInsertRequestQuery = "INSERT INTO blood_receiver_request(user_id, blood_group, quantity, request_date, status, status_change_date)" +
        " VALUES (\"3\", \"A+\", \"2\", \"" + String.valueOf(LocalDate.now()) + "\", \"active\", \"" + String.valueOf(LocalDate.now()) + "\");";
    Assertions.assertEquals(actualInsertRequestQuery, expectedInsertRequestQuery, "Incorrect insert user query.");
  }

  @Test
  @DisplayName("select receiver request query by user id")
  public void selectRequestByUserId() {
    final int userId = 3;
    final BloodReceiverRequestQueryBuilder receiverRequestQueryBuilder = BloodReceiverRequestQueryBuilder.getInstance();
    final String actualSelectReceiverRequestQuery = receiverRequestQueryBuilder.selectBloodReceiverQuery(userId);
    final String expectedSelectReceiverRequestQuery = "SELECT request_id, user_id, blood_group, quantity, request_date, status, status_change_date FROM blood_receiver_request WHERE user_id = 3;";
    Assertions.assertEquals(expectedSelectReceiverRequestQuery, actualSelectReceiverRequestQuery, "Incorrect select receiver request query");
  }

  @Test
  @DisplayName("Update blood donation status to Request from Active")
  public void updateRequestStatusQuery() {
    final int userId = 5;
    final String STATUS_ACTIVE = "active";
    final String STATUS_REQUEST = "request";
    final BloodReceiverRequestQueryBuilder bloodReceiverRequestQueryBuilder = BloodReceiverRequestQueryBuilder.getInstance();
    final String actualUpdateQuery = bloodReceiverRequestQueryBuilder.updateRequestStatusQuery(userId);
    final String expectedUpdateQuery = "UPDATE blood_receiver_request SET status = \"" + STATUS_REQUEST + "\", status_change_date = \"" + String.valueOf(LocalDate.now()) + "\" WHERE user_id = " + userId + " AND status = \"" + STATUS_ACTIVE + "\";";
    Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery, "Incorrect Update Blood Donation Query");
  }
}