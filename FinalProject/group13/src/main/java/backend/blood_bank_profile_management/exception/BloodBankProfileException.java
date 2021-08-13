package backend.blood_bank_profile_management.exception;

/**
 * {@code BloodBankProfileException} class is thrown when any error
 * occurs during the blood bank profile management.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankProfileException} is a checked exception.
 */
public class BloodBankProfileException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code BloodBankProfileException} instance.
   *
   * @param errorMessage error message generated
   */
  public BloodBankProfileException(final String errorMessage) {
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
    return "BloodBankProfileException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
