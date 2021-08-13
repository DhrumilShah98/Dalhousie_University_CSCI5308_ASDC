package blood_stock_management.database;

import backend.blood_stock_management.database.blood_stock_unit_price.ManageBloodStockUnitPriceQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ManageBloodStockUnitPriceQueryBuilder class test suite")
public class ManageBloodStockUnitPriceQueryBuilderTest {

    @Test
    @DisplayName("getBloodStockUnitPriceForBloodTypeQuery() test")
    public void getBloodStockUnitPriceForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final ManageBloodStockUnitPriceQueryBuilder manageBloodStockUnitPriceQueryBuilder = ManageBloodStockUnitPriceQueryBuilder.getInstance();
        final String actualGetBloodStockUnitPriceForBloodTypeQuery = manageBloodStockUnitPriceQueryBuilder.getBloodStockUnitPriceForBloodTypeQuery(bloodGroup, bloodBankId);
        final String expectedGetBloodStockUnitPriceForBloodTypeQuery = "SELECT unit_price FROM blood_stock WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedGetBloodStockUnitPriceForBloodTypeQuery, actualGetBloodStockUnitPriceForBloodTypeQuery, "Incorrect method implementation: getBloodStockUnitPriceForBloodTypeQuery()");
    }

    @Test
    @DisplayName("updateBloodStockUnitPriceForBloodTypeQuery() test")
    public void updateBloodStockUnitPriceForBloodTypeQuery() {
        final int bloodBankId = 5;
        final String bloodGroup = "A+";
        final double newBloodStockUnitPrice = 450.0d;
        final ManageBloodStockUnitPriceQueryBuilder manageBloodStockUnitPriceQueryBuilder = ManageBloodStockUnitPriceQueryBuilder.getInstance();
        final String actualUpdateBloodStockUnitPriceForBloodTypeQuery = manageBloodStockUnitPriceQueryBuilder.updateBloodStockUnitPriceForBloodTypeQuery(newBloodStockUnitPrice, bloodGroup, bloodBankId);
        final String expectedUpdateBloodStockUnitPriceForBloodTypeQuery = "UPDATE blood_stock SET unit_price = 450.0 WHERE blood_group = \"A+\" AND blood_bank_id = 5;";
        Assertions.assertEquals(expectedUpdateBloodStockUnitPriceForBloodTypeQuery, actualUpdateBloodStockUnitPriceForBloodTypeQuery, "Incorrect method implementation: updateBloodStockUnitPriceForBloodTypeQuery()");
    }
}