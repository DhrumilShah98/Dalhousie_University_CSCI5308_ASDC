package backend.blood_bank_camp.exception;

/**
 * {@code CampRegistrationException} class is thrown when any error
 * occurs during the blood bank authentication.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankAuthenticationException} is a checked exception.
 */
public class CampRegistrationException extends Exception {

  private final String errorMessage;

  /**
   * Constructs this {@code CampRegistrationException} instance.
   *
   * @param errorMessage error message generated.
   */
  public CampRegistrationException(String errorMessage) {
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
    return "CampRegistrationException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}