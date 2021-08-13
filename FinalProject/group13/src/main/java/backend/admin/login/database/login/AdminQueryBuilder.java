package backend.admin.login.database.login;

import static backend.admin.login.database.AdminDatabaseConstant.*;

/**
 * {@code AdminQueryBuilder} implements the
 * {@code AdminQueryBuilderDAO} to provide a concrete
 * implementation for the admin login with email.
 * This class is implemented using the Singleton Design Pattern.
 */

public class AdminQueryBuilder implements AdminQueryBuilderDAO {

    // Static instance of this class
    private static AdminQueryBuilder instance;

    /**
     * Constructs this {@code AdminQueryBuilder} instance.
     */
    private AdminQueryBuilder() {
        //Required empty constructor
    }

    /**
     * Instantiates this {@code AdminQueryBuilder} class.
     * Lazy implementation instantiation.
     *
     * @return instance of this {@code AdminQueryBuilder} class.
     */
    public static AdminQueryBuilder getInstance() {
        if (instance == null) {
            instance = new AdminQueryBuilder();
        }
        return instance;
    }

    /**
     *  Gets the query to select admin by email.
     *
     * @param email is email of the admin.
     *
     * @return string query to select admin by email.
     */
    @Override
    public String selectAdminByEmailQuery(String email) {
        return "SELECT " +
                ADMIN_ID_COLUMN + ", " +
                ADMIN_EMAIL_COLUMN + ", " +
                ADMIN_PASSWORD_COLUMN +
                " FROM " +
                ADMIN_TABLE +
                " WHERE " +
                ADMIN_EMAIL_COLUMN + "=\"" + email + "\";";
    }
}
