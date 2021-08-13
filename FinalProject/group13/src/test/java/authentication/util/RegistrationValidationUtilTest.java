package authentication.util;

import backend.authentication.util.RegistrationValidationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Registration validation test suite")
public class RegistrationValidationUtilTest {

    @Test
    @DisplayName("Validate incorrect first name")
    public void validateIncorrectFirstName() {
        final String firstName1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(firstName1), firstName1 + " first name 1 is correct.");
        final String firstName2 = "Viraj007";
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(firstName2), firstName2 + " first name 2 is correct.");
        final String firstName3 = "007Priya";
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(firstName3), firstName3 + " first name 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct first name")
    public void validateCorrectFirstName() {
        final String firstName1 = "Dhrumil";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(firstName1), firstName1 + " first name 1 is incorrect.");
        final String firstName2 = "Dhrumil Rakesh";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(firstName2), firstName2 + " first name 2 is incorrect.");
        final String firstName3 = "Priya Arunkumar";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(firstName3), firstName3 + " first name 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate incorrect last name")
    public void validateIncorrectLastName() {
        final String lastName1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isLastNameValid(lastName1), lastName1 + " last name 1 is correct.");
        final String lastName2 = "Shah007";
        Assertions.assertFalse(RegistrationValidationUtil.isLastNameValid(lastName2), lastName2 + " last name 2 is correct.");
        final String lastName3 = "007Savaliya";
        Assertions.assertFalse(RegistrationValidationUtil.isLastNameValid(lastName3), lastName3 + " last name 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct last name")
    public void validateCorrectLastName() {
        final String lastName1 = "Shah";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(lastName1), lastName1 + " last name 1 is incorrect.");
        final String lastName2 = "Savaliya";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(lastName2), lastName2 + " last name 2 is incorrect.");
        final String lastName3 = "Gokul";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(lastName3), lastName3 + " last name 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate incorrect gender")
    public void validateIncorrectGender(){
        final String gender1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isGenderValid(gender1), gender1 + " gender 1 is correct.");
        final String gender2 = "B";
        Assertions.assertFalse(RegistrationValidationUtil.isGenderValid(gender2), gender2 + " gender 2 is correct.");
        final String gender3 = "G";
        Assertions.assertFalse(RegistrationValidationUtil.isGenderValid(gender3), gender3 + " gender 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct gender")
    public void validateCorrectGender(){
        final String gender1 = "M";
        Assertions.assertTrue(RegistrationValidationUtil.isGenderValid(gender1), gender1 + " gender 1 is incorrect.");
        final String gender2 = "F";
        Assertions.assertTrue(RegistrationValidationUtil.isGenderValid(gender2), gender2 + " gender 2 is incorrect.");
        final String gender3 = "O";
        Assertions.assertTrue(RegistrationValidationUtil.isGenderValid(gender3), gender3 + " gender 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate incorrect email")
    public void validateIncorrectEmail(){
        final String email1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isEmailValid(email1), email1 + " email 1 is correct");
        final String email2 = "dhrumil@gmailcom";
        Assertions.assertFalse(RegistrationValidationUtil.isEmailValid(email2), email2 + " email 2 is correct");
        final String email3 = "dhrumil.shah@gmailcom";
        Assertions.assertFalse(RegistrationValidationUtil.isEmailValid(email3), email3 + " email 3 is correct");
        final String email4 = "dhrumil.shahgmail.com";
        Assertions.assertFalse(RegistrationValidationUtil.isEmailValid(email4), email4 + " email 4 is correct");
        final String email5 = "dhrumil.shah@gmail.c";
        Assertions.assertFalse(RegistrationValidationUtil.isEmailValid(email4), email5 + " email 5 is correct");
    }

    @Test
    @DisplayName("Validate correct email")
    public void validateCorrectEmail(){
        final String email1 = "dhrumil@gmail.com";
        Assertions.assertTrue(RegistrationValidationUtil.isEmailValid(email1), email1 + " email 1 is incorrect");
        final String email2 = "dhrumilrakesh.shah@gmail.com";
        Assertions.assertTrue(RegistrationValidationUtil.isEmailValid(email2), email2 + " email 2 is incorrect");
        final String email3 = "priya.savaliya@gmail.com";
        Assertions.assertTrue(RegistrationValidationUtil.isEmailValid(email3), email3 + " email 3 is incorrect");
    }

    @Test
    @DisplayName("Validate incorrect password")
    public void validateIncorrectPassword(){
        final String password1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password1), password1 + " password 1 is correct");
        final String password2 = "dhrumil123";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password2), password2 + " password 2 is correct");
        final String password3 = "dhrumil@";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password3), password3 + " password 3 is correct");
        final String password4 = "dhrumilShah";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password4), password4 + " password 4 is correct");
        final String password5 = "DhrumilShah";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password5), password5 + " password 5 is correct");
        final String password6 = "D@as1";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password6), password6 + " password 6 is correct");
        final String password7 = "DhrumilShah@12345678910";
        Assertions.assertFalse(RegistrationValidationUtil.isPasswordValid(password7), password7 + " password 7 is correct");
    }

    @Test
    @DisplayName("Validate correct password")
    public void validateCorrectPassword(){
        final String password1 = "Dhrumil@123";
        Assertions.assertTrue(RegistrationValidationUtil.isPasswordValid(password1), password1 + " password 1 is incorrect");
        final String password2 = "D@hrumil123";
        Assertions.assertTrue(RegistrationValidationUtil.isPasswordValid(password2), password2 + " password 2 is incorrect");
        final String password3 = "Dhrumil123@";
        Assertions.assertTrue(RegistrationValidationUtil.isPasswordValid(password3), password3 + " password 3 is incorrect");
    }

    @Test
    @DisplayName("Validate incorrect date format")
    public void validateIncorrectDateFormat(){
        final String dateFormat1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isDateFormatValid(dateFormat1), dateFormat1 + " date format 1 is correct");
        final String dateFormat2 = "18-04-1998";
        Assertions.assertFalse(RegistrationValidationUtil.isDateFormatValid(dateFormat2), dateFormat2 + " date format 2 is correct");
        final String dateFormat3 = "1998-18-04";
        Assertions.assertFalse(RegistrationValidationUtil.isDateFormatValid(dateFormat3), dateFormat3 + " date format 3 is correct");
        final String dateFormat4 = "1998-02-30";
        Assertions.assertFalse(RegistrationValidationUtil.isDateFormatValid(dateFormat4), dateFormat4 + " date format 4 is correct");
    }

    @Test
    @DisplayName("Validate correct date format")
    public void validateCorrectDateFormat(){
        final String dateFormat1 = "1998-04-18";
        Assertions.assertTrue(RegistrationValidationUtil.isDateFormatValid(dateFormat1), dateFormat1 + " date format 1 is incorrect");
        final String dateFormat2 = "1998-02-28";
        Assertions.assertTrue(RegistrationValidationUtil.isDateFormatValid(dateFormat2), dateFormat2 + " date format 2 is incorrect");
        final String dateFormat3 = "2000-02-29";
        Assertions.assertTrue(RegistrationValidationUtil.isDateFormatValid(dateFormat3), dateFormat3 + " date format 3 is incorrect");
    }

    @Test
    @DisplayName("Validate incorrect age")
    public void validateIncorrectAge(){
        final String dateFormat1Age = null;
        Assertions.assertFalse(RegistrationValidationUtil.isAgeValid(dateFormat1Age), dateFormat1Age + " date format 1 age is correct");
        final String dateFormat2Age = "2010-02-22";
        Assertions.assertFalse(RegistrationValidationUtil.isAgeValid(dateFormat2Age), dateFormat2Age + " date format 2 age is correct");
        final String dateFormat3Age = "2013-07-09";
        Assertions.assertFalse(RegistrationValidationUtil.isAgeValid(dateFormat3Age), dateFormat3Age + " date format 3 age is correct");
    }

    @Test
    @DisplayName("Validate correct age")
    public void validateCorrectAge(){
        final String dateFormat1Age = "1998-04-18";
        Assertions.assertTrue(RegistrationValidationUtil.isAgeValid(dateFormat1Age), dateFormat1Age + " date format 1 age is incorrect");
        final String dateFormat2Age = "1995-02-22";
        Assertions.assertTrue(RegistrationValidationUtil.isAgeValid(dateFormat2Age), dateFormat2Age + " date format 2 age is incorrect");
        final String dateFormat3Age = "2002-07-08";
        Assertions.assertTrue(RegistrationValidationUtil.isAgeValid(dateFormat3Age), dateFormat3Age + " date format 3 age is incorrect");
    }

    @Test
    @DisplayName("Validate incorrect contact number")
    public void validateIncorrectContactNumber(){
        final String contactNumber1 = "9032939303";
        Assertions.assertFalse(RegistrationValidationUtil.isContactNumberValid(contactNumber1), contactNumber1 + " contact number 1 age is correct");
        final String contactNumber2 = "8032383939";
        Assertions.assertFalse(RegistrationValidationUtil.isContactNumberValid(contactNumber2), contactNumber2 + " contact number 2 age is correct");
        final String contactNumber3 = "9824588888";
        Assertions.assertFalse(RegistrationValidationUtil.isContactNumberValid(contactNumber3), contactNumber3 + " contact number 3 age is correct");
        final String contactNumber4 = "1237483939";
        Assertions.assertFalse(RegistrationValidationUtil.isContactNumberValid(contactNumber4), contactNumber4 + " contact number 4 age is correct");
        final String contactNumber5 = "9122833849";
        Assertions.assertFalse(RegistrationValidationUtil.isContactNumberValid(contactNumber5), contactNumber5 + " contact number 5 age is correct");
    }

    @Test
    @DisplayName("Validate correct contact number")
    public void validateCorrectContactNumber(){
        final String contactNumber1 = "9025299754";
        Assertions.assertTrue(RegistrationValidationUtil.isContactNumberValid(contactNumber1), contactNumber1 + " contact number 1 age is incorrect");
        final String contactNumber2 = "9020045893";
        Assertions.assertTrue(RegistrationValidationUtil.isContactNumberValid(contactNumber2), contactNumber2 + " contact number 2 age is incorrect");
        final String contactNumber3 = "9021338554";
        Assertions.assertTrue(RegistrationValidationUtil.isContactNumberValid(contactNumber3), contactNumber3 + " contact number 3 age is incorrect");
    }

    @Test
    @DisplayName("Validate incorrect blood group")
    public void validateIncorrectBloodGroup(){
        final String bg1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isBloodGroupValid(bg1), bg1 + " blood group 1 is correct.");
        final String bg2 = "AC+";
        Assertions.assertFalse(RegistrationValidationUtil.isBloodGroupValid(bg2), bg2 + " blood group 2 is correct.");
        final String bg3 = "AC-";
        Assertions.assertFalse(RegistrationValidationUtil.isBloodGroupValid(bg3), bg3 + " blood group 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct blood group")
    public void validateCorrectBloodGroup(){
        final String bg1 = "A+";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg1), bg1 + " blood group 1 is incorrect.");
        final String bg2 = "A-";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg2), bg2 + " blood group 2 is incorrect.");
        final String bg3 = "B+";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg3), bg3 + " blood group 3 is incorrect.");
        final String bg4 = "B-";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg4), bg4 + " blood group 4 is incorrect.");
        final String bg5 = "AB+";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg5), bg5 + " blood group 5 is incorrect.");
        final String bg6 = "AB-";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg6), bg6 + " blood group 6 is incorrect.");
        final String bg7 = "O+";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg7), bg7 + " blood group 7 is incorrect.");
        final String bg8 = "O-";
        Assertions.assertTrue(RegistrationValidationUtil.isBloodGroupValid(bg8), bg8 + " blood group 8 is incorrect.");
    }

    @Test
    @DisplayName("Validate incorrect zip code")
    public void validateIncorrectZipCode(){
        final String zipCode1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode1), zipCode1 + " zip code 1 is incorrect.");
        final String zipCode2 = "B4V2@8";
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode2), zipCode2 + " zip code 2 is incorrect.");
        final String zipCode3 = "B4V 2V8";
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode3), zipCode3 + " zip code 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate correct zip code")
    public void validateCorrectZipCode(){
        final String zipCode1 = "B4V208";
        Assertions.assertTrue(RegistrationValidationUtil.isZipCodeValid(zipCode1), zipCode1 + " zip code 1 is correct.");
        final String zipCode2 = "B4V2V8";
        Assertions.assertTrue(RegistrationValidationUtil.isZipCodeValid(zipCode2), zipCode2 + " zip code 2 is correct.");
    }

    @Test
    @DisplayName("Validate incorrect blood bank name")
    public void validateIncorrectBloodBankName(){
        final String bloodBank1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isNameValid(bloodBank1), bloodBank1 + " blood bank 1 is correct.");
        final String bloodBank2 = "Red cross @";
        Assertions.assertFalse(RegistrationValidationUtil.isNameValid(bloodBank2), bloodBank2 + " blood bank 2 is correct.");
        final String bloodBank3 = "Save~ Life";
        Assertions.assertFalse(RegistrationValidationUtil.isNameValid(bloodBank3), bloodBank3 + " blood bank 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct blood bank name")
    public void validateCorrectBloodBankName(){
        final String bloodBank1 = "Red Cross";
        Assertions.assertTrue(RegistrationValidationUtil.isNameValid(bloodBank1), bloodBank1 + " blood bank 1 is incorrect.");
        final String bloodBank2 = "Save Life";
        Assertions.assertTrue(RegistrationValidationUtil.isNameValid(bloodBank2), bloodBank2 + " blood bank 2 is incorrect.");
        final String bloodBank3 = "Tris 09 Blood Bank";
        Assertions.assertTrue(RegistrationValidationUtil.isNameValid(bloodBank3), bloodBank3 + " blood bank 3 is incorrect.");
    }
}