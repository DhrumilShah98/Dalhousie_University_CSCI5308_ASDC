package backend.blood_stock_management.database.blood_stock_quantity;

import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_GROUP_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_STOCK_TABLE;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.QUANTITY_COLUMN;

/**
 * {@code ManageBloodStockQuantityQueryBuilder} implements the
 * {@code ManageBloodStockQuantityQueryBuilderDAO} to provide a concrete
 * implementation for managing the blood stock quantity.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockQuantityQueryBuilder implements ManageBloodStockQuantityQueryBuilderDAO {

  // Static instance of this class.
  private static ManageBloodStockQuantityQueryBuilder instance;

  /**
   * Constructs this {@code ManageBloodStockQuantityQueryBuilder} instance.
   */
  private ManageBloodStockQuantityQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code ManageBloodStockQuantityQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ManageBloodStockQuantityQueryBuilder} class.
   */
  public static ManageBloodStockQuantityQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ManageBloodStockQuantityQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to fetch blood stock quantity for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock quantity for blood type specified.
   */
  @Override
  public String getBloodStockQuantityForBloodTypeQuery(final String bloodGroup,
                                                       final int bloodBankId) {
    return "SELECT " +
        QUANTITY_COLUMN +
        " FROM " +
        BLOOD_STOCK_TABLE +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }

  /**
   * Gets the update query to update blood stock quantity for blood type.
   * specified.
   *
   * @param newQuantity new blood stock quantity.
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return update query to update blood stock quantity for blood type.
   */
  @Override
  public String updateBloodStockQuantityForBloodTypeQuery(final int newQuantity,
                                                          final String bloodGroup,
                                                          final int bloodBankId) {
    return "UPDATE " +
        BLOOD_STOCK_TABLE +
        " SET " +
        QUANTITY_COLUMN + " = " + newQuantity +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }
}