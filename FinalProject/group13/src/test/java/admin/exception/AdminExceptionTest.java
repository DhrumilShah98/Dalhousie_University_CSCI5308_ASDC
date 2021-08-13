package admin.exception;

import backend.admin.login.exception.AdminException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AdminException class test suite")
public class AdminExceptionTest {
    @Test
    @DisplayName("getErrorMessage() test")
    public void getErrorMessageTest() {
        final AdminException adminException = new AdminException("Invalid email/password.");
        Assertions.assertEquals("Invalid email/password.", adminException.getErrorMessage());
    }

    @Test
    @DisplayName("toString() test")
    public void toStringTest() {
        final AdminException adminException = new AdminException("Invalid email/password.");
        Assertions.assertEquals("AdminException{errorMessage='Invalid email/password.'}", adminException.toString());
    }
}