package backend.blood_donation_statistic.exception;

/**
 * {@code BloodDonationStatisticsException} class is thrown when any error
 * occurs during the blood donation statistic.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodDonationStatisticsException} is a checked exception.
 */
public class BloodDonationStatisticsException extends Exception {

  // The generated errorMessage
  private final String errorMessage;

  /**
   * Constructs this {@code BloodDonationStatisticsException} instance.
   *
   * @param errorMessage error message generated
   */
  public BloodDonationStatisticsException(String errorMessage) {
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
    return "BloodDonationStatisticsException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}