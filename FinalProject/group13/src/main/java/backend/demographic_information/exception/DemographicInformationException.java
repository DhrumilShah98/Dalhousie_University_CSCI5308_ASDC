package backend.demographic_information.exception;

/**
 * {@code DemographicInformationException} class is thrown when any error
 * occurs during the fetching the information of demographic information.
 * This class extends the {@code Exception} class.
 * Hence {@code DemographicInformationException} is a checked exception.
 */
public final class DemographicInformationException extends Throwable {

  private final String errorMessage;

  /**
   * Constructs this {@code DemographicInformationException} instance.
   *
   * @param errorMessage error message generated.
   */
  public DemographicInformationException(String errorMessage) {
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
    return "DemographicInformationException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
