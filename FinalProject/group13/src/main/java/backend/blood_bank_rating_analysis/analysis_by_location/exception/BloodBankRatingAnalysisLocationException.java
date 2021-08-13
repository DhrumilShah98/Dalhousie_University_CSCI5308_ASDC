package backend.blood_bank_rating_analysis.analysis_by_location.exception;

/**
 * {@code BloodBankRatingAnalysisLocationException} class is thrown when any error
 * occurs during the blood bank authentication.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankRatingAnalysisLocationException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisLocationException
    extends Exception {

  // error message of this exception.
  private final String errorMessage;

  /**
   * Constructs this {@code BloodBankRatingException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodBankRatingAnalysisLocationException(String errorMessage) {
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
    return "BloodBankRatingAnalysisLocationException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}