package backend.blood_receiver_request.util;

public final class BloodRequestStatusUtil {
  public static final String ACTIVE_STATUS = "active";
  public static final String REQUEST_STATUS = "request";
  public static final String FULFILL_STATUS = "fulfilled";
  public static final String REJECT_STATUS = "rejected";

  public static boolean isRequestStatusValid(String status) {
    if (status == null) {
      return false;
    }
    switch (status) {
      case ACTIVE_STATUS:
      case REQUEST_STATUS:
      case FULFILL_STATUS:
      case REJECT_STATUS:
        return true;
      default:
        return false;
    }

  }
}
