package backend.blood_stock_management.controller.blood_stock_threshold;

import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionException;

/**
 * {@code ManageBloodStockThresholdControllerDAO} provides a contract to manage
 * blood stock threshold.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ManageBloodStockThresholdControllerDAO {

  /**
   * Increments the blood stock threshold.
   *
   * @param incrementBloodGroupThresholdString increment threshold string.
   * @param bloodBankId                        blood bank id.
   *
   * @return incremented threshold.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock threshold.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int incrementBloodStockThreshold(final String incrementBloodGroupThresholdString,
                                   final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Decrement the blood stock threshold
   *
   * @param decrementBloodGroupThresholdString decrement threshold string
   * @param bloodBankId                        blood bank id.
   *
   * @return decremented threshold.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock threshold.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int decrementBloodStockThreshold(final String decrementBloodGroupThresholdString,
                                   final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;

  /**
   * Overwrite the blood stock threshold
   *
   * @param overwriteBloodGroupThresholdString overwrite threshold string
   * @param bloodBankId                        blood bank id.
   *
   * @return overwritten threshold.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock threshold.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  int overwriteBloodStockThreshold(final String overwriteBloodGroupThresholdString,
                                   final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;
}