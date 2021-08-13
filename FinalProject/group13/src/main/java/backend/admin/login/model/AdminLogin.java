package backend.admin.login.model;

/**
 * {@code AdminLogin} class stores information related to admin of the
 * application
 * this class acts as a single object/entry/model.
 */
public class AdminLogin {

    //Unique Id of admin
    private final int adminId;

    //Email of admin
    private final String email;

    //Password of admin
    private final String password;

    /**
     *
     * @param adminId   Unique Id of admin
     * @param email     Email of admin
     * @param password  Password of admin
     */
    public AdminLogin(  final int adminId,
                        final String email,
                        final String password) {
        this.adminId = adminId;
        this.email = email;
        this.password = password;
    }

    /**
     *
     * @param email     Email of admin
     * @param password  Password of admin
     */
    public AdminLogin(String email, String password) {
        this(-1, email, password);
    }

    /**
     * Gets an email of the admin
     *
     * @return an email of the admin
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password of the admin
     *
     * @return password of the admin
     */
    public String getPassword() {
        return password;
    }
}