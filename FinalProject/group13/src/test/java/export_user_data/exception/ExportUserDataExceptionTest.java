package export_user_data.exception;

import backend.export_user_data.exception.ExportUserDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ExportUserDataException class test suite")
public class ExportUserDataExceptionTest {

  @Test
  @DisplayName("Export user data exception exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final ExportUserDataException exportUserDataException = new ExportUserDataException("Failed to export user data. ");
    Assertions.assertEquals("Failed to export user data. ", exportUserDataException.getErrorMessage());
  }

  @Test
  @DisplayName("Export user data exception toString() test")
  public void toStringTest() {
    final ExportUserDataException exportUserDataException = new ExportUserDataException("Failed to export user data.");
    Assertions.assertEquals("ExportUserDataException{errorMessage='Failed to export user data.'}", exportUserDataException.toString());
  }
}
