package backend.blood_stock_management.database.blood_stock_threshold;

import backend.blood_stock_management.database.blood_stock_quantity.ManageBloodStockQuantityQueryBuilder;

import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_GROUP_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_STOCK_TABLE;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.THRESHOLD_COLUMN;

/**
 * {@code ManageBloodStockThresholdQueryBuilder} implements the
 * {@code ManageBloodStockThresholdQueryBuilderDAO} to provide a concrete
 * implementation for managing the blood stock threshold.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockThresholdQueryBuilder implements ManageBloodStockThresholdQueryBuilderDAO {
  private static ManageBloodStockThresholdQueryBuilder instance;

  /**
   * Constructs this {@code ManageBloodStockThresholdQueryBuilder} instance.
   */
  private ManageBloodStockThresholdQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code ManageBloodStockThresholdQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ManageBloodStockThresholdQueryBuilder} class.
   */
  public static ManageBloodStockThresholdQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ManageBloodStockThresholdQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to fetch blood stock threshold for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock threshold for blood type specified.
   */
  @Override
  public String getBloodStockThresholdForBloodTypeQuery(final String bloodGroup,
                                                        final int bloodBankId) {
    return "SELECT " +
        THRESHOLD_COLUMN +
        " FROM " +
        BLOOD_STOCK_TABLE +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }

  /**
   * Gets the update query to update blood stock threshold for blood type.
   * specified.
   *
   * @param newThreshold new blood stock threshold.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @return update query to update blood stock threshold for blood type.
   */
  @Override
  public String updateBloodStockThresholdForBloodTypeQuery(final int newThreshold,
                                                           final String bloodGroup,
                                                           final int bloodBankId) {
    return "UPDATE " +
        BLOOD_STOCK_TABLE +
        " SET " +
        THRESHOLD_COLUMN + " = " + newThreshold +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }
}
