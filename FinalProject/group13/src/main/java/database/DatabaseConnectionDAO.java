package database;

import java.sql.Connection;

public interface DatabaseConnectionDAO {
    Connection getDatabaseConnection() throws DatabaseConnectionException;

    void clearDatabaseConnection();
}
