package backend.blood_stock_management.model;

/**
 * {@code BloodStock} class stores the information related to blood stock
 * This class acts as a single object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodStock {

  // Blood stock unique id of blood group type.
  private final int bloodStockId;

  // Blood bank unique id.
  private final int bloodBankId;

  // Blood group type.
  private final String bloodGroup;

  // Blood group quantity.
  private final int quantity;

  // Blood group threshold.
  private final int threshold;

  // Blood group unit price.
  private final double unitPrice;

  /**
   * Constructs this {@code BloodStock} instance.
   *
   * @param bloodStockId blood stock unique id of blood group type.
   * @param bloodBankId  blood bank unique id.
   * @param bloodGroup   blood group type.
   * @param quantity     blood group quantity.
   * @param threshold    blood group threshold.
   * @param unitPrice    blood group unit price.
   */
  public BloodStock(final int bloodStockId,
                    final int bloodBankId,
                    final String bloodGroup,
                    final int quantity,
                    final int threshold,
                    final double unitPrice) {
    this.bloodStockId = bloodStockId;
    this.bloodBankId = bloodBankId;
    this.bloodGroup = bloodGroup;
    this.quantity = quantity;
    this.threshold = threshold;
    this.unitPrice = unitPrice;
  }

  /**
   * Gets the unique id of this blood stock of blood group type.
   *
   * @return unique id of this blood stock.
   */
  public int getBloodStockId() {
    return this.bloodStockId;
  }

  /**
   * Gets the blood bank id related to this blood stock.
   *
   * @return blood bank id related to this blood stock.
   */
  public int getBloodBankId() {
    return this.bloodBankId;
  }

  /**
   * Gets the blood group type.
   *
   * @return blood group type.
   */
  public String getBloodGroup() {
    return this.bloodGroup;
  }

  /**
   * Gets the quantity of this blood group type.
   *
   * @return quantity of this blood group type.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Gets the threshold of this blood group type.
   *
   * @return threshold of this blood group type.
   */
  public int getThreshold() {
    return this.threshold;
  }

  /**
   * Gets the unit price of this blood group type.
   *
   * @return unit price of this blood group type.
   */
  public double getUnitPrice() {
    return this.unitPrice;
  }
}