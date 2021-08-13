package backend.blood_stock_management.database.view_blood_stock;

/**
 * {@code ViewBloodStockQueryBuilderDAO} provides a contract for handling
 * queries related to view blood stock.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ViewBloodStockQueryBuilderDAO {

  /**
   * Gets the query to view all blood stock.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return query to view all blood stock.
   */
  String selectBloodStockQuery(int bloodBankId);
}