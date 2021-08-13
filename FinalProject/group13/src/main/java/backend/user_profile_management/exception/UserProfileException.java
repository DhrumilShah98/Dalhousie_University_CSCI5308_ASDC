package backend.user_profile_management.exception;

/**
 * {@code UserProfileException} class is thrown when any error
 * occurs during updating the user profile.
 * This class extends the {@code Exception} class.
 * Hence {@code UserProfileException} is a checked exception.
 */
public final class UserProfileException extends Exception {

  private final String errorMessage;

  /**
   * Constructs this {@code UserProfileException} instance.
   *
   * @param errorMessage error message generated.
   */
  public UserProfileException(String errorMessage) {
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
    return "UserProfileException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
