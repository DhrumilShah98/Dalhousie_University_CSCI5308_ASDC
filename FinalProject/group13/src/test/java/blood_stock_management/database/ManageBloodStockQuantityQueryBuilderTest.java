package blood_stock_management.database;

import backend.blood_stock_management.database.blood_stock_quantity.ManageBloodStockQuantityQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ManageBloodStockQuantityQueryBuilder class test suite")
public class ManageBloodStockQuantityQueryBuilderTest {

    @Test
    @DisplayName("getBloodStockQuantityForBloodTypeQuery() test")
    public void getBloodStockQuantityForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final ManageBloodStockQuantityQueryBuilder manageBloodStockQuantityQueryBuilder = ManageBloodStockQuantityQueryBuilder.getInstance();
        final String actualGetBloodStockQuantityForBloodTypeQuery = manageBloodStockQuantityQueryBuilder.getBloodStockQuantityForBloodTypeQuery(bloodGroup, bloodBankId);
        final String expectedGetBloodStockQuantityForBloodTypeQuery = "SELECT quantity FROM blood_stock WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedGetBloodStockQuantityForBloodTypeQuery, actualGetBloodStockQuantityForBloodTypeQuery, "Incorrect method implementation: getBloodStockQuantityForBloodTypeQuery()");
    }

    @Test
    @DisplayName("updateBloodStockQuantityForBloodTypeQuery() test")
    public void updateBloodStockQuantityForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final int newBloodQuantity = 211;
        final ManageBloodStockQuantityQueryBuilder manageBloodStockQuantityQueryBuilder = ManageBloodStockQuantityQueryBuilder.getInstance();
        final String actualUpdateBloodStockQuantityForBloodTypeQuery = manageBloodStockQuantityQueryBuilder.updateBloodStockQuantityForBloodTypeQuery(newBloodQuantity, bloodGroup, bloodBankId);
        final String expectedUpdateBloodStockQuantityForBloodTypeQuery = "UPDATE blood_stock SET quantity = 211 WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedUpdateBloodStockQuantityForBloodTypeQuery, actualUpdateBloodStockQuantityForBloodTypeQuery, "Incorrect method implementation: updateBloodStockQuantityForBloodTypeQuery()");
    }
}
