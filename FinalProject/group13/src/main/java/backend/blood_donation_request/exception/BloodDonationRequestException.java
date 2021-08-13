package backend.blood_donation_request.exception;

/**
 * {@code BloodDonationRequestException} class is thrown when any error
 * occurs during the blood donation request.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodDonationRequestException} is a checked exception.
 */
public class BloodDonationRequestException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code BloodDonationRequestException} instance.
   *
   * @param errorMessage error message generated
   */
  public BloodDonationRequestException(final String errorMessage) {
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
    return "BloodDonationRequestException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}