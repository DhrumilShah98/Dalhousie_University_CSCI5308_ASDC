package blood_stock_management.database;

import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ViewBloodStockQueryBuilder class test suite")
public class ViewBloodStockQueryBuilderTest {

    @Test
    @DisplayName("selectBloodStockQuery() test")
    public void selectBloodStockQuery() {
        final int bloodBankId = 5;
        final ViewBloodStockQueryBuilder viewBloodStockQueryBuilder = ViewBloodStockQueryBuilder.getInstance();
        final String actualSelectBloodStockQuery = viewBloodStockQueryBuilder.selectBloodStockQuery(bloodBankId);
        final String expectedSelectBloodStockQuery = "SELECT blood_stock_id, blood_bank_id, blood_group, quantity, threshold, unit_price FROM blood_stock WHERE blood_bank_id = 5;";
        Assertions.assertEquals(expectedSelectBloodStockQuery, actualSelectBloodStockQuery, "Incorrect method implementation: selectBloodStockQuery()");
    }
}