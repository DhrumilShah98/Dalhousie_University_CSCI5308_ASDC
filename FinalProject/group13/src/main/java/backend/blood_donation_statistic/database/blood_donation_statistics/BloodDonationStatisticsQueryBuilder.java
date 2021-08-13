package backend.blood_donation_statistic.database.blood_donation_statistics;

import static backend.blood_donation_statistic.database.BloodDonationStatisticsDatabaseConstant.*;

/**
 * {@code BloodDonationStatisticsQueryBuilder} implements the
 * {@code BloodDonationStatisticsQueryBuilderDAO} to provide a concrete
 * implementation for viewing blood donation request and user tables.
 * This class is implemented using the Singleton Design Pattern.
 */
public class BloodDonationStatisticsQueryBuilder
    implements BloodDonationStatisticsQueryBuilderDAO {

  // Static instance of this class
  private static BloodDonationStatisticsQueryBuilder instance;

  /**
   * Constructs this {@code BloodDonationStatisticsQueryBuilder} instance.
   */
  private BloodDonationStatisticsQueryBuilder() {
    // required empty private constructor
  }

  /**
   * Instantiates this {@code BloodDonationStatisticsQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodDonationStatisticsQueryBuilder} class.
   */
  public static BloodDonationStatisticsQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodDonationStatisticsQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select blood donation statistics.
   *
   * @return string query to select blood donation statistics.
   */
  @Override
  public String selectBloodDonationStatisticsQuery() {
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
        ", " +
        "bdr." + STATUS_CHANGE_DATE_COLUMN +
        ", " +
        "bdr." + STATUS_COLUMN +
        " FROM " +
        USER_TABLE +
        " AS u, " +
        BLOOD_DONATION_REQUEST_TABLE +
        " AS bdr " +
        "WHERE " +
        "u." + USER_ID_COLUMN + " = bdr." + BLOOD_DONATION_USER_ID_COLUMN + ";";
  }
}
