package backend.blood_stock_management.controller.blood_stock_quantity;

import backend.blood_stock_management.database.BloodStockDatabaseConstant;
import backend.blood_stock_management.database.blood_stock_quantity.ManageBloodStockQuantityQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code ManageBloodStockQuantityController} implements the
 * {@code ManageBloodStockQuantityControllerDAO} to provide a concrete
 * implementation for managing the blood stock quantity.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockQuantityController
    implements ManageBloodStockQuantityControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Manage blood stock query builder instance.
  private final ManageBloodStockQuantityQueryBuilderDAO
      manageBloodStockQuantityQueryBuilderDAO;

  /**
   * Constructs this {@code ManageBloodStockQuantityController} instance.
   *
   * @param databaseConnectionDAO                   database connection instance.
   * @param manageBloodStockQuantityQueryBuilderDAO manage blood stock
   *                                                query builder instance.
   */
  public ManageBloodStockQuantityController(final DatabaseConnectionDAO databaseConnectionDAO,
                                            final ManageBloodStockQuantityQueryBuilderDAO manageBloodStockQuantityQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.manageBloodStockQuantityQueryBuilderDAO =
        manageBloodStockQuantityQueryBuilderDAO;
  }

  /**
   * Gets the blood stock quantity for blood group type.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank unique id.
   *
   * @return blood stock quantity for blood group type.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private int getBloodStockQuantity(final String bloodGroup,
                                    final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement =
             dbConnection.createStatement();
         final ResultSet bloodStockResultSet =
             bloodStockStatement.
                 executeQuery(manageBloodStockQuantityQueryBuilderDAO.
                     getBloodStockQuantityForBloodTypeQuery(bloodGroup, bloodBankId))) {
      int bloodStockQuantity = 0;
      if (bloodStockResultSet.next()) {
        bloodStockQuantity = bloodStockResultSet.getInt(
            BloodStockDatabaseConstant.QUANTITY_COLUMN);
      }
      return bloodStockQuantity;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Updates the blood stock quantity for blood group type.
   *
   * @param newQuantity new quantity to update.
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private void updateBloodStockQuantity(final int newQuantity,
                                        final String bloodGroup,
                                        final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement =
             dbConnection.createStatement()) {
      bloodStockStatement.executeUpdate(
          manageBloodStockQuantityQueryBuilderDAO
              .updateBloodStockQuantityForBloodTypeQuery(newQuantity,
                  bloodGroup,
                  bloodBankId));
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Validates the blood stock quantity string.
   *
   * @param bloodGroupQuantityString blood stock quantity string.
   *
   * @throws BloodStockException if any error occurs while managing the
   *                             blood stock quantity.
   */
  private void validateBloodGroupQuantityString(final String bloodGroupQuantityString)
      throws BloodStockException {
    if (bloodGroupQuantityString == null) {
      throw new BloodStockException("Invalid blood group quantity string.");
    }
    final String[] bloodGroupQuantityStringArr = bloodGroupQuantityString.split(" ");
    if (bloodGroupQuantityStringArr.length != 2) {
      throw new BloodStockException("Invalid blood group quantity string.");
    }
    final String bloodGroupString = bloodGroupQuantityStringArr[0];
    final boolean bloodGroupValid = RegistrationValidationUtil.isBloodGroupValid(bloodGroupString);
    if (!bloodGroupValid) {
      throw new BloodStockException("Invalid blood group type entered.");
    }
    try {
      final String quantityString = bloodGroupQuantityStringArr[1];
      final int quantity = Integer.parseInt(quantityString);
      if (quantity <= 0) {
        throw new BloodStockException("Invalid blood group type entered.");
      }
    } catch (Exception e) {
      throw new BloodStockException(e.getMessage());
    }
  }

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
  @Override
  public int incrementBloodStockQuantity(final String incrementBloodGroupQuantityString,
                                         final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupQuantityString(incrementBloodGroupQuantityString);
    final String[] incrementBloodGroupQuantityStringArr =
        incrementBloodGroupQuantityString.split(" ");
    final String bloodGroup = incrementBloodGroupQuantityStringArr[0];
    final int incrementBloodQuantity =
        Integer.parseInt(incrementBloodGroupQuantityStringArr[1]);
    final int bloodStockQuantity = getBloodStockQuantity(bloodGroup, bloodBankId);
    final int newBloodStockQuantity = bloodStockQuantity + incrementBloodQuantity;
    updateBloodStockQuantity(newBloodStockQuantity, bloodGroup, bloodBankId);
    return newBloodStockQuantity;
  }

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
  @Override
  public int decrementBloodStockQuantity(final String decrementBloodGroupQuantityString,
                                         final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupQuantityString(decrementBloodGroupQuantityString);
    final String[] decrementBloodGroupQuantityStringArr =
        decrementBloodGroupQuantityString.split(" ");
    final String bloodGroup = decrementBloodGroupQuantityStringArr[0];
    final int decrementBloodQuantity =
        Integer.parseInt(decrementBloodGroupQuantityStringArr[1]);
    final int bloodStockQuantity = getBloodStockQuantity(bloodGroup, bloodBankId);
    final int newBloodStockQuantity = bloodStockQuantity - decrementBloodQuantity;
    if (newBloodStockQuantity < 0) {
      throw new BloodStockException("Invalid blood quantity entered.");
    }
    updateBloodStockQuantity(newBloodStockQuantity, bloodGroup, bloodBankId);
    return newBloodStockQuantity;
  }

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
  @Override
  public int overwriteBloodStockQuantity(final String overwriteBloodGroupQuantityString,
                                         final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupQuantityString(overwriteBloodGroupQuantityString);
    final String[] overwriteBloodGroupQuantityStringArr =
        overwriteBloodGroupQuantityString.split(" ");
    final String bloodGroup = overwriteBloodGroupQuantityStringArr[0];
    final int overwriteBloodQuantity = Integer.parseInt(overwriteBloodGroupQuantityStringArr[1]);
    updateBloodStockQuantity(overwriteBloodQuantity, bloodGroup, bloodBankId);
    return overwriteBloodQuantity;
  }
}