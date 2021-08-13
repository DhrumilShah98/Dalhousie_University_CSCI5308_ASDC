package backend.blood_bank_camp.util;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class CampRegistrationValidation {

  public static boolean isOrganizerNameValid(String organizerName) {
    if (organizerName == null) {
      return false;
    }
    return Pattern.matches("[A-Za-z ]+", organizerName);
  }

  public static boolean isDateFormatValid(String date) {
    if (date == null) {
      return false;
    }
    try {
      final String[] dateArr = date.split("-");
      if (dateArr[0].length() != 4) {
        return false;
      }
      if (dateArr[1].length() != 2) {
        return false;
      }
      if (dateArr[2].length() != 2) {
        return false;
      }
      final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      simpleDateFormat.setLenient(false);
      return simpleDateFormat.parse(date) != null;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isTimeValid(String time) {
    if (time == null) {
      return false;
    }
    return Pattern.matches("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$", time);

  }

  public static boolean isVenueNameValid(String venue) {
    if (venue == null) {
      return false;
    }
    return Pattern.matches("^[#.0-9a-zA-Z\\s,-]+$", venue);
  }

  public static boolean isContactNumberValid(String contactNumber) {
    if (contactNumber == null) {
      return false;
    }
    return Pattern.matches("^(902)[\\d]{7}$", contactNumber);
  }


}
