package blood_bank_profile_management.exception;

import backend.blood_bank_profile_management.exception.BloodBankProfileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankProfileException class test suite")
public class BloodBankProfileExceptionTest {

  @Test
  @DisplayName("Blood bank profile management exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final BloodBankProfileException bloodBankProfileException = new BloodBankProfileException("Error while updating the blood bank profile details. ");
    Assertions.assertEquals("Error while updating the blood bank profile details. ", bloodBankProfileException.getErrorMessage());
  }

  @Test
  @DisplayName("Blood bank profile management exception toString() test")
  public void toStringTest() {
    final BloodBankProfileException bloodBankProfileException = new BloodBankProfileException("Error while updating the blood bank profile details.");
    Assertions.assertEquals("BloodBankProfileException{errorMessage='Error while updating the blood bank profile details.'}", bloodBankProfileException.toString());
  }
}
