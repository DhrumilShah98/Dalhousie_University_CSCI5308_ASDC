package backend.blood_stock_management.controller.view_blood_stock;

import backend.blood_stock_management.exception.BloodStockException;
import backend.blood_stock_management.model.BloodStock;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewBloodStockControllerDAO} provides a contract to view all blood
 * stock of a blood bank.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface ViewBloodStockControllerDAO {

  /**
   * Gets all blood stock of this blood bank.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return blood stock of this blood bank.
   *
   * @throws BloodStockException         if any error occurs while managing the
   *                                     blood stock unit price.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  List<BloodStock> viewBloodStock(final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException;
}
