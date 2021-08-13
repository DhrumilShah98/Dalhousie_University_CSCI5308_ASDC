package backend.export_blood_bank_data.exception;

/**
 * {@code ExportBloodBankDataException} class is thrown when any error
 * occurs during exporting the blood bank data.
 * This class extends the {@code Exception} class.
 * Hence {@code ExportBloodBankDataException} is a checked exception.
 */
public class ExportBloodBankDataException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code ExportBloodBankDataException} instance.
   *
   * @param errorMessage error message generated
   */
  public ExportBloodBankDataException(String errorMessage) {
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
    return "ExportBloodBankDataException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
