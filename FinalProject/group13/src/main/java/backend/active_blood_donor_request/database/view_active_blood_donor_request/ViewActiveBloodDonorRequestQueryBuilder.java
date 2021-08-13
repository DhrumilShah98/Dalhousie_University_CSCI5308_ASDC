package backend.active_blood_donor_request.database.view_active_blood_donor_request;

import static backend.active_blood_donor_request.database.ActiveBloodDonorRequestDatabaseConstant.*;

/**
 * {@code ViewActiveBloodDonorRequestQueryBuilder} implements the
 * {@code ViewActiveBloodDonorRequestQueryBuilderDAO} to provide a concrete
 * implementation for viewing active blood donation requests.
 * This class is implemented using the Singleton Design Pattern.
 */
public class ViewActiveBloodDonorRequestQueryBuilder
    implements ViewActiveBloodDonorRequestQueryBuilderDAO {

  // Static instance of this class
  private static ViewActiveBloodDonorRequestQueryBuilder instance;

  /**
   * Constructs this {@code ViewActiveBloodDonorRequestQueryBuilder} instance.
   */
  private ViewActiveBloodDonorRequestQueryBuilder() {
    // required empty private constructor
  }

  /**
   * Instantiates this {@code ViewActiveBloodDonorRequestQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ViewActiveBloodDonorRequestQueryBuilder} class.
   */
  public static ViewActiveBloodDonorRequestQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ViewActiveBloodDonorRequestQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select active blood donation requests.
   *
   * @return string query to select active blood donation requests.
   */
  @Override
  public String selectActiveBloodDonorRequestQuery() {
    return "SELECT " +
        "u." + USER_ID_COLUMN +
        ", " +
        "bdr." + BLOOD_DONATION_REQUEST_ID_COLUMN +
        ", " +
        "u." + USER_FIRST_NAME_COLUMN +
        ", " +
        "u." + USER_LAST_NAME_COLUMN +
        ", " +
        "u." + USER_DATE_OF_BIRTH_COLUMN +
        ", " +
        "u." + USER_BLOOD_GROUP_COLUMN +
        ", " +
        "u." + USER_EMAIL_COLUMN +
        ", " +
        "u." + USER_CONTACT_NUMBER_COLUMN +
        ", " +
        "u." + USER_ADDRESS_FIRST_LINE_COLUMN +
        ", " +
        "u." + USER_ADDRESS_STREET_COLUMN +
        ", " +
        "u." + USER_ADDRESS_CITY_COLUMN +
        ", " +
        "u." + USER_ADDRESS_PROVINCE_COLUMN +
        ", " +
        "u." + USER_ADDRESS_ZIP_CODE_COLUMN +
        ", " +
        "u." + USER_ADDRESS_COUNTRY_COLUMN +
        " FROM " +
        USER_TABLE +
        " AS u, " +
        BLOOD_DONATION_REQUEST_TABLE +
        " AS bdr " +
        "WHERE " +
        "u." + USER_ID_COLUMN + " = bdr." + BLOOD_DONATION_USER_ID_COLUMN +
        " AND " +
        "bdr." + STATUS_COLUMN + " = \"" + STATUS_ACTIVE + "\";";
  }
}
