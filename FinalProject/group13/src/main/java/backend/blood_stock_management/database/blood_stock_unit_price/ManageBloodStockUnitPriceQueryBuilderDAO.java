package backend.blood_stock_management.database.blood_stock_unit_price;

/**
 * {@code ManageBloodStockUnitPriceQueryBuilderDAO} provides a contract for
 * handling queries related to unit price management.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockUnitPriceQueryBuilderDAO {

  /**
   * Gets the query to fetch blood stock unit price for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock unit price for blood type specified.
   */
  String getBloodStockUnitPriceForBloodTypeQuery(final String bloodGroup,
                                                 final int bloodBankId);

  /**
   * Gets the update query to update blood stock unit price for blood type.
   * specified.
   *
   * @param newUnitPrice new blood stock unit price.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @return update query to update blood stock unit price for blood type.
   */
  String updateBloodStockUnitPriceForBloodTypeQuery(final double newUnitPrice,
                                                    final String bloodGroup,
                                                    final int bloodBankId);
}