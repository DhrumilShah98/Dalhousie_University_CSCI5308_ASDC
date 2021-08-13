package authentication.user.database;

import backend.authentication.user.database.login_with_contact_number.UserLoginWithContactNumberQueryBuilder;
import backend.authentication.user.database.login_with_email.UserLoginWithEmailQueryBuilder;
import backend.authentication.user.database.registration.UserRegistrationQueryBuilder;
import backend.authentication.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User query builder test suite")
public class UserQueryBuilderTest {
    @Test
    @DisplayName("insertUserQuery() test")
    public void insertUserQuery() {
        final User user = new User("Viraj",
                "Shah",
                "M",
                "1995-02-22",
                "viraj@gmail.com",
                "Viraj@12345",
                "9023451234",
                "A+",
                "Bakers Building",
                "177 Albert Street",
                "Herbert",
                "Saskatchwen",
                "S4P3Y2",
                "Canada",
                true);
        final UserRegistrationQueryBuilder userRegistrationQueryBuilder = UserRegistrationQueryBuilder.getInstance();
        final String actualInsertUserQuery = userRegistrationQueryBuilder.insertUserQuery(user);
        final String expectedInsertUserQuery = "INSERT INTO user(first_name, last_name, gender, date_of_birth, email, password, contact_number, blood_group, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active)" +
                " VALUES (\"Viraj\", \"Shah\", \"M\", \"1995-02-22\", \"viraj@gmail.com\", \"Viraj@12345\", \"9023451234\", \"A+\", \"Bakers Building\", \"177 Albert Street\", \"Herbert\", \"Saskatchwen\", \"S4P3Y2\", \"Canada\", true);";
        Assertions.assertEquals(actualInsertUserQuery, expectedInsertUserQuery, "Incorrect insert user query.");
    }

    @Test
    @DisplayName("insertUserSecurityQAQuery() test")
    public void insertUserSecurityQAQuery() {
        final int userId = 1;
        final int securityQ1Id = 3;
        final int securityQ2Id = 1;
        final String securityQ1Ans = "Canada";
        final String securityQ2Ans = "Aniket";
        final UserRegistrationQueryBuilder userRegistrationQueryBuilder = UserRegistrationQueryBuilder.getInstance();
        final String actualInsertUserSecurityQAQuery = userRegistrationQueryBuilder.insertUserSecurityQAQuery(userId, securityQ1Id, securityQ1Ans, securityQ2Id, securityQ2Ans);
        final String expectedInsertUserSecurityQAQuery = "INSERT INTO user_security_question_user(user_id, security_question_id, security_question_ans) " +
                "VALUES (1, 3, \"Canada\"),(1, 1, \"Aniket\");";
        Assertions.assertEquals(expectedInsertUserSecurityQAQuery, actualInsertUserSecurityQAQuery, "Incorrect insert security question user query.");
    }

    @Test
    @DisplayName("User by email select query")
    public void selectUserByEmail() {
        final String email = "dhrumil@gmail.com";
        final UserLoginWithEmailQueryBuilder userLoginWithEmailQueryBuilder = UserLoginWithEmailQueryBuilder.getInstance();
        final String actualSelectUserByEmailQuery = userLoginWithEmailQueryBuilder.selectUserByEmail(email);
        final String expectedSelectUserByEmailQuery = "SELECT user_id, first_name, last_name, gender, date_of_birth, email, password, contact_number, blood_group, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active FROM user WHERE email=\"dhrumil@gmail.com\";";
        Assertions.assertEquals(expectedSelectUserByEmailQuery, actualSelectUserByEmailQuery, "Incorrect select user by email query");
    }

    @Test
    @DisplayName("User by contact number select query")
    public void selectUserByContactNumber() {
        final String contactNumber = "9025299754";
        final UserLoginWithContactNumberQueryBuilder userLoginWithContactNumberQueryBuilder = UserLoginWithContactNumberQueryBuilder.getInstance();
        final String actualSelectUserByContactNumberQuery = userLoginWithContactNumberQueryBuilder.selectUserByContactNumber(contactNumber);
        final String expectedSelectUserByContactNumberQuery = "SELECT user_id, first_name, last_name, gender, date_of_birth, email, password, contact_number, blood_group, address_first_line, address_street, address_city, address_province, address_zip_code, address_country, account_active FROM user WHERE contact_number=\"9025299754\";";
        Assertions.assertEquals(expectedSelectUserByContactNumberQuery, actualSelectUserByContactNumberQuery, "Incorrect select user by contact number query");
    }
}