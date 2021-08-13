package backend.authentication.blood_bank.exception;

/**
 * {@code BloodBankAuthenticationException} class is thrown when any error
 * occurs during the blood bank authentication.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankAuthenticationException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankAuthenticationException extends Exception {

  // error message.
  private final String errorMessage;

  /**
   * Constructs this {@code BloodBankAuthenticationException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodBankAuthenticationException(final String errorMessage) {
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
    return "BloodBankAuthenticationException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}