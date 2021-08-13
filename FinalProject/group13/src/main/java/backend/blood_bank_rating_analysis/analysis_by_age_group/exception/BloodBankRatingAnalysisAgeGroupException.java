package backend.blood_bank_rating_analysis.analysis_by_age_group.exception;

/**
 * {@code BloodBankRatingAnalysisAgeGroupException} class is thrown when any error
 * occurs during the blood bank rating analysis by age group.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankRatingAnalysisAgeGroupException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisAgeGroupException
    extends Exception {

  // error message of this exception.
  private final String errorMessage;

  /**
   * Constructs this {@code BloodBankRatingAnalysisAgeGroupException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodBankRatingAnalysisAgeGroupException(String errorMessage) {
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
    return "BloodBankRatingAnalysisAgeGroupException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}