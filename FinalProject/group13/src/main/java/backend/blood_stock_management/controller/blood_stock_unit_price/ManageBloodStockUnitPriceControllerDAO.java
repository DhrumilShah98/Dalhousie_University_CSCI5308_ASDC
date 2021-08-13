package backend.blood_stock_management.controller.blood_stock_unit_price;

import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionException;

/**
 * {@code ManageBloodStockUnitPriceControllerDAO} provides a contract to manage
 * blood stock unit price.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockUnitPriceControllerDAO {

  /**
   * Increments the blood stock unit price.
   *
   * @param incrementBloodGroupUnitPriceString String increment unit price string.
   * @param bloodBankId                        blood bank id.
   *
   * @return incremented unit price.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock unit price.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  double incrementBloodStockUnitPrice(final String incrementBloodGroupUnitPriceString,
                                      final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Decrement the blood stock unit price
   *
   * @param decrementBloodGroupUnitPriceString String decrement unit price string
   * @param bloodBankId                        blood bank id.
   *
   * @return decremented unit price.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock unit price.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  double decrementBloodStockUnitPrice(final String decrementBloodGroupUnitPriceString,
                                      final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Overwrite the blood stock unit price
   *
   * @param overwriteBloodGroupUnitPriceString String overwrite unit price string
   * @param bloodBankId                        blood bank id.
   *
   * @return overwritten unit price.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock unit price.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  double overwriteBloodStockUnitPrice(final String overwriteBloodGroupUnitPriceString,
                                      final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;
}
