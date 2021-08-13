package blood_bank_rating_analysis.analysis_by_location.database;

import backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationQueryBuilder;
import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankRatingAnalysisLocationQueryBuilder class test suite")
public class BloodBankRatingAnalysisLocationQueryBuilderTest {

    @Test
    @DisplayName("BloodBankRatingAnalysisLocationQueryBuilder getBloodBankRatingsQuery() test")
    public void getBloodBankRatingsQuery() {
        final BloodBankRatingAnalysisLocationQueryBuilder queryBuilder =
                BloodBankRatingAnalysisLocationQueryBuilder.getInstance();
        final String actualBloodBankRatingAnalysisLocationQuery = queryBuilder.getBloodBankRatingsQuery();
        final String expectedBloodBankRatingAnalysisLocationQuery = "SELECT blood_bank.blood_bank_id, blood_bank.name, blood_bank.address_province, blood_bank_rating.star FROM blood_bank, blood_bank_rating WHERE blood_bank.blood_bank_id = blood_bank_rating.blood_bank_id;";
        Assertions.assertEquals(expectedBloodBankRatingAnalysisLocationQuery, actualBloodBankRatingAnalysisLocationQuery, "Incorrect method implementation: getBloodBankRatingsQuery()");
    }
}