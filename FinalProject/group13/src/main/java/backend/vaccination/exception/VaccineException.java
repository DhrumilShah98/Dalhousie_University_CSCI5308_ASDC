package backend.vaccination.exception;

/**
 * {@code VaccineException} class is thrown when any error
 * occurs during inserting or fetching vaccination information.
 * This class extends the {@code Exception} class.
 * Hence {@code VaccineException} is a checked exception.
 */
public final class VaccineException extends Throwable {

  private final String errorMessage;

  /**
   * Constructs this {@code VaccineException} instance.
   *
   * @param errorMessage error message generated.
   */
  public VaccineException(String errorMessage) {
    //super(errorMessage);
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
    return "VaccineException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
