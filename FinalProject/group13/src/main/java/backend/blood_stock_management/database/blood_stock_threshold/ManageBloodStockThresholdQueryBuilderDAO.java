package backend.blood_stock_management.database.blood_stock_threshold;

/**
 * {@code ManageBloodStockThresholdQueryBuilderDAO} provides a contract for
 * handling queries related to threshold management.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockThresholdQueryBuilderDAO {

  /**
   * Gets the query to fetch blood stock threshold for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock threshold for blood type specified.
   */
  String getBloodStockThresholdForBloodTypeQuery(final String bloodGroup,
                                                 final int bloodBankId);

  /**
   * Gets the update query to update blood stock threshold for blood type.
   * specified.
   *
   * @param newThreshold new blood stock threshold.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @return update query to update blood stock threshold for blood type.
   */
  String updateBloodStockThresholdForBloodTypeQuery(final int newThreshold,
                                                    final String bloodGroup,
                                                    final int bloodBankId);
}