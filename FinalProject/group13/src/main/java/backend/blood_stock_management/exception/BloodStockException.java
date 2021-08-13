package backend.blood_stock_management.exception;

/**
 * {@code BloodStockException} class is thrown when any error
 * occurs during the management of blood stock.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodStockException} is a checked exception.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodStockException
    extends Exception {

  // error message of this exception.
  private final String errorMessage;

  /**
   * Constructs this {@code BloodStockException} instance.
   *
   * @param errorMessage error message generated.
   */
  public BloodStockException(final String errorMessage) {
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
    return "BloodStockException{" +
        "errorMessage='" + errorMessage + '\'' +
        '}';
  }
}