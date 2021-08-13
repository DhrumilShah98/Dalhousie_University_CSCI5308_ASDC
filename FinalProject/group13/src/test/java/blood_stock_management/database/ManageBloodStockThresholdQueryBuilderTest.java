package blood_stock_management.database;

import backend.blood_stock_management.database.blood_stock_threshold.ManageBloodStockThresholdQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ManageBloodStockThresholdQueryBuilder class test suite")
public class ManageBloodStockThresholdQueryBuilderTest {

    @Test
    @DisplayName("getBloodStockThresholdForBloodTypeQuery() test")
    public void getBloodStockThresholdForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final ManageBloodStockThresholdQueryBuilder manageBloodStockThresholdQueryBuilder = ManageBloodStockThresholdQueryBuilder.getInstance();
        final String actualGetBloodStockThresholdForBloodTypeQuery = manageBloodStockThresholdQueryBuilder.getBloodStockThresholdForBloodTypeQuery(bloodGroup, bloodBankId);
        final String expectedGetBloodStockThresholdForBloodTypeQuery = "SELECT threshold FROM blood_stock WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedGetBloodStockThresholdForBloodTypeQuery, actualGetBloodStockThresholdForBloodTypeQuery, "Incorrect method implementation: getBloodStockThresholdForBloodTypeQuery()");
    }

    @Test
    @DisplayName("updateBloodStockThresholdForBloodTypeQuery() test")
    public void updateBloodStockThresholdForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final int newThreshold = 50;
        final ManageBloodStockThresholdQueryBuilder manageBloodStockThresholdQueryBuilder = ManageBloodStockThresholdQueryBuilder.getInstance();
        final String actualUpdateBloodStockThresholdForBloodTypeQuery = manageBloodStockThresholdQueryBuilder.updateBloodStockThresholdForBloodTypeQuery(newThreshold, bloodGroup, bloodBankId);
        final String expectedUpdateBloodStockThresholdForBloodTypeQuery = "UPDATE blood_stock SET threshold = 50 WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedUpdateBloodStockThresholdForBloodTypeQuery, actualUpdateBloodStockThresholdForBloodTypeQuery, "Incorrect method implementation: updateBloodStockThresholdForBloodTypeQuery()");
    }
}
