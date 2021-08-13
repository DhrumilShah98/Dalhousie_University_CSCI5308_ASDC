package backend.blood_receiver_request.exception;

/**
 * {@code BloodReceiverRequestException} class is thrown when any error
 * occurs during the blood receiver request.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodReceiverRequestException} is a checked exception.
 */
public class BloodReceiverRequestException extends Exception {

  private final String errorMessage;

  /**
   * Constructs this {@code BloodReceiverRequestException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodReceiverRequestException(String errorMessage) {
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
    return "BloodReceiverRequestException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
