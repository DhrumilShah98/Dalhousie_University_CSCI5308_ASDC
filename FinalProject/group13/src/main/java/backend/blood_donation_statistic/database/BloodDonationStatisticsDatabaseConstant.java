package backend.blood_donation_statistic.database;

/**
 * {@code BloodDonationStatisticsDatabaseConstant} stores the information related
 * to the blood donation statistics.
 */
public class BloodDonationStatisticsDatabaseConstant {

  // User table name.
  public static final String USER_TABLE =
      "user";

  // User table unique id column.
  public static final String USER_ID_COLUMN =
      "user_id";

  // User table first name column.
  public static final String USER_FIRST_NAME_COLUMN =
      "first_name";

  // User table last name column.
  public static final String USER_LAST_NAME_COLUMN =
      "last_name";

  // User table date of birth column.
  public static final String USER_DATE_OF_BIRTH_COLUMN =
      "date_of_birth";

  // User table email column.
  public static final String USER_EMAIL_COLUMN =
      "email";

  // User table contact number column.
  public static final String USER_CONTACT_NUMBER_COLUMN =
      "contact_number";

  // User table blood group column.
  public static final String USER_BLOOD_GROUP_COLUMN =
      "blood_group";

  // User table address first line column.
  public static final String USER_ADDRESS_FIRST_LINE_COLUMN =
      "address_first_line";

  // User table address street column.
  public static final String USER_ADDRESS_STREET_COLUMN =
      "address_street";

  // User table address city column.
  public static final String USER_ADDRESS_CITY_COLUMN =
      "address_city";

  // User table address province column.
  public static final String USER_ADDRESS_PROVINCE_COLUMN =
      "address_province";

  // User table address zip code column.
  public static final String USER_ADDRESS_ZIP_CODE_COLUMN =
      "address_zip_code";

  // User table address country column.
  public static final String USER_ADDRESS_COUNTRY_COLUMN =
      "address_country";

  // User table account active column.
  public static final String USER_ACCOUNT_ACTIVE_COLUMN =
      "account_active";

  // Blood donation request table name.
  public static final String BLOOD_DONATION_REQUEST_TABLE =
      "blood_donation_request";

  // Blood donation request table unique id column.
  public static final String BLOOD_DONATION_REQUEST_ID_COLUMN =
      "request_id";

  // Blood donation request table unique user id column.
  public static final String BLOOD_DONATION_USER_ID_COLUMN =
      "user_id";

  // Blood donation request table status change date column.
  public static final String STATUS_CHANGE_DATE_COLUMN =
      "status_change_date";

  // Blood donation request table status column.
  public static final String STATUS_COLUMN =
      "status";

  // Blood donation request table status active constant.
  public static final String STATUS_ACTIVE =
      "active";

  // Blood donation request table status fulfilled constant.
  public static final String STATUS_FULFILLED =
      "fulfilled";
}
