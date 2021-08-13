package backend.blood_stock_management.database.blood_stock_unit_price;

import static backend.blood_stock_management.database.BloodStockDatabaseConstant.*;

/**
 * {@code ManageBloodStockUnitPriceQueryBuilder} implements the
 * {@code ManageBloodStockUnitPriceQueryBuilderDAO} to provide a concrete
 * implementation for managing the blood stock quantity.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class ManageBloodStockUnitPriceQueryBuilder
    implements ManageBloodStockUnitPriceQueryBuilderDAO {

  // Static instance of this class.
  private static ManageBloodStockUnitPriceQueryBuilder instance;

  /**
   * Constructs this {@code ManageBloodStockUnitPriceQueryBuilder} instance.
   */
  private ManageBloodStockUnitPriceQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code ManageBloodStockUnitPriceQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ManageBloodStockUnitPriceQueryBuilder} class.
   */
  public static ManageBloodStockUnitPriceQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ManageBloodStockUnitPriceQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to fetch blood stock unit price for blood type specified.
   *
   * @param bloodGroup  blood group type.
   * @param bloodBankId blood bank id.
   *
   * @return query to fetch blood stock unit price for blood type specified.
   */
  @Override
  public String getBloodStockUnitPriceForBloodTypeQuery(final String bloodGroup,
                                                        final int bloodBankId) {
    return "SELECT " +
        UNIT_PRICE_COLUMN +
        " FROM " +
        BLOOD_STOCK_TABLE +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }

  /**
   * Gets the update query to update blood stock unit price for blood type.
   * specified.
   *
   * @param newUnitPrice new blood stock unit price.
   * @param bloodGroup   blood group type.
   * @param bloodBankId  blood bank id.
   *
   * @return update query to update blood stock unit price for blood type.
   */
  @Override
  public String updateBloodStockUnitPriceForBloodTypeQuery(final double newUnitPrice,
                                                           final String bloodGroup,
                                                           final int bloodBankId) {
    return "UPDATE " +
        BLOOD_STOCK_TABLE +
        " SET " +
        UNIT_PRICE_COLUMN + " = " + newUnitPrice +
        " WHERE " +
        BLOOD_GROUP_COLUMN + " = \"" + bloodGroup + "\" AND " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }
}