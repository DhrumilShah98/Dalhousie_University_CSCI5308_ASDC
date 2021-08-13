package active_blood_donor_request.exception;

import backend.active_blood_donor_request.exception.ActiveBloodDonorRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ActiveBloodDonorRequest class test suite")
public class ActiveBloodDonorRequestExceptionTest {

  @Test
  @DisplayName("Active blood donor request exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final ActiveBloodDonorRequestException activeBloodDonorRequestException = new ActiveBloodDonorRequestException("No active blood donor requests found. ");
    Assertions.assertEquals("No active blood donor requests found. ", activeBloodDonorRequestException.getErrorMessage());
  }

  @Test
  @DisplayName("Active blood donor request exception toString() test")
  public void toStringTest() {
    final ActiveBloodDonorRequestException activeBloodDonorRequestException = new ActiveBloodDonorRequestException("No active blood donor requests found.");
    Assertions.assertEquals("ActiveBloodDonorRequestException{errorMessage='No active blood donor requests found.'}", activeBloodDonorRequestException.toString());
  }
}
