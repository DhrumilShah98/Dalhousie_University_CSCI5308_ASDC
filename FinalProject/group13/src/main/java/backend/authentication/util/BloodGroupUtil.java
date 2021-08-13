package backend.authentication.util;

/**
 * {@code BloodGroupUtil} is a utility class that holds all blood groups.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodGroupUtil {

  // A+ blood group type.
  public static final String A_POSITIVE = "A+";

  // A- blood group type.
  public static final String A_NEGATIVE = "A-";

  // B+ blood group type.
  public static final String B_POSITIVE = "B+";

  // B- blood group type.
  public static final String B_NEGATIVE = "B-";

  // AB+ blood group type.
  public static final String AB_POSITIVE = "AB+";

  // AB- blood group type.
  public static final String AB_NEGATIVE = "AB-";

  // O+ blood group type.
  public static final String O_POSITIVE = "O+";

  // O- blood group type.
  public static final String O_NEGATIVE = "O-";

  /**
   * Validates the {@code bloodGroup} argument.
   *
   * @param bloodGroup blood group type.
   *
   * @return {@code true} if blood group is valid otherwise {@code false}.
   */
  public static boolean isBloodGroupValid(final String bloodGroup) {
    if (bloodGroup == null) {
      return false;
    }
    switch (bloodGroup) {
      case A_POSITIVE:
      case A_NEGATIVE:
      case B_POSITIVE:
      case B_NEGATIVE:
      case AB_POSITIVE:
      case AB_NEGATIVE:
      case O_POSITIVE:
      case O_NEGATIVE:
        return true;
      default:
        return false;
    }
  }
}