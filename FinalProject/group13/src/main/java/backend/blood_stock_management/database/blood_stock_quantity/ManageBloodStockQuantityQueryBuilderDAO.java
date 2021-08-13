package backend.blood_stock_management.database.blood_stock_quantity;

/**
 * {@code ManageBloodStockQuantityQueryBuilderDAO} provides a contract for
 * handling queries related to quantity management.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockQuantityQueryBuilderDAO {

  /**
   * Gets the query to fetch blood stock quantity for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock quantity for blood type specified.
   */
  String getBloodStockQuantityForBloodTypeQuery(final String bloodGroup,
                                                final int bloodBankId);

  /**
   * Gets the update query to update blood stock quantity for blood type.
   * specified.
   *
   * @param newQuantity new blood stock quantity.
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return update query to update blood stock quantity for blood type.
   */
  String updateBloodStockQuantityForBloodTypeQuery(final int newQuantity,
                                                   final String bloodGroup,
                                                   final int bloodBankId);
}