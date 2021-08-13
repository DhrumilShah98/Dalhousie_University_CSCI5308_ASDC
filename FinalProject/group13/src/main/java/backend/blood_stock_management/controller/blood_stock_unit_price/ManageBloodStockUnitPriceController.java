package backend.blood_stock_management.controller.blood_stock_unit_price;

import backend.blood_stock_management.database.BloodStockDatabaseConstant;
import backend.blood_stock_management.database.blood_stock_unit_price.ManageBloodStockUnitPriceQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code ManageBloodStockUnitPriceController} implements the
 * {@code ManageBloodStockUnitPriceController} to provide a concrete
 * implementation for managing the blood stock unit price.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockUnitPriceController implements ManageBloodStockUnitPriceControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Manage blood stock query builder instance.
  private final ManageBloodStockUnitPriceQueryBuilderDAO
      manageBloodStockUnitPriceQueryBuilderDAO;

  /**
   * Constructs this {@code ManageBloodStockUnitPriceController} instance.
   *
   * @param databaseConnectionDAO                    database connection instance.
   * @param manageBloodStockUnitPriceQueryBuilderDAO manage blood stock
   *                                                 query builder instance.
   */
  public ManageBloodStockUnitPriceController(final DatabaseConnectionDAO databaseConnectionDAO,
                                             final ManageBloodStockUnitPriceQueryBuilderDAO manageBloodStockUnitPriceQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.manageBloodStockUnitPriceQueryBuilderDAO =
        manageBloodStockUnitPriceQueryBuilderDAO;
  }

  /**
   * Gets the blood stock unit price for blood group type.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank unique id.
   *
   * @return blood stock quantity for blood group type.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private double getBloodStockUnitPrice(final String bloodGroup,
                                        final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement = dbConnection.createStatement();
         final ResultSet bloodStockResultSet =
             bloodStockStatement.executeQuery(
                 manageBloodStockUnitPriceQueryBuilderDAO
                     .getBloodStockUnitPriceForBloodTypeQuery(
                         bloodGroup,
                         bloodBankId))) {
      double bloodStockUnitPrice = 0;
      if (bloodStockResultSet.next()) {
        bloodStockUnitPrice = bloodStockResultSet
            .getDouble(BloodStockDatabaseConstant.UNIT_PRICE_COLUMN);
      }
      return bloodStockUnitPrice;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Updates the blood stock unit price for blood group type.
   *
   * @param newUnitPrice new unit price to update.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private void updateBloodStockUnitPrice(final double newUnitPrice,
                                         final String bloodGroup,
                                         final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement()) {
      statement.executeUpdate(
          manageBloodStockUnitPriceQueryBuilderDAO
              .updateBloodStockUnitPriceForBloodTypeQuery(
                  newUnitPrice,
                  bloodGroup,
                  bloodBankId));
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Validates the blood stock unit price string.
   *
   * @param bloodGroupUnitPriceString blood stock quantity string.
   *
   * @throws BloodStockException if any error occurs while managing the
   *                             blood stock quantity.
   */
  private void validateBloodGroupUnitPriceString(final String bloodGroupUnitPriceString)
      throws BloodStockException {
    if (bloodGroupUnitPriceString == null) {
      throw new BloodStockException("Invalid blood group unit price string.");
    }
    final String[] bloodGroupUnitPriceStringArr = bloodGroupUnitPriceString.split(" ");
    if (bloodGroupUnitPriceStringArr.length != 2) {
      throw new BloodStockException("Invalid blood group unit price string.");
    }
    final String bloodGroupString = bloodGroupUnitPriceStringArr[0];
    final boolean bloodGroupValid = RegistrationValidationUtil.isBloodGroupValid(bloodGroupString);
    if (!bloodGroupValid) {
      throw new BloodStockException("Invalid blood group type entered.");
    }
    try {
      final String unitPriceString = bloodGroupUnitPriceStringArr[1];
      final double unitPrice = Double.parseDouble(unitPriceString);
      if (unitPrice < 0.0d) {
        throw new BloodStockException("Invalid unit price entered.");
      }
    } catch (Exception e) {
      throw new BloodStockException(e.getMessage());
    }
  }

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
  @Override
  public double incrementBloodStockUnitPrice(final String incrementBloodGroupUnitPriceString,
                                             final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupUnitPriceString(incrementBloodGroupUnitPriceString);
    final String[] incrementBloodGroupUnitPriceStringArr =
        incrementBloodGroupUnitPriceString.split(" ");
    final String bloodGroup = incrementBloodGroupUnitPriceStringArr[0];
    final double incrementBloodUnitPrice =
        Double.parseDouble(incrementBloodGroupUnitPriceStringArr[1]);
    final double bloodStockUnitPrice = getBloodStockUnitPrice(bloodGroup, bloodBankId);
    final double newBloodStockUnitPrice = bloodStockUnitPrice + incrementBloodUnitPrice;
    updateBloodStockUnitPrice(newBloodStockUnitPrice, bloodGroup, bloodBankId);
    return newBloodStockUnitPrice;
  }

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
  @Override
  public double decrementBloodStockUnitPrice(final String decrementBloodGroupUnitPriceString,
                                             final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupUnitPriceString(decrementBloodGroupUnitPriceString);
    final String[] decrementBloodGroupUnitPriceStringArr =
        decrementBloodGroupUnitPriceString.split(" ");
    final String bloodGroup = decrementBloodGroupUnitPriceStringArr[0];
    final double decrementBloodUnitPrice =
        Double.parseDouble(decrementBloodGroupUnitPriceStringArr[1]);
    final double bloodStockUnitPrice = getBloodStockUnitPrice(bloodGroup, bloodBankId);
    final double newBloodStockUnitPrice = bloodStockUnitPrice - decrementBloodUnitPrice;
    if (newBloodStockUnitPrice < 0) {
      throw new BloodStockException("Invalid blood unit price entered.");
    }
    updateBloodStockUnitPrice(newBloodStockUnitPrice, bloodGroup, bloodBankId);
    return newBloodStockUnitPrice;
  }

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
  @Override
  public double overwriteBloodStockUnitPrice(final String overwriteBloodGroupUnitPriceString,
                                             final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupUnitPriceString(overwriteBloodGroupUnitPriceString);
    final String[] overwriteBloodGroupUnitPriceStringArr =
        overwriteBloodGroupUnitPriceString.split(" ");
    final String bloodGroup = overwriteBloodGroupUnitPriceStringArr[0];
    final double overwriteBloodUnitPrice =
        Double.parseDouble(overwriteBloodGroupUnitPriceStringArr[1]);
    updateBloodStockUnitPrice(overwriteBloodUnitPrice, bloodGroup, bloodBankId);
    return overwriteBloodUnitPrice;
  }
}

