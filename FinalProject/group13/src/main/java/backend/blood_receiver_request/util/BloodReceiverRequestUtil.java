package backend.blood_receiver_request.util;

import backend.authentication.util.BloodGroupUtil;

import java.util.regex.Pattern;

public class BloodReceiverRequestUtil {

  public static boolean isBloodGroupValid(String bloodGroup) {
    return BloodGroupUtil.isBloodGroupValid(bloodGroup);
  }

  public static boolean isQuantityValid(String quantity) {
    if (quantity == null) {
      return false;
    }
    return Pattern.matches("[0-9 ]+", quantity);
  }

  public static boolean isStatusValid(String status) {
    return BloodRequestStatusUtil.isRequestStatusValid(status);
  }
}
