package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection implements DatabaseConnectionDAO {

    private static final String DATABASE_CONFIGURATION_FILE = "./databaseConfig.properties";
    private Connection connection = null;
    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() {
        //Required private constructor
    }

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    private Connection connectToDatabase() throws DatabaseConnectionException {
        try (final InputStream inputStream = new FileInputStream(DATABASE_CONFIGURATION_FILE)) {
            final Properties databaseConfigProperties = new Properties();
            databaseConfigProperties.load(inputStream);

            Class.forName(databaseConfigProperties.getProperty("mysqlJDBCDriver")).getDeclaredConstructor().newInstance();

            final String databaseType = databaseConfigProperties.getProperty("databaseType");
            final String databaseURL = databaseConfigProperties.getProperty("databaseURL") +
                    databaseConfigProperties.getProperty(databaseType + "Database");
            final String databaseUserName = databaseConfigProperties.getProperty(databaseType + "Username");
            final String databasePassword = databaseConfigProperties.getProperty(databaseType + "Password");

            return DriverManager.getConnection(databaseURL, databaseUserName, databasePassword);
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            throw new DatabaseConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public Connection getDatabaseConnection() throws DatabaseConnectionException {
        clearDatabaseConnection();
        connection = connectToDatabase();
        return connection;
    }

    @Override
    public void clearDatabaseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            connection = null;
        }
    }
}
