package blood_bank_rating_by_user.exception;

import backend.blood_bank_rating_by_user.exception.BloodBankRatingException;
import backend.blood_donation_request.exception.BloodDonationRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankRatingExceptionTest class test suite")
public class BloodBankRatingExceptionTest {

    @Test
    @DisplayName("BloodBankRatingExceptionTest getErrorMessage() test")
    public void getErrorMessageTest() {
        final BloodBankRatingException exception = new BloodBankRatingException("Testing BloodBankRatingException");
        Assertions.assertEquals("Testing BloodBankRatingException", exception.getErrorMessage());
    }

    @Test
    @DisplayName("BloodBankRatingExceptionTest toString() test")
    public void toStringTest() {
        final BloodBankRatingException exception = new BloodBankRatingException("Testing BloodBankRatingException");
        Assertions.assertEquals("BloodBankRatingException{errorMessage='Testing BloodBankRatingException'}", exception.toString());
    }
}

