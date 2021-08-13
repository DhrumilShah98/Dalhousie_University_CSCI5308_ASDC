package backend.blood_donation_request.database.view_previous_blood_donation_request;

import static backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant.*;

/**
 * {@code ViewPreviousBloodDonationRequestQueryBuilder} implements the
 * {@code ViewPreviousBloodDonationRequestQueryBuilderDAO} to provide a concrete
 * implementation for viewing all previous blood donation requests for this
 * user.
 * This class is implemented using the Singleton Design Pattern.
 */
public class ViewPreviousBloodDonationRequestQueryBuilder
    implements ViewPreviousBloodDonationRequestQueryBuilderDAO {

  // Static instance of this class
  private static ViewPreviousBloodDonationRequestQueryBuilder instance;

  /**
   * Constructs this {@code ViewPreviousBloodDonationRequestQueryBuilder} instance.
   */
  private ViewPreviousBloodDonationRequestQueryBuilder() {
    // required empty private constructor
  }

  /**
   * Instantiates this {@code ViewPreviousBloodDonationRequestQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ViewPreviousBloodDonationRequestQueryBuilder} class.
   */
  public static ViewPreviousBloodDonationRequestQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ViewPreviousBloodDonationRequestQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select previous blood donation requests of this user.
   *
   * @param userId unique id of this user.
   *
   * @return string query to select previous blood donation requests of this
   * user.
   */
  @Override
  public String selectPreviousBloodDonationQuery(final int userId) {
    return "SELECT " +
        BLOOD_DONATION_REQUEST_ID_COLUMN +
        ", " +
        USER_ID_COLUMN +
        ", " +
        REQUEST_DATE_COLUMN +
        ", " +
        STATUS_CHANGE_DATE_COLUMN +
        ", " +
        STATUS_COLUMN +
        ", " +
        CERTIFICATE_ID_COLUMN +
        " FROM " +
        BLOOD_DONATION_REQUEST_TABLE +
        " WHERE " +
        USER_ID_COLUMN + " = " + userId +
        " ORDER BY " +
        BLOOD_DONATION_REQUEST_ID_COLUMN +
        " DESC LIMIT 1;";
  }
}
