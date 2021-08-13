package backend.authentication.util;

import backend.authentication.util.BloodGroupUtil;
import backend.authentication.util.GenderUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * {@code RegistrationValidationUtil} is a utility class that handles all
 * the validation related business logic.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class RegistrationValidationUtil {

  /**
   * Validates the {@code firstName} argument.
   *
   * @param firstName first name.
   *
   * @return {@code true} if first name is valid otherwise {@code false}.
   */
  public static boolean isFirstNameValid(final String firstName) {
    if (firstName == null) {
      return false;
    }
    return Pattern.matches("[A-Za-z ]+", firstName);
  }

  /**
   * Validates the {@code lastName} argument.
   *
   * @param lastName last name.
   *
   * @return {@code true} if last name is valid otherwise {@code false}.
   */
  public static boolean isLastNameValid(final String lastName) {
    if (lastName == null) {
      return false;
    }
    return Pattern.matches("[A-Za-z]+", lastName);
  }

  /**
   * Validates the {@code gender} argument.
   *
   * @param gender gender type.
   *
   * @return {@code true} if gender is valid otherwise {@code false}.
   */
  public static boolean isGenderValid(final String gender) {
    return GenderUtil.isGenderValid(gender);
  }

  /**
   * Validates the format of {@code date} argument.
   *
   * @param date date (yyyy-MM-dd).
   *
   * @return {@code true} if date is valid otherwise {@code false}.
   */
  public static boolean isDateFormatValid(final String date) {
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
      final SimpleDateFormat simpleDateFormat =
          new SimpleDateFormat("yyyy-MM-dd");
      simpleDateFormat.setLenient(false);
      return simpleDateFormat.parse(date) != null;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Validates the age by calculating from {@code date} argument.
   *
   * @param date date (yyyy-MM-dd).
   *
   * @return {@code true} if date is valid otherwise {@code false}.
   */
  public static boolean isAgeValid(final String date) {
    final boolean isDateFormatValid = isDateFormatValid(date);
    if (isDateFormatValid) {
      final LocalDate birthDate =
          LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      final LocalDate currentDate = LocalDate.now();
      return Period.between(birthDate, currentDate).getYears() >= 18;
    } else {
      return false;
    }
  }

  /**
   * Validates the {@code email} argument.
   *
   * @param email email.
   *
   * @return {@code true} if email is valid otherwise {@code false}.
   */
  public static boolean isEmailValid(final String email) {
    if (email == null) {
      return false;
    }
    return Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
        email);
  }

  /**
   * Validates the {@code password} argument.
   *
   * @param password password.
   *
   * @return {@code true} if password is valid otherwise {@code false}.
   */
  public static boolean isPasswordValid(final String password) {
    if (password == null) {
      return false;
    }
    return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
        password);
  }

  /**
   * Validates the {@code contactNumber} argument.
   *
   * @param contactNumber contact number.
   *
   * @return {@code true} if contact number is valid otherwise {@code false}.
   */
  public static boolean isContactNumberValid(final String contactNumber) {
    if (contactNumber == null) {
      return false;
    }
    return Pattern.matches("^(902)[\\d]{7}$", contactNumber);
  }

  /**
   * Validates the {@code bloodGroup} argument.
   *
   * @param bloodGroup blood group type.
   *
   * @return {@code true} if blood group is valid otherwise {@code false}.
   */
  public static boolean isBloodGroupValid(final String bloodGroup) {
    return BloodGroupUtil.isBloodGroupValid(bloodGroup);
  }

  /**
   * Validates the {@code zipCode} argument.
   *
   * @param zipCode zip code.
   *
   * @return {@code true} if zip code is valid otherwise {@code false}.
   */
  public static boolean isZipCodeValid(final String zipCode) {
    if (zipCode == null) {
      return false;
    }
    return Pattern.matches("^[A-Za-z\\d]{6}$", zipCode);
  }

  /**
   * Validates the {@code name} argument.
   *
   * @param name name.
   *
   * @return {@code true} if name is valid otherwise {@code false}.
   */
  public static boolean isNameValid(final String name) {
    if (name == null) {
      return false;
    }
    return Pattern.matches("[A-Za-z\\d ]+", name);
  }
}