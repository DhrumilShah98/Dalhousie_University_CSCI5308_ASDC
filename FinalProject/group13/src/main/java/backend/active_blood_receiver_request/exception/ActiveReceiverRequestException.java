package backend.active_blood_receiver_request.exception;

/**
 * {@code ActiveReceiverRequestException} class is thrown when any error
 * occurs during the blood donation request.
 * This class extends the {@code Exception} class.
 * Hence {@code ActiveReceiverRequestException} is a checked exception.
 */
public class ActiveReceiverRequestException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code ActiveReceiverRequestException} instance.
   *
   * @param errorMessage error message generated
   */
  public ActiveReceiverRequestException(String errorMessage) {
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
    return "ActiveReceiverRequestException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}