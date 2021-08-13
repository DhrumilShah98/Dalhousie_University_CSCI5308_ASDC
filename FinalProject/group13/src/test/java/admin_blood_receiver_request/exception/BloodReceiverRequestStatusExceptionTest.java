package admin_blood_receiver_request.exception;

import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import backend.admin_blood_receiver_request.exception.BloodReceiverRequestStatusException;

class BloodReceiverRequestStatusExceptionTest {

  @Test
  void getErrorMessage() {
    final BloodReceiverRequestStatusException bloodReceiverRequestStatusException = new BloodReceiverRequestStatusException("status didn't get updated");
    Assertions.assertEquals("status didn't get updated",
        bloodReceiverRequestStatusException.getErrorMessage());
  }

  @Test
  void testToString() {
    final BloodReceiverRequestStatusException bloodReceiverRequestStatusException =
        new BloodReceiverRequestStatusException("New blood donation request cannot be created.");
    Assertions.assertEquals("BloodDonationRequestException{errorMessage='New " +
        "blood donation request cannot be created.'}", bloodReceiverRequestStatusException.toString());
  }
}