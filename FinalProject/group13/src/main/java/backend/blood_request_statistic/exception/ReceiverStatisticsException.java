package backend.blood_request_statistic.exception;

/**
 * {@code ReceiverStatisticsException} class is thrown when any error
 * occurs during the blood receiver request.
 * This class extends the {@code Exception} class.
 * Hence {@code ReceiverStatisticsException} is a checked exception.
 */
public class ReceiverStatisticsException extends Exception {

  private final String errorMessage;

  /**
   * Constructs this {@code ReceiverStatisticsException} instance.
   *
   * @param errorMessage error message generated.
   */
  public ReceiverStatisticsException(String errorMessage) {
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
    return "ReceiverStatisticsException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}