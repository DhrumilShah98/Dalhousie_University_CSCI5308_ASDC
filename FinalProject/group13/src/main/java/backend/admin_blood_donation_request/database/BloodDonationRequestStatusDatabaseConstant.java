package backend.admin_blood_donation_request.database;

/**
 * {@code BloodDonationRequestStatusDatabaseConstant } stores the information
 * related to the blood donor request database.
 */
public class BloodDonationRequestStatusDatabaseConstant {

  // Blood donor request table
  public static final String BLOOD_DONATION_REQUEST_TABLE = "blood_donation_request";

  //  Blood donor request id column
  public static final String BLOOD_DONATION_REQUEST_ID_COLUMN = "request_id";

  // Blood donor user id column
  public static final String USER_ID_COLUMN = "user_id";

  // Blood donor request date column
  public static final String REQUEST_DATE_COLUMN = "request_date";

  // Blood donor status change date column
  public static final String STATUS_CHANGE_DATE_COLUMN = "status_change_date";

  // Blood donor status column
  public static final String STATUS_COLUMN = "status";

  // Blood donor request state
  public static final String STATUS_REQUEST = "request";

  // Blood donor active state
  public static final String STATUS_ACTIVE = "active";

  // Blood donor fulfilled state
  public static final String STATUS_FULFILLED = "fulfilled";

  // Blood donor rejected state
  public static final String STATUS_REJECTED = "rejected";
}
