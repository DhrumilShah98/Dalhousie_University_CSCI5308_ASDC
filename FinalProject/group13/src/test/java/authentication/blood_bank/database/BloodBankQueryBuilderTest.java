package authentication.blood_bank.database;

import backend.authentication.blood_bank.database.login_with_contact_number.BloodBankLoginWithContactNumberQueryBuilder;
import backend.authentication.blood_bank.database.login_with_email.BloodBankLoginWithEmailQueryBuilder;
import backend.authentication.blood_bank.database.registration.BloodBankRegistrationQueryBuilder;
import backend.authentication.blood_bank.model.BloodBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Blood Bank query builder test suite")
public class BloodBankQueryBuilderTest {
    @Test
    @DisplayName("insertBloodBankQuery() test")
    public void insertBloodBankQuery() {
        final BloodBank bloodBank = new BloodBank(
                "Cooper Blood Bank",
                "cooper@gmail.com",
                "cooperbloodbank@12345",
                "9028832245",
                "Copper Blood Bank Buildings",
                "2302 Brook St",
                "Cape Breton Island",
                "NS",
                "B3S936",
                "Canada",
                true
        );
        final BloodBankRegistrationQueryBuilder bloodBankQueryBuilder = BloodBankRegistrationQueryBuilder.getInstance();
        final String actualInsertBloodBankQuery = bloodBankQueryBuilder.insertBloodBankQuery(bloodBank);
        final String expectedInsertBloodBankQuery = "INSERT INTO blood_bank(name, email, password, contact_number, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active) " +
                "VALUES (\"Cooper Blood Bank\", \"cooper@gmail.com\", \"cooperbloodbank@12345\", \"9028832245\", \"Copper Blood Bank Buildings\", \"2302 Brook St\", \"Cape Breton Island\", \"NS\", \"B3S936\", \"Canada\", true);";
        Assertions.assertEquals(expectedInsertBloodBankQuery, actualInsertBloodBankQuery, "Incorrect insert blood bank query.");
    }

    @Test
    @DisplayName("insertBloodBankSecurityQAQuery() test")
    public void insertBloodBankSecurityQAQuery() {
        final int bloodBankId = 1;
        final int securityQ1Id = 1;
        final int securityQ2Id = 2;
        final String securityQ1Ans = "3";
        final String securityQ2Ans = "1998-01-01";
        final BloodBankRegistrationQueryBuilder bloodBankQueryBuilder = BloodBankRegistrationQueryBuilder.getInstance();
        final String actualInsertBloodBankSecurityQAQuery = bloodBankQueryBuilder.insertBloodBankSecurityQAQuery(bloodBankId, securityQ1Id, securityQ1Ans, securityQ2Id, securityQ2Ans);
        final String expectedInsertBloodBankSecurityQAQuery = "INSERT INTO blood_bank_security_question_blood_bank(blood_bank_id, security_question_id, security_question_ans) " +
                "VALUES (1, 1, \"3\"), (1, 2, \"1998-01-01\");";
        Assertions.assertEquals(expectedInsertBloodBankSecurityQAQuery, actualInsertBloodBankSecurityQAQuery, "Incorrect insert security question blood bank query.");
    }

    @Test
    @DisplayName("Blood bank registration stored procedure")
    public void storedProcedureForBloodBankRegistration() {
        final BloodBank bloodBank = new BloodBank(
                "Dummy BB",
                "dummy@gmail.com",
                "Dummy@12345",
                "9023333333",
                "LakeView Buildings",
                "2302 Brook St",
                "Newcombville",
                "Nova Scotia",
                "B4V2V8",
                "Canada",
                true
        );
        final int sQ1Id = 1;
        final String sQ1Ans = "8";
        final int sQ2Id = 2;
        final String sQ2Ans = "1993-01-01";
        final BloodBankRegistrationQueryBuilder bloodBankQueryBuilder = BloodBankRegistrationQueryBuilder.getInstance();
        final String actualBloodBankRegistrationSP = bloodBankQueryBuilder.storedProcedureForBloodBankRegistrationQuery(bloodBank, sQ1Id, sQ1Ans, sQ2Id, sQ2Ans);
        final String expectedBloodBankRegistrationSP = "CALL blood_bank_registration(\"Dummy BB\", \"dummy@gmail.com\", \"Dummy@12345\", \"9023333333\", \"LakeView Buildings\", \"2302 Brook St\", \"Newcombville\", \"Nova Scotia\", \"B4V2V8\", \"Canada\", 1, \"8\", 2, \"1993-01-01\");";
        Assertions.assertEquals(expectedBloodBankRegistrationSP, actualBloodBankRegistrationSP, "Incorrect stored procedure for blood bank registration");
    }

    @Test
    @DisplayName("selectBloodBankByEmail() test")
    public void selectBloodBankByEmail() {
        final String email = "redcross@gmail.com";
        final BloodBankLoginWithEmailQueryBuilder bloodBankLoginWithEmailQueryBuilder = BloodBankLoginWithEmailQueryBuilder.getInstance();
        final String actualSelectBloodBankByEmailQuery = bloodBankLoginWithEmailQueryBuilder.selectBloodBankByEmailQuery(email);
        final String expectedSelectBloodBankByEmailQuery = "SELECT blood_bank_id, name, email, password, contact_number, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active FROM blood_bank WHERE email=\"redcross@gmail.com\";";
        Assertions.assertEquals(expectedSelectBloodBankByEmailQuery, actualSelectBloodBankByEmailQuery, "Incorrect select blood bank by email query");
    }

    @Test
    @DisplayName("selectBloodBankByContactNumber() test")
    public void selectBloodBankByContactNumber() {
        final String contactNumber = "9081234567";
        final BloodBankLoginWithContactNumberQueryBuilder bloodBankLoginWithContactNumberQueryBuilder = BloodBankLoginWithContactNumberQueryBuilder.getInstance();
        final String actualSelectBloodBankByContactNumberQuery = bloodBankLoginWithContactNumberQueryBuilder.selectBloodBankByContactNumberQuery(contactNumber);
        final String expectedSelectBloodBankByContactNumberQuery = "SELECT blood_bank_id, name, email, password, contact_number, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active FROM blood_bank WHERE contact_number=\"9081234567\";";
        Assertions.assertEquals(expectedSelectBloodBankByContactNumberQuery, actualSelectBloodBankByContactNumberQuery, "Incorrect select blood bank by contact number query");
    }
}
