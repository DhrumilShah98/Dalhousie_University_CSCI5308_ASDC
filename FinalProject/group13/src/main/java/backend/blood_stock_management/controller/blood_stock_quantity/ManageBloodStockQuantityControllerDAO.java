package backend.blood_stock_management.controller.blood_stock_quantity;

import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionException;

/**
 * {@code ManageBloodStockQuantityControllerDAO} provides a contract to manage
 * blood stock quantity.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockQuantityControllerDAO {

  /**
   * Increments the blood stock quantity.
   *
   * @param incrementBloodGroupQuantityString increment quantity string.
   * @param bloodBankId                       blood bank id.
   *
   * @return incremented quantity.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock quantity.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int incrementBloodStockQuantity(final String incrementBloodGroupQuantityString,
                                  final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Decrement the blood stock quantity
   *
   * @param decrementBloodGroupQuantityString decrement quantity string
   * @param bloodBankId                       blood bank id.
   *
   * @return decremented quantity.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock quantity.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int decrementBloodStockQuantity(final String decrementBloodGroupQuantityString,
                                  final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Overwrite the blood stock quantity
   *
   * @param overwriteBloodGroupQuantityString overwrite quantity string
   * @param bloodBankId                       blood bank id.
   *
   * @return overwritten quantity.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock quantity.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int overwriteBloodStockQuantity(final String overwriteBloodGroupQuantityString,
                                  final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;
}