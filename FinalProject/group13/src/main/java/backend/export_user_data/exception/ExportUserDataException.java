package backend.export_user_data.exception;

/**
 * {@code ExportUserDataException} class is thrown when any error
 * occurs during exporting the user data.
 * This class extends the {@code Exception} class.
 * Hence {@code ExportUserDataException} is a checked exception.
 */
public class ExportUserDataException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code ExportUserDataException} instance.
   *
   * @param errorMessage error message generated
   */
  public ExportUserDataException(String errorMessage) {
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
    return "ExportUserDataException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
