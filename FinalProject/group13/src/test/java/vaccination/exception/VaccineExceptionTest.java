package vaccination.exception;

import backend.user_profile_management.exception.UserProfileException;
import backend.vaccination.exception.VaccineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VaccineExceptionTest {
  @Test
  @DisplayName("Vaccination Exception class constructor tested")
  public void vaccineExceptionConstructorTest() {
    VaccineException vaccineException = new VaccineException("Testing");
    Assertions.assertEquals("VaccineException{errorMessage='Testing'}", vaccineException.toString());
  }

  @Test
  @DisplayName("Vaccine Exception class getErrorMessage method tested")
  public void getErrorMessageTest() {
    VaccineException vaccineException = new VaccineException("Testing");
    Assertions.assertEquals("Testing", vaccineException.getErrorMessage());
  }
}
