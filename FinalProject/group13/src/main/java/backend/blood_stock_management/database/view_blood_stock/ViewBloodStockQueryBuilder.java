package backend.blood_stock_management.database.view_blood_stock;

import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_GROUP_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_STOCK_ID_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.BLOOD_STOCK_TABLE;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.QUANTITY_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.THRESHOLD_COLUMN;
import static backend.blood_stock_management.database.BloodStockDatabaseConstant.UNIT_PRICE_COLUMN;

/**
 * {@code ViewBloodStockQueryBuilder} implements the
 * {@code ViewBloodStockQueryBuilderDAO} to provide a concrete
 * implementation for handling queries related to view blood stock.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ViewBloodStockQueryBuilder
    implements ViewBloodStockQueryBuilderDAO {

  // Static instance of this class.
  private static ViewBloodStockQueryBuilder instance;

  /**
   * Constructs this {@code ViewBloodStockQueryBuilder} instance.
   */
  private ViewBloodStockQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankRatingQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankRatingQueryBuilder} class.
   */
  public static ViewBloodStockQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ViewBloodStockQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to view all blood stock of blood bank with unique id
   * {@code bloodBankId}.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return query to view all blood stock.
   */
  @Override
  public String selectBloodStockQuery(int bloodBankId) {
    return "SELECT " +
        BLOOD_STOCK_ID_COLUMN + ", " +
        BLOOD_BANK_ID_COLUMN + ", " +
        BLOOD_GROUP_COLUMN + ", " +
        QUANTITY_COLUMN + ", " +
        THRESHOLD_COLUMN + ", " +
        UNIT_PRICE_COLUMN +
        " FROM " +
        BLOOD_STOCK_TABLE +
        " WHERE " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }
}