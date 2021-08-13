package demographic_information.exception;

import backend.demographic_information.exception.DemographicInformationException;
import backend.user_profile_management.exception.UserProfileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DemographicInformationExceptionTest {
  @Test
  @DisplayName("Demographic Information Exception class constructor tested")
  public void demographicInformationExceptionTest() {
    DemographicInformationException demographicInformationException = new DemographicInformationException("Testing");
    Assertions.assertEquals("DemographicInformationException{errorMessage='Testing'}", demographicInformationException.toString());
  }

  @Test
  @DisplayName("Demographic Information Exception class getErrorMessage method tested")
  public void getErrorMessageTest() {
    DemographicInformationException demographicInformationException = new DemographicInformationException("Testing");
    Assertions.assertEquals("Testing", demographicInformationException.getErrorMessage());
  }
}
