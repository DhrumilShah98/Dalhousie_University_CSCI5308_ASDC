package backend.blood_donation_request.database;

/**
 * {@code BloodDonationRequestDatabaseConstant} stores the information related
 * to the blood donation request database.
 */
public class BloodDonationRequestDatabaseConstant {

  // Blood donation request table.
  public static final String BLOOD_DONATION_REQUEST_TABLE =
      "blood_donation_request";

  // Blood donation request unique id column.
  public static final String BLOOD_DONATION_REQUEST_ID_COLUMN =
      "request_id";

  // Blood donation request user unique id column.
  public static final String USER_ID_COLUMN =
      "user_id";

  // Blood donation request table's request date column.
  public static final String REQUEST_DATE_COLUMN =
      "request_date";

  // Blood donation request table's status change date column.
  public static final String STATUS_CHANGE_DATE_COLUMN =
      "status_change_date";

  // Blood donation request status column.
  public static final String STATUS_COLUMN =
      "status";

  // Blood donation request  certificate unique id column.
  public static final String CERTIFICATE_ID_COLUMN =
      "certificate_id";

  // Blood donation request active status constant.
  public static final String STATUS_ACTIVE =
      "active";

  // Blood donation request table's request status constant.
  public static final String STATUS_REQUEST =
      "request";
}
