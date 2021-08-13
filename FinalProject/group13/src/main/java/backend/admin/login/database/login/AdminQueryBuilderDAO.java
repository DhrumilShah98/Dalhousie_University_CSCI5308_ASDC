package backend.admin.login.database.login;

import backend.admin.login.model.AdminLogin;

/**
 * {@code AdminQueryBuilderDAO} provides a contract for the admin
 * login with email
 */

public interface AdminQueryBuilderDAO {

    /**
     * Gets the query to select admin by email.
     *
     * @param email is email of the admin
     *
     * @return String query to select admin by email.
     */
    String selectAdminByEmailQuery(String email);
}