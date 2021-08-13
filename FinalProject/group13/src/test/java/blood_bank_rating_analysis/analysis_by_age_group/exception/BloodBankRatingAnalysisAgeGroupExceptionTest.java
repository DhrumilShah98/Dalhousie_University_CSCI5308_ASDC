package blood_bank_rating_analysis.analysis_by_age_group.exception;

import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankRatingAnalysisAgeGroupException class test suite")
public class BloodBankRatingAnalysisAgeGroupExceptionTest {
    @Test
    @DisplayName("BloodBankRatingAnalysisAgeGroupException getErrorMessage() test")
    public void getErrorMessageTest() {
        final BloodBankRatingAnalysisAgeGroupException exception = new BloodBankRatingAnalysisAgeGroupException("Error occurred during analysis.");
        Assertions.assertEquals("Error occurred during analysis.", exception.getErrorMessage());
    }

    @Test
    @DisplayName("BloodBankRatingAnalysisAgeGroupException toString() test")
    public void toStringTest() {
        final BloodBankRatingAnalysisAgeGroupException exception = new BloodBankRatingAnalysisAgeGroupException("Error occurred during analysis.");
        Assertions.assertEquals("BloodBankRatingAnalysisAgeGroupException{errorMessage='Error occurred during analysis.'}", exception.toString());
    }
}

