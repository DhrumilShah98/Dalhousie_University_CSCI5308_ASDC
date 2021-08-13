package backend.blood_stock_management.controller.view_blood_stock;

import backend.blood_stock_management.database.view_blood_stock.ViewBloodStockQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import backend.blood_stock_management.model.BloodStock;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_STOCK_ID_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_GROUP_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.QUANTITY_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.THRESHOLD_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.UNIT_PRICE_COLUMN;

/**
 * {@code ViewBloodStockController} implements the
 * {@code ViewBloodStockControllerDAO} to provide a concrete
 * implementation for viewing all blood stock.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ViewBloodStockController implements ViewBloodStockControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // View all blood stock query builder.
  private final ViewBloodStockQueryBuilderDAO
      viewBloodStockQueryBuilderDAO;

  /**
   * Constructs this {@code ManageBloodStockUnitPriceController} instance.
   *
   * @param databaseConnectionDAO         database connection instance.
   * @param viewBloodStockQueryBuilderDAO view all blood stock query builder
   *                                      instance.
   */
  public ViewBloodStockController(final DatabaseConnectionDAO databaseConnectionDAO,
                                  final ViewBloodStockQueryBuilderDAO viewBloodStockQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.viewBloodStockQueryBuilderDAO =
        viewBloodStockQueryBuilderDAO;
  }

  /**
   * Prepares the blood stock list.
   *
   * @param bloodBankId         blood bank unique id.
   * @param bloodStockResultSet blood stock result set.
   *
   * @return a list of blood stock.
   *
   * @throws SQLException if any error occurs while accessing the result set.
   */
  public List<BloodStock> prepareBloodStockList(final int bloodBankId,
                                                final ResultSet bloodStockResultSet)
      throws SQLException {
    final List<BloodStock> bloodStockList = new ArrayList<>();
    while (bloodStockResultSet.next()) {
      final int bloodStockId = bloodStockResultSet.getInt(BLOOD_STOCK_ID_COLUMN);
      final String bloodGroup = bloodStockResultSet.getString(BLOOD_GROUP_COLUMN);
      final int quantity = bloodStockResultSet.getInt(QUANTITY_COLUMN);
      final int threshold = bloodStockResultSet.getInt(THRESHOLD_COLUMN);
      final double unitPrice = bloodStockResultSet.getDouble(UNIT_PRICE_COLUMN);
      final BloodStock bloodStock = new BloodStock(
          bloodStockId,
          bloodBankId,
          bloodGroup,
          quantity,
          threshold,
          unitPrice);
      bloodStockList.add(bloodStock);
    }
    return bloodStockList;
  }

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
  @Override
  public List<BloodStock> viewBloodStock(final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    if (bloodBankId < 0) {
      throw new BloodStockException("Invalid blood bank id");
    }
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement = dbConnection.createStatement();
         final ResultSet bloodStockResultSet = bloodStockStatement
             .executeQuery(viewBloodStockQueryBuilderDAO
                 .selectBloodStockQuery(bloodBankId))) {
      return prepareBloodStockList(bloodBankId, bloodStockResultSet);
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}