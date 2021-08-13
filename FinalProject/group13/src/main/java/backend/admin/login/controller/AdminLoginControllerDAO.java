package backend.admin.login.controller;

import backend.admin.login.exception.AdminException;
import backend.admin.login.model.AdminLogin;
import database.DatabaseConnectionException;

/**
 * {@code AdminLoginControllerDAO} provides a contract for the
 * admin login with email.
 */
public interface AdminLoginControllerDAO {

    /**
     * Performs login of this admin with email.
     *
     * @param email     admin email.
     * @param password  admin password.
     *
     * @return Logged in admin instance.
     *
     * @throws AdminException if any error occurs while admin login.
     *
     * @throws DatabaseConnectionException if any error occurs while
     * connecting to the database.
     */
    AdminLogin loginWithEmail(String email, String password) throws AdminException,
        DatabaseConnectionException;
}
