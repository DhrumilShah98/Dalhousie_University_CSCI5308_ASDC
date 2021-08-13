package blood_bank_rating_analysis.analysis_by_location.exception;

import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import backend.blood_stock_management.exception.BloodStockException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankRatingAnalysisLocationException class test suite")
public class BloodBankRatingAnalysisLocationExceptionTest {
    @Test
    @DisplayName("BloodBankRatingAnalysisLocationException getErrorMessage() test")
    public void getErrorMessageTest() {
        final BloodBankRatingAnalysisLocationException exception = new BloodBankRatingAnalysisLocationException("Error occurred during analysis.");
        Assertions.assertEquals("Error occurred during analysis.", exception.getErrorMessage());
    }

    @Test
    @DisplayName("BloodBankRatingAnalysisLocationException toString() test")
    public void toStringTest() {
        final BloodBankRatingAnalysisLocationException exception = new BloodBankRatingAnalysisLocationException("Error occurred during analysis.");
        Assertions.assertEquals("BloodBankRatingAnalysisLocationException{errorMessage='Error occurred during analysis.'}", exception.toString());
    }
}

