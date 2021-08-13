package backend.authentication.user.exception;

/**
 * {@code UserAuthenticationException} class is thrown when any error
 * occurs during the user authentication.
 * This class extends the {@code Exception} class.
 * Hence {@code UserAuthenticationException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserAuthenticationException extends Exception {

  // Error message string.
  private final String errorMessage;

  /**
   * Constructs this {@code UserAuthenticationException} instance.
   *
   * @param errorMessage error message generated.
   */
  public UserAuthenticationException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  /**
   * Gets the error message of this exception.
   *
   * @return error message of this exception.
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
    return "UserAuthenticationException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}