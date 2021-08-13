package backend.blood_bank_rating_by_user.exception;

/**
 * {@code BloodBankRatingException} class is thrown when any error
 * occurs during the blood bank authentication.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodBankRatingException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingException
    extends Exception {

  // error message of this exception.
  private final String errorMessage;

  /**
   * Constructs this {@code BloodBankRatingException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodBankRatingException(final String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  /**
   * Gets the error message of this exception.
   *
   * @return error message of this exception.
   */
  public String getErrorMessage() {
    return this.errorMessage;
  }

  /**
   * Gets the string representation of this exception.
   *
   * @return string representation of this exception.
   */
  @Override
  public String toString() {
    return "BloodBankRatingException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}