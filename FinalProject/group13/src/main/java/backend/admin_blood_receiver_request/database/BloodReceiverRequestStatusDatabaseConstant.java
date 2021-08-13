package backend.admin_blood_receiver_request.database;

/**
 * {@code BloodReceiverRequestStatusDatabaseConstant } stores the information
 * related to the blood receiver request database.
 */
public class BloodReceiverRequestStatusDatabaseConstant {

    // Blood receiver request table
    public static final String BLOOD_RECEIVER_REQUEST_TABLE = "blood_receiver_request";

    //  Blood receiver request id column
    public static final String BLOOD_RECEIVER_REQUEST_ID_COLUMN = "request_id";

    // Blood receiver user id column
    public static final String USER_ID_COLUMN = "user_id";

    // Blood receiver request date column
    public static final String DATE_REQUEST_COLUMN = "request_date";

    // Blood receiver status change date column
    public static final String STATUS_CHANGED_DATE_COLUMN = "status_change_date";

    // Blood receiver status column
    public static final String STATUS_COLUMN = "status";

    // Blood receiver request state
    public static final String STATUS_REQUEST = "request";

    // Blood receiver active state
    public static final String STATUS_ACTIVE = "active";

    // Blood receiver fulfilled state
    public static final String STATUS_FULFILLED = "fulfilled";

    // Blood receiver rejected state
    public static final String STATUS_REJECTED = "rejected";


}
