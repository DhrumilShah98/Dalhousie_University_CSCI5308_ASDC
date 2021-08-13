package backend.authentication.util;

/**
 * {@code GenderUtil} is a utility class that holds all genders.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class GenderUtil {

  // Male gender.
  public static final String MALE = "M";

  // Female gender.
  public static final String FEMALE = "F";

  // Other gender.
  public static final String OTHER = "O";

  /**
   * Validates the {@code gender} argument.
   *
   * @param gender gender type.
   *
   * @return {@code true} if gender is valid otherwise {@code false}.
   */
  public static boolean isGenderValid(final String gender) {
    if (gender == null) {
      return false;
    }
    switch (gender) {
      case MALE:
      case FEMALE:
      case OTHER:
        return true;
      default:
        return false;
    }
  }
}