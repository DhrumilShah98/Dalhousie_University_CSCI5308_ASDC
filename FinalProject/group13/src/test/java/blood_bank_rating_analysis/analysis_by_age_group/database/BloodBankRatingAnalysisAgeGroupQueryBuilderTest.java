package blood_bank_rating_analysis.analysis_by_age_group.database;

import backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankRatingAnalysisAgeGroupQueryBuilder class test suite")
public class BloodBankRatingAnalysisAgeGroupQueryBuilderTest {

    @Test
    @DisplayName("BloodBankRatingAnalysisAgeGroupQueryBuilder getBloodBankRatingByBloodBankId() test")
    public void getBloodBankRatingsQuery() {
        final int bloodBankId = 1;
        final BloodBankRatingAnalysisAgeGroupQueryBuilder queryBuilder = BloodBankRatingAnalysisAgeGroupQueryBuilder.getInstance();
        final String actualBloodBankRatingAnalysisAgeGroupQuery = queryBuilder.getBloodBankRatingByBloodBankId(bloodBankId);
        final String expectedBloodBankRatingAnalysisAgeGroupQuery = "SELECT star, age_during_rating FROM blood_bank_rating WHERE blood_bank_id = 1;";
        Assertions.assertEquals(expectedBloodBankRatingAnalysisAgeGroupQuery, actualBloodBankRatingAnalysisAgeGroupQuery, "Incorrect method implementation: getBloodBankRatingByBloodBankId()");
    }
}