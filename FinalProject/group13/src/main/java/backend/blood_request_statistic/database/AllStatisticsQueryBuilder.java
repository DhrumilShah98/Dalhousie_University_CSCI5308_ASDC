package backend.blood_request_statistic.database;

import static backend.blood_request_statistic.database.ReceiverRequestConstant.*;

/**
 * {@code AllStatisticsQueryBuilder} implements the
 * {@code AllStatisticsQueryBuilderDAO} to provide a concrete
 * implementation for the blood receiver request statistics.
 * This class is implemented using the Singleton Design Pattern.
 */
public class AllStatisticsQueryBuilder implements AllStatisticsQueryBuilderDAO {

  // Static instance of this class
  private static AllStatisticsQueryBuilder instance;

  /**
   * Constructs this {@code AllStatisticsQueryBuilder} instance.
   */
  private AllStatisticsQueryBuilder() {
    // required empty private constructor
  }

  /**
   * Instantiates this {@code AllStatisticsQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code AllStatisticsQueryBuilder} class.
   */
  public static AllStatisticsQueryBuilder getInstance() {
    if (instance == null) {
      instance = new AllStatisticsQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select this blood receiver request in the table.
   *
   * @return string query to select this blood receiver request in the table.
   */
  @Override
  public String selectBloodReceiverRequestQuery() {
    return "SELECT " +
        "u." + USER_ID_COLUMN + ", " +
        "brr." + BLOOD_RECEIVER_REQUEST_ID_COLUMN + ", " +
        "u." + USER_FIRST_NAME_COLUMN + ", " +
        "u." + USER_LAST_NAME_COLUMN + ", " +
        "u." + USER_DATE_OF_BIRTH_COLUMN + ", " +
        "u." + USER_BLOOD_GROUP_COLUMN + ", " +
        "u." + USER_EMAIL_COLUMN + ", " +
        "u." + USER_CONTACT_NUMBER_COLUMN + ", " +
        "u." + USER_ADDRESS_FIRST_LINE_COLUMN + ", " +
        "u." + USER_ADDRESS_STREET_COLUMN + ", " +
        "u." + USER_ADDRESS_CITY_COLUMN + ", " +
        "u." + USER_ADDRESS_PROVINCE_COLUMN + ", " +
        "u." + USER_ADDRESS_ZIP_CODE_COLUMN + ", " +
        "u." + USER_ADDRESS_COUNTRY_COLUMN +
        " FROM " +
        USER_TABLE + " AS u, " +
        BLOOD_RECEIVER_REQUEST_TABLE + " AS brr " +
        "WHERE " +
        "u." + USER_ID_COLUMN + " = brr." + BLOOD_RECEIVER_USER_ID_COLUMN + ";";
  }

  /**
   * Gets the query to select this active blood receiver request in the table.
   *
   * @return string query to select this active blood receiver request in the
   * table.
   */
  @Override
  public String selectActiveBloodReceiverRequestQuery() {
    return "SELECT " +
        "u." + USER_ID_COLUMN + ", " +
        "brr." + BLOOD_RECEIVER_REQUEST_ID_COLUMN + ", " +
        "u." + USER_FIRST_NAME_COLUMN + ", " +
        "u." + USER_LAST_NAME_COLUMN + ", " +
        "u." + USER_DATE_OF_BIRTH_COLUMN + ", " +
        "u." + USER_BLOOD_GROUP_COLUMN + ", " +
        "u." + USER_EMAIL_COLUMN + ", " +
        "u." + USER_CONTACT_NUMBER_COLUMN + ", " +
        "u." + USER_ADDRESS_FIRST_LINE_COLUMN + ", " +
        "u." + USER_ADDRESS_STREET_COLUMN + ", " +
        "u." + USER_ADDRESS_CITY_COLUMN + ", " +
        "u." + USER_ADDRESS_PROVINCE_COLUMN + ", " +
        "u." + USER_ADDRESS_ZIP_CODE_COLUMN + ", " +
        "u." + USER_ADDRESS_COUNTRY_COLUMN +
        " FROM " +
        USER_TABLE + " AS u, " +
        BLOOD_RECEIVER_REQUEST_TABLE + " AS brr " +
        "WHERE " +
        "u." + USER_ID_COLUMN + " = brr." + BLOOD_RECEIVER_USER_ID_COLUMN +
        " AND " +
        "brr." + STATUS_COLUMN + " = \"" + STATUS_ACTIVE + ";";
  }
}