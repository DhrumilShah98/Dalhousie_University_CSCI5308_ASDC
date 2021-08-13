package user_profile_management.exception;

import backend.user_profile_management.exception.UserProfileException;
//import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;
//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserProfileExceptionTest {

  @Test
  @DisplayName("User Profile Exception class constructor tested")
  public void userProfileExceptionConstructorTest() {
    UserProfileException userProfileException = new UserProfileException("Testing");
    Assertions.assertEquals("UserProfileException{errorMessage='Testing'}", userProfileException.toString());
  }

  @Test
  @DisplayName("User Profile Exception class getErrorMessage method tested")
  public void getErrorMessageTest() {
    UserProfileException userProfileException = new UserProfileException("Testing");
    Assertions.assertEquals("Testing", userProfileException.getErrorMessage());
  }
}
