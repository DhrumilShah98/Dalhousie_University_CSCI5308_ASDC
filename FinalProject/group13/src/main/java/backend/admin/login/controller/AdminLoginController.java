package backend.admin.login.controller;

import backend.admin.login.database.AdminDatabaseConstant;
import backend.admin.login.database.login.AdminQueryBuilderDAO;
import backend.admin.login.exception.AdminException;
import backend.admin.login.model.AdminLogin;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.HashAlgorithmUtil;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * {@code AdminLoginController} implements the
 * {@code AdminLoginControllerDAO} to provide concrete information for the
 * admin login with email.
 */
public class AdminLoginController implements AdminLoginControllerDAO {

    // Database connection instance.
    private final DatabaseConnectionDAO databaseConnectionDAO;

    // Admin login with email query builder instance.
    private final AdminQueryBuilderDAO adminQueryBuilderDAO;

    /**
     * Constructs this {@code AdminLoginController} instance.
     *
     * @param databaseConnectionDAO  database connection instance.
     * @param adminQueryBuilderDAO   Admin login with admin query builder
     *                               instance.
     */

    public AdminLoginController(DatabaseConnectionDAO databaseConnectionDAO, AdminQueryBuilderDAO adminQueryBuilderDAO) {
        this.databaseConnectionDAO = databaseConnectionDAO;
        this.adminQueryBuilderDAO = adminQueryBuilderDAO;
    }

    /**
     * Validate login credentials of admin.
     *
     * @param email     email id of admin.
     * @param password  password of admin.
     *
     * @throws AdminException if any error occurs while admin login.
     */
    private void validateLoginWithEmailCredentials(String email, String password)
            throws AdminException {
        final boolean isAdminEmailValid = (email != null) &&
                (!email.trim().isEmpty()) &&
                (RegistrationValidationUtil.isEmailValid(email.trim()));
        final boolean isAdminPasswordValid = (password != null) &&
                (!password.trim().isEmpty()) &&
                (RegistrationValidationUtil.isPasswordValid(password.trim()));
        if (!isAdminEmailValid || !isAdminPasswordValid) {
            throw new AdminException("Invalid email and/or password");
        }
    }

    /**
     * Prepare admin instance after login credentials are correct.
     *
     * @param adminLoginResultSet result set of logged-in admin.
     *
     * @return admin instance of logged in admin.
     *
     * @throws SQLException if any error occurs while admin login.
     */
    private AdminLogin prepareAdminModel(ResultSet adminLoginResultSet) throws SQLException {
        AdminLogin adminLogin= null;
        final int adminId =
            adminLoginResultSet.getInt(AdminDatabaseConstant.ADMIN_ID_COLUMN);
        final String adminEmail =
            adminLoginResultSet.getString(AdminDatabaseConstant.ADMIN_EMAIL_COLUMN);
        final String adminPassword =
            adminLoginResultSet.getString(AdminDatabaseConstant.ADMIN_PASSWORD_COLUMN);

        adminLogin = new AdminLogin(
            adminId,
            adminEmail,
            adminPassword
        );

        return adminLogin;
    }

    /**
     * Performs login of admin with email.
     *
     * @param email     admin email.
     * @param password  admin password.
     *
     * @return logged in admin instance.
     *
     * @throws AdminException if any error occurs while admin login.
     *
     * @throws DatabaseConnectionException if any error occurs while
     * connecting to database.
     */
    @Override
    public AdminLogin loginWithEmail(String email, String password) throws AdminException, DatabaseConnectionException {

        //  Validates login credentials
        validateLoginWithEmailCredentials(email, password);

        // Perform login
        try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
             final Statement statement = connection.createStatement();
             final ResultSet adminLoginResultSet = statement.executeQuery(adminQueryBuilderDAO.selectAdminByEmailQuery(email))) {

            if (adminLoginResultSet == null) {
                throw new AdminException("Invalid email and/or password");
            }

            if (adminLoginResultSet.next()) {
                final boolean isPasswordValid = HashAlgorithmUtil.validateSHA256Hash(password, adminLoginResultSet.getString(AdminDatabaseConstant.ADMIN_PASSWORD_COLUMN));
                if (!isPasswordValid) {
                    throw new AdminException("Invalid email and/or password");
                }
                return prepareAdminModel(adminLoginResultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e.getMessage(), e);
        }
    }
}