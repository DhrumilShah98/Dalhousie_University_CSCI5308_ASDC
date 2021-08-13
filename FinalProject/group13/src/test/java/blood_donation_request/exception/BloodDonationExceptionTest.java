package blood_donation_request.exception;

import backend.blood_donation_request.exception.BloodDonationRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodDonationException class test suite")
public class BloodDonationExceptionTest {

  @Test
  @DisplayName("Blood donation request exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final BloodDonationRequestException bloodDonationRequestException = new BloodDonationRequestException("New blood donation request cannot be created. ");
    Assertions.assertEquals("New blood donation request cannot be created. ", bloodDonationRequestException.getErrorMessage());
  }

  @Test
  @DisplayName("Blood donation request exception toString() test")
  public void toStringTest() {
    final BloodDonationRequestException bloodDonationRequestException = new BloodDonationRequestException("New blood donation request cannot be created.");
    Assertions.assertEquals("BloodDonationRequestException{errorMessage='New blood donation request cannot be created.'}", bloodDonationRequestException.toString());
  }
}
