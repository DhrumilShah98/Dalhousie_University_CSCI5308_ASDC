package backend.admin.login.exception;

/**
 * {@code AdminException} class is thrown when any errors occurs
 * during admin login
 * This class extends the {@code Exception} class.
 * Hence {@code AdminException} is a checked exception.
 */

public class AdminException extends Exception {
    private final String errorMessage;

    /**
     * Construct this {@code AdminException} instance.
     *
     * @param errorMessage generates error message.
     */
    public AdminException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message of this exception.
     *
     * @return the error message of this exception.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets the string representation of this exception.
     *
     * @return string representation of this exception.
     */
    @Override
    public String toString() {
        return "AdminException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
