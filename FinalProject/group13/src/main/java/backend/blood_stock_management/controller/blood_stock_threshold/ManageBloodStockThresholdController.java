package backend.blood_stock_management.controller.blood_stock_threshold;

import backend.blood_stock_management.database.BloodStockDatabaseConstant;
import backend.blood_stock_management.database.blood_stock_threshold.ManageBloodStockThresholdQueryBuilderDAO;
import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code ManageBloodStockThresholdController} implements the
 * {@code ManageBloodStockThresholdControllerDAO} to provide a concrete
 * implementation for managing the blood stock threshold.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockThresholdController implements ManageBloodStockThresholdControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Manage blood stock threshold builder instance.
  private final ManageBloodStockThresholdQueryBuilderDAO manageBloodStockThresholdQueryBuilderDAO;

  /**
   * Constructs this {@code ManageBloodStockThresholdController} instance.
   *
   * @param databaseConnectionDAO                    database connection instance.
   * @param manageBloodStockThresholdQueryBuilderDAO manage blood stock
   *                                                 query builder instance.
   */
  public ManageBloodStockThresholdController(final DatabaseConnectionDAO databaseConnectionDAO,
                                             final ManageBloodStockThresholdQueryBuilderDAO manageBloodStockThresholdQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.manageBloodStockThresholdQueryBuilderDAO =
        manageBloodStockThresholdQueryBuilderDAO;
  }

  /**
   * Gets the blood stock threshold for blood group type.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank unique id.
   *
   * @return blood stock threshold for blood group type.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private int getBloodStockThreshold(final String bloodGroup,
                                     final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement = dbConnection.createStatement();
         final ResultSet bloodStockResultSet = bloodStockStatement
             .executeQuery(manageBloodStockThresholdQueryBuilderDAO
                 .getBloodStockThresholdForBloodTypeQuery(bloodGroup, bloodBankId))) {
      int bloodStockThreshold = 0;
      if (bloodStockResultSet.next()) {
        bloodStockThreshold = bloodStockResultSet.getInt(
            BloodStockDatabaseConstant.THRESHOLD_COLUMN);
      }
      return bloodStockThreshold;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Updates the blood stock threshold for blood group type.
   *
   * @param newThreshold new threshold to update.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private void updateBloodStockThreshold(final int newThreshold,
                                         final String bloodGroup,
                                         final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodStockStatement = dbConnection.createStatement()) {
      bloodStockStatement
          .executeUpdate(manageBloodStockThresholdQueryBuilderDAO
              .updateBloodStockThresholdForBloodTypeQuery(
                  newThreshold,
                  bloodGroup,
                  bloodBankId));
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Validates the blood stock threshold string.
   *
   * @param bloodGroupThresholdString blood stock quantity string.
   *
   * @throws BloodStockException if any error occurs while managing the
   *                             blood stock quantity.
   */
  private void validateBloodGroupThresholdString(final String bloodGroupThresholdString)
      throws BloodStockException {
    if (bloodGroupThresholdString == null) {
      throw new BloodStockException("Invalid blood group Threshold string.");
    }
    final String[] bloodGroupThresholdStringArr = bloodGroupThresholdString.split(" ");
    if (bloodGroupThresholdStringArr.length != 2) {
      throw new BloodStockException("Invalid blood group Threshold string.");
    }
    final String bloodGroupString = bloodGroupThresholdStringArr[0];
    final boolean bloodGroupValid = RegistrationValidationUtil.isBloodGroupValid(bloodGroupString);
    if (!bloodGroupValid) {
      throw new BloodStockException("Invalid blood group type entered.");
    }
    try {
      final String ThresholdString = bloodGroupThresholdStringArr[1];
      final int Threshold = Integer.parseInt(ThresholdString);
      if (Threshold <= 0) {
        throw new BloodStockException("Invalid blood group type entered.");
      }
    } catch (Exception e) {
      throw new BloodStockException(e.getMessage());
    }
  }

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
  @Override
  public int incrementBloodStockThreshold(final String incrementBloodGroupThresholdString,
                                          final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupThresholdString(incrementBloodGroupThresholdString);
    final String[] incrementBloodGroupThresholdStringArr =
        incrementBloodGroupThresholdString.split(" ");
    final String bloodGroup = incrementBloodGroupThresholdStringArr[0];
    final int incrementBloodThreshold =
        Integer.parseInt(incrementBloodGroupThresholdStringArr[1]);
    final int bloodStockThreshold = getBloodStockThreshold(bloodGroup, bloodBankId);
    final int newBloodStockThreshold = bloodStockThreshold + incrementBloodThreshold;
    updateBloodStockThreshold(newBloodStockThreshold, bloodGroup, bloodBankId);
    return newBloodStockThreshold;
  }

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
  @Override
  public int decrementBloodStockThreshold(final String decrementBloodGroupThresholdString,
                                          final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupThresholdString(decrementBloodGroupThresholdString);
    final String[] decrementBloodGroupThresholdStringArr =
        decrementBloodGroupThresholdString.split(" ");
    final String bloodGroup = decrementBloodGroupThresholdStringArr[0];
    final int decrementBloodThreshold =
        Integer.parseInt(decrementBloodGroupThresholdStringArr[1]);
    final int bloodStockThreshold = getBloodStockThreshold(bloodGroup, bloodBankId);
    final int newBloodStockThreshold = bloodStockThreshold - decrementBloodThreshold;
    if (newBloodStockThreshold < 0) {
      throw new BloodStockException("Invalid blood Threshold entered.");
    }
    updateBloodStockThreshold(newBloodStockThreshold, bloodGroup, bloodBankId);
    return newBloodStockThreshold;
  }

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
  @Override
  public int overwriteBloodStockThreshold(final String overwriteBloodGroupThresholdString,
                                          final int bloodBankId)
      throws BloodStockException, DatabaseConnectionException {
    validateBloodGroupThresholdString(overwriteBloodGroupThresholdString);
    final String[] overwriteBloodGroupThresholdStringArr =
        overwriteBloodGroupThresholdString.split(" ");
    final String bloodGroup = overwriteBloodGroupThresholdStringArr[0];
    final int overwriteBloodThreshold =
        Integer.parseInt(overwriteBloodGroupThresholdStringArr[1]);
    updateBloodStockThreshold(overwriteBloodThreshold, bloodGroup, bloodBankId);
    return overwriteBloodThreshold;
  }
}