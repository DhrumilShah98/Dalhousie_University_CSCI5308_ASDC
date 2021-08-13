package backend.blood_receiver_request.database;

/**
 * {@code BloodReceiverRequestDatabaseConstant} stores the information related to the
 * blood receiver request database.
 */
public class BloodReceiverRequestDatabaseConstant {

  // Blood receiver request table.
  public static final String BLOOD_RECEIVER_REQUEST_TABLE =
      "blood_receiver_request";

  //  Blood receiver request unique id column.
  public static final String REQUEST_ID_COLUMN =
      "request_id";

  //  Blood receiver request user unique id column.
  public static final String USER_ID_COLUMN =
      "user_id";

  // Blood receiver request blood group column.
  public static final String BLOOD_GROUP_COLUMN =
      "blood_group";

  // Blood receiver request blood quantity column.
  public static final String QUANTITY_COLUMN =
      "quantity";

  // Blood receiver request date column.
  public static final String DATE_REQUEST_COLUMN =
      "request_date";

  // Blood receiver request status column.
  public static final String STATUS_COLUMN =
      "status";

  // Blood receiver request status change column.
  public static final String STATUS_CHANGED_COLUMN =
      "status_change_date";
}