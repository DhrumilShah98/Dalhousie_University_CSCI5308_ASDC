package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("Database connection test suite")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseConnectionTest {
    public static DatabaseConnection databaseConnection;

    @DisplayName("Database connection setup")
    @BeforeAll
    @Test
    public static void setUp() {
        databaseConnection = DatabaseConnection.getInstance();
    }

    @Test
    @DisplayName("Test database connection")
    @Order(1)
    public void getDatabaseConnection() {
        Assertions.assertDoesNotThrow(() -> databaseConnection.getDatabaseConnection(),
                "Failed to establish a connection with the database.");
    }

    @Test
    @DisplayName("Clear database connection")
    @Order(2)
    public void clearDatabaseConnection() {
        Assertions.assertDoesNotThrow(() -> databaseConnection.clearDatabaseConnection(),
                "Failed to clear the database connection.");
    }
}
