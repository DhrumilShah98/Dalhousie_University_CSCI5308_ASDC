package backend.active_blood_donor_request.exception;

/**
 * {@code ActiveBloodDonorRequestException} class is thrown when any error
 * occurs during the blood donation request.
 * This class extends the {@code Exception} class.
 * Hence {@code ActiveBloodDonorRequestException} is a checked exception.
 */
public class ActiveBloodDonorRequestException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code ActiveBloodDonorRequestException} instance.
   *
   * @param errorMessage error message generated
   */
  public ActiveBloodDonorRequestException(final String errorMessage) {
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
    return "ActiveBloodDonorRequestException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
