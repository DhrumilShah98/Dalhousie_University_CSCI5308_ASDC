package backend.blood_donation_request.database.update_blood_donation_request_status;

import java.time.LocalDate;

import static backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant.*;

/**
 * {@code UpdateBloodDonationRequestStatusQueryBuilder} implements the
 * {@code UpdateBloodDonationRequestStatusQueryBuilderDAO} to provide a concrete
 * implementation for updating blood donation request status of this user.
 * This class is implemented using the Singleton Design Pattern.
 */
public class UpdateBloodDonationRequestStatusQueryBuilder
    implements UpdateBloodDonationRequestStatusQueryBuilderDAO {

  // Static instance of this class
  private static UpdateBloodDonationRequestStatusQueryBuilder instance;

  /**
   * Constructs this {@code UpdateBloodDonationRequestStatusQueryBuilder} instance.
   */
  private UpdateBloodDonationRequestStatusQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code UpdateBloodDonationRequestStatusQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UpdateBloodDonationRequestStatusQueryBuilder} class.
   */
  public static UpdateBloodDonationRequestStatusQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UpdateBloodDonationRequestStatusQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to update the blood donation request status of this user.
   *
   * @param userId unique id of this user.
   *
   * @return string query to update the blood donation request status of this
   * user.
   */
  @Override
  public String updateBloodDonationRequestStatusQuery(final int userId) {
    return "UPDATE " +
        BLOOD_DONATION_REQUEST_TABLE +
        " SET " +
        STATUS_COLUMN +
        " = \"" +
        STATUS_REQUEST +
        "\", " +
        STATUS_CHANGE_DATE_COLUMN +
        " = \"" +
        LocalDate.now() +
        "\"" +
        " WHERE " +
        USER_ID_COLUMN + " = " + userId +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_ACTIVE + "\";";
  }
}
