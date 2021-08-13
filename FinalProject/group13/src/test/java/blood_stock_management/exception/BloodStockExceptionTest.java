package blood_stock_management.exception;

import backend.blood_stock_management.exception.BloodStockException;
import backend.blood_stock_management.model.BloodStock;
import backend.user_profile_management.exception.UserProfileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodStockException class test suite")
public class BloodStockExceptionTest {
    @Test
    @DisplayName("Blood stock exception getErrorMessage() test")
    public void getErrorMessageTest() {
        final BloodStockException bloodStockException = new BloodStockException("Blood stock for type A- is not sufficient.");
        Assertions.assertEquals("Blood stock for type A- is not sufficient.", bloodStockException.getErrorMessage());
    }

    @Test
    @DisplayName("Blood stock exception toString() test")
    public void toStringTest() {
        final BloodStockException bloodStockException = new BloodStockException("Invalid A+ blood type stock.");
        Assertions.assertEquals("BloodStockException{errorMessage='Invalid A+ blood type stock.'}", bloodStockException.toString());
    }
}

