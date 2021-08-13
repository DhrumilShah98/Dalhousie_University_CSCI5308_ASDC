package blood_receiver_request.util;

import backend.blood_bank_camp.util.CampRegistrationValidation;
import backend.blood_receiver_request.util.BloodReceiverRequestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("blood receiver request validation test ")
public class BloodReceiverRequestTest {
  @Test
  @DisplayName("Validate Incorrect Blood Group name")
  public void validateIncorrectBloodGroupName() {
    final String bloodGroup1 = null;
    Assertions.assertFalse(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup1), bloodGroup1 + " blood group 1 is correct.");
    final String bloodGroup2 = "C+";
    Assertions.assertFalse(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup2), bloodGroup2 + " blood group 2 is correct.");
    final String bloodGroup3 = "+A";
    Assertions.assertFalse(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup3), bloodGroup3 + " blood group 3 is correct.");
  }

  @Test
  @DisplayName("Validate Correct Blood Group name")
  public void validateCorrectBloodGroupName() {
    final String bloodGroup1 = "B+";
    Assertions.assertTrue(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup1), bloodGroup1 + " blood group 1 is Incorrect.");
    final String bloodGroup2 = "A+";
    Assertions.assertTrue(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup2), bloodGroup2 + " blood group 2 is Incorrect.");
    final String bloodGroup3 = "AB-";
    Assertions.assertTrue(BloodReceiverRequestUtil.isBloodGroupValid(bloodGroup3), bloodGroup3 + " blood group 3 is Incorrect.");
  }

  @Test
  @DisplayName("Validate Incorrect Quantity value")
  public void validateIncorrectQuantity() {
    final String quantity1 = null;
    Assertions.assertFalse(BloodReceiverRequestUtil.isQuantityValid(quantity1), quantity1 + " quantity1 is correct.");
    final String quantity2 = "1 unit";
    Assertions.assertFalse(BloodReceiverRequestUtil.isQuantityValid(quantity2), quantity2 + " quantity2 is correct.");
    final String quantity3 = "Five";
    Assertions.assertFalse(BloodReceiverRequestUtil.isQuantityValid(quantity3), quantity3 + " quantity3 is correct.");
  }

  @Test
  @DisplayName("Validate Correct Quantity value")
  public void validateCorrectQuantity() {
    final String quantity1 = "2";
    Assertions.assertTrue(BloodReceiverRequestUtil.isQuantityValid(quantity1), quantity1 + " quantity1 is Incorrect.");
    final String quantity2 = "1";
    Assertions.assertTrue(BloodReceiverRequestUtil.isQuantityValid(quantity2), quantity2 + " quantity2 is Incorrect.");
    final String quantity3 = "5";
    Assertions.assertTrue(BloodReceiverRequestUtil.isQuantityValid(quantity3), quantity3 + " quantity3 is Incorrect.");
  }

  @Test
  @DisplayName("Validate Incorrect Status keyword")
  public void validateIncorrectStatus() {
    final String status1 = null;
    Assertions.assertFalse(BloodReceiverRequestUtil.isStatusValid(status1), status1 + " status1 is correct.");
    final String status2 = "requested";
    Assertions.assertFalse(BloodReceiverRequestUtil.isStatusValid(status2), status2 + " status2 is correct.");
    final String status3 = "canceled";
    Assertions.assertFalse(BloodReceiverRequestUtil.isStatusValid(status3), status3 + " status3 is correct.");
  }

  @Test
  @DisplayName("Validate Correct Status keyword")
  public void validateCorrectStatus() {
    final String status1 = "active";
    Assertions.assertTrue(BloodReceiverRequestUtil.isStatusValid(status1), status1 + " status1 is Incorrect.");
    final String status2 = "fulfilled";
    Assertions.assertTrue(BloodReceiverRequestUtil.isStatusValid(status2), status2 + " status2 is Incorrect.");
    final String status3 = "rejected";
    Assertions.assertTrue(BloodReceiverRequestUtil.isStatusValid(status3), status3 + " status3 is Incorrect.");
  }
}
