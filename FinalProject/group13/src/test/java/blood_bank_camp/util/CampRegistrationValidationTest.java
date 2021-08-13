package blood_bank_camp.util;

import backend.blood_bank_camp.util.CampRegistrationValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Camp Registration validation test ")
public class CampRegistrationValidationTest {
  @Test
  @DisplayName("Validate incorrect organizer name")
  public void validateIncorrectOrganizerName() {
    final String organizerName1 = null;
    Assertions.assertFalse(CampRegistrationValidation.isOrganizerNameValid(organizerName1), organizerName1 + " organizer name 1 is correct.");
    final String organizerName2 = "Blood-+";
    Assertions.assertFalse(CampRegistrationValidation.isOrganizerNameValid(organizerName2), organizerName2 + " organizer name 2 is correct.");
    final String organizerName3 = "007blood";
    Assertions.assertFalse(CampRegistrationValidation.isOrganizerNameValid(organizerName3), organizerName3 + " organizer name 3 is correct.");
  }

  @Test
  @DisplayName("Validate correct organizer name")
  public void validateCorrectOrganizerName() {
    final String organizerName1 = "Blood Book";
    Assertions.assertTrue(CampRegistrationValidation.isOrganizerNameValid(organizerName1), organizerName1 + " organizer name 1 is incorrect.");
    final String organizerName2 = "Blood Services";
    Assertions.assertTrue(CampRegistrationValidation.isOrganizerNameValid(organizerName2), organizerName2 + " organizer name 2 is incorrect.");
    final String organizerName3 = "Blood life";
    Assertions.assertTrue(CampRegistrationValidation.isOrganizerNameValid(organizerName3), organizerName3 + " organizer name 3 is incorrect.");
  }

  @Test
  @DisplayName("Validate incorrect date format")
  public void validateIncorrectDateFormat() {
    final String dateFormat1 = null;
    Assertions.assertFalse(CampRegistrationValidation.isDateFormatValid(dateFormat1), dateFormat1 + " date format 1 is correct");
    final String dateFormat2 = "18-04-1998";
    Assertions.assertFalse(CampRegistrationValidation.isDateFormatValid(dateFormat2), dateFormat2 + " date format 2 is correct");
    final String dateFormat3 = "1998-18-04";
    Assertions.assertFalse(CampRegistrationValidation.isDateFormatValid(dateFormat3), dateFormat3 + " date format 3 is correct");
    final String dateFormat4 = "1998-02-30";
    Assertions.assertFalse(CampRegistrationValidation.isDateFormatValid(dateFormat4), dateFormat4 + " date format 4 is correct");
  }

  @Test
  @DisplayName("Validate correct date format")
  public void validateCorrectDateFormat() {
    final String dateFormat1 = "1998-04-18";
    Assertions.assertTrue(CampRegistrationValidation.isDateFormatValid(dateFormat1), dateFormat1 + " date format 1 is incorrect");
    final String dateFormat2 = "1998-02-28";
    Assertions.assertTrue(CampRegistrationValidation.isDateFormatValid(dateFormat2), dateFormat2 + " date format 2 is incorrect");
    final String dateFormat3 = "2000-02-29";
    Assertions.assertTrue(CampRegistrationValidation.isDateFormatValid(dateFormat3), dateFormat3 + " date format 3 is incorrect");
  }

  @Test
  @DisplayName("Validate incorrect time format")
  public void validateIncorrectTimeFormat() {
    final String timeFormat1 = null;
    Assertions.assertFalse(CampRegistrationValidation.isTimeValid(timeFormat1), timeFormat1 + " time format 1 is correct");
    final String timeFormat2 = "24:00";
    Assertions.assertFalse(CampRegistrationValidation.isTimeValid(timeFormat2), timeFormat2 + " time format 2 is correct");
    final String timeFormat3 = "00:80";
    Assertions.assertFalse(CampRegistrationValidation.isTimeValid(timeFormat3), timeFormat3 + " time format 3 is correct");
    final String timeFormat4 = "80:00";
    Assertions.assertFalse(CampRegistrationValidation.isTimeValid(timeFormat4), timeFormat4 + " time format 4 is correct");
  }

  @Test
  @DisplayName("Validate correct time format")
  public void validateCorrectTimeFormat() {
    final String timeFormat1 = "15:59";
    Assertions.assertTrue(CampRegistrationValidation.isTimeValid(timeFormat1), timeFormat1 + " time format 1 is Incorrect");
    final String timeFormat2 = "23:59";
    Assertions.assertTrue(CampRegistrationValidation.isTimeValid(timeFormat2), timeFormat2 + " time format 2 is Incorrect");
    final String timeFormat3 = "00:00";
    Assertions.assertTrue(CampRegistrationValidation.isTimeValid(timeFormat3), timeFormat3 + " time format 3 is Incorrect");
    final String timeFormat4 = "00:01";
    Assertions.assertTrue(CampRegistrationValidation.isTimeValid(timeFormat4), timeFormat4 + " time format 4 is correct");
  }

  @Test
  @DisplayName("Validate incorrect Venue name")
  public void validateIncorrectVenueName() {
    final String venueName1 = null;
    Assertions.assertFalse(CampRegistrationValidation.isVenueNameValid(venueName1), venueName1 + " venue name 1 is correct.");
    final String venueName2 = "Street/ road";
    Assertions.assertFalse(CampRegistrationValidation.isVenueNameValid(venueName2), venueName2 + " venue name 2 is correct.");
    final String venueName3 = "1925 %Bowen Rd.";
    Assertions.assertFalse(CampRegistrationValidation.isVenueNameValid(venueName3), venueName3 + " venue name 3 is correct.");
  }

  @Test
  @DisplayName("Validate correct Venue name")
  public void validateCorrectVenueName() {
    final String venueName1 = "#Street Rd.";
    Assertions.assertTrue(CampRegistrationValidation.isVenueNameValid(venueName1), venueName1 + " venue name 1 is Incorrect.");
    final String venueName2 = "Street road 259";
    Assertions.assertTrue(CampRegistrationValidation.isVenueNameValid(venueName2), venueName2 + " venue name 2 is Incorrect.");
    final String venueName3 = "1925-Bowen Rd.";
    Assertions.assertTrue(CampRegistrationValidation.isVenueNameValid(venueName3), venueName3 + " venue name 3 is Incorrect.");
  }

  @Test
  @DisplayName("Validate incorrect contact number")
  public void validateIncorrectContactNumber() {
    final String contactNumber1 = "9032939303";
    Assertions.assertFalse(CampRegistrationValidation.isContactNumberValid(contactNumber1), contactNumber1 + " contact number 1 age is correct");
    final String contactNumber2 = "8032383939";
    Assertions.assertFalse(CampRegistrationValidation.isContactNumberValid(contactNumber2), contactNumber2 + " contact number 2 age is correct");
    final String contactNumber3 = "9824588888";
    Assertions.assertFalse(CampRegistrationValidation.isContactNumberValid(contactNumber3), contactNumber3 + " contact number 3 age is correct");
  }

  @Test
  @DisplayName("Validate correct contact number")
  public void validateCorrectContactNumber() {
    final String contactNumber1 = "9025299754";
    Assertions.assertTrue(CampRegistrationValidation.isContactNumberValid(contactNumber1), contactNumber1 + " contact number 1 age is incorrect");
    final String contactNumber2 = "9020045893";
    Assertions.assertTrue(CampRegistrationValidation.isContactNumberValid(contactNumber2), contactNumber2 + " contact number 2 age is incorrect");
    final String contactNumber3 = "9021338554";
    Assertions.assertTrue(CampRegistrationValidation.isContactNumberValid(contactNumber3), contactNumber3 + " contact number 3 age is incorrect");
  }
}
