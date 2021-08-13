package backend.active_blood_receiver_request.database;

import static backend.active_blood_receiver_request.database.ActiveBloodReceiverRequestDatabaseConstant.*;

/**
 * {@code ActiveReceiverRequestQueryBuilder} implements the
 * {@code ActiveReceiverRequestQueryBuilderDAO} to provide a concrete
 * implementation for viewing active blood receiver requests.
 * This class is implemented using the Singleton Design Pattern.
 */
public class ActiveReceiverRequestQueryBuilder implements ActiveReceiverRequestQueryBuilderDAO {

  // Static instance of this class
  private static ActiveReceiverRequestQueryBuilder instance;

  /**
   * Constructs this {@code ActiveReceiverRequestQueryBuilder} instance.
   */
  private ActiveReceiverRequestQueryBuilder() {
    // required empty private constructor
  }

  /**
   * Instantiates this {@code ActiveReceiverRequestQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ActiveReceiverRequestQueryBuilder} class.
   */
  public static ActiveReceiverRequestQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ActiveReceiverRequestQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select active blood receiver requests.
   *
   * @return string query to select active blood receiver requests.
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
        "brr." + STATUS_COLUMN + " = \"" + STATUS_ACTIVE + "\";";
  }
}
