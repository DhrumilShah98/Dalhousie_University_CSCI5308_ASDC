package export_blood_bank_data.exception;

import backend.export_blood_bank_data.exception.ExportBloodBankDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ExportBloodBankDataException class test suite")
public class ExportBloodBankDataExceptionTest {

  @Test
  @DisplayName("Export blood bank data exception exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final ExportBloodBankDataException exportBloodBankDataException = new ExportBloodBankDataException("Failed to export blood bank data. ");
    Assertions.assertEquals("Failed to export blood bank data. ", exportBloodBankDataException.getErrorMessage());
  }

  @Test
  @DisplayName("Export blood bank data exception toString() test")
  public void toStringTest() {
    final ExportBloodBankDataException exportBloodBankDataException = new ExportBloodBankDataException("Failed to export blood bank data.");
    Assertions.assertEquals("ExportBloodBankDataException{errorMessage='Failed to export blood bank data.'}", exportBloodBankDataException.toString());
  }
}
