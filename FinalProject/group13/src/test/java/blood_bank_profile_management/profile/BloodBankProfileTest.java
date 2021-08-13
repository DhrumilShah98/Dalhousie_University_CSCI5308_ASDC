package blood_bank_profile_management.profile;

import backend.authentication.blood_bank.model.BloodBank;
import backend.authentication.util.RegistrationValidationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodBankProfileController class test suite")
public class BloodBankProfileTest {
    private static BloodBank bloodBank;

    @Test
    @DisplayName("Validate incorrect blood bank name")
    public void validateIncorrectBloodBankName() {
        final String bloodBankName1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(bloodBankName1), bloodBankName1 + " blood bank name 1 is correct.");
        final String bloodBankName2 = "Viraj007";
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(bloodBankName2), bloodBankName2 + " blood bank name 2 is correct.");
        final String bloodBankName3 = "007Priya";
        Assertions.assertFalse(RegistrationValidationUtil.isFirstNameValid(bloodBankName3), bloodBankName3 + " blood bank name 3 is correct.");
    }

    @Test
    @DisplayName("Validate correct blood bank name")
    public void validateCorrectFirstName() {
        final String bloodBankName1 = "Dhrumil";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(bloodBankName1), bloodBankName1 + " blood bank name 1 is incorrect.");
        final String bloodBankName2 = "Dhrumil Rakesh";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(bloodBankName2), bloodBankName2 + " blood bank name 2 is incorrect.");
        final String bloodBankName3 = "Priya Arunkumar";
        Assertions.assertTrue(RegistrationValidationUtil.isFirstNameValid(bloodBankName3), bloodBankName3 + " blood bank name 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate incorrect zip code")
    public void validateIncorrectZipCode() {
        final String zipCode1 = null;
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode1), zipCode1 + " zip code 1 is incorrect.");
        final String zipCode2 = "B4V2@8";
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode2), zipCode2 + " zip code 2 is incorrect.");
        final String zipCode3 = "B4V 2V8";
        Assertions.assertFalse(RegistrationValidationUtil.isZipCodeValid(zipCode3), zipCode3 + " zip code 3 is incorrect.");
    }

    @Test
    @DisplayName("Validate correct zip code")
    public void validateCorrectZipCode() {
        final String zipCode1 = "B4V208";
        Assertions.assertTrue(RegistrationValidationUtil.isZipCodeValid(zipCode1), zipCode1 + " zip code 1 is correct.");
        final String zipCode2 = "B4V2V8";
        Assertions.assertTrue(RegistrationValidationUtil.isZipCodeValid(zipCode2), zipCode2 + " zip code 2 is correct.");
    }

}
