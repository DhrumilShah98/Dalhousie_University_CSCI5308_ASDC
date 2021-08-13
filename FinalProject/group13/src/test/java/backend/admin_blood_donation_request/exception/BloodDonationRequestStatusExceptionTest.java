package backend.admin_blood_donation_request.exception;

import backend.admin_blood_receiver_request.exception.BloodReceiverRequestStatusException;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import backend.admin_blood_donation_request.exception.BloodDonationRequestStatusException;

class BloodDonationRequestStatusExceptionTest {

  @Test
  void getErrorMessage() {
    final BloodDonationRequestStatusException bloodDonationRequestStatusException = new BloodDonationRequestStatusException("status didn't get updated");
    Assertions.assertEquals("status didn't get updated" ,
        bloodDonationRequestStatusException.getErrorMessage());

  }

  @Test
  void testToString() {
    final BloodDonationRequestStatusException bloodDonationRequestException =
        new BloodDonationRequestStatusException("New blood donation request cannot be created.");
    Assertions.assertEquals("BloodDonationRequestException{errorMessage='New " +
        "blood donation request cannot be created.'}", bloodDonationRequestException.toString());
  }
}