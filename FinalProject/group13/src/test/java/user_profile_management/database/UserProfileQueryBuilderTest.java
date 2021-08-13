package user_profile_management.database;

import backend.authentication.user.model.User;
import backend.user_profile_management.database.UserProfileQueryBuilder;
import backend.user_profile_management.database.UserProfileQueryBuilderDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Profile Database Constant test suite")
public class UserProfileQueryBuilderTest {

  @Test
  @DisplayName("User Profile Update Query")
  public void updateUserQuery() {

    final User user = new User(
        9,
        "viraj jigar",
        "shah",
        "M",
        "1999-03-14",
        "viraj@gmail.com",
        "Viraj99@",
        "9025299721",
        "O+",
        "Scotia Park",
        "brunswick street",
        "Halifax",
        "Nova Scotia",
        "B3V2A8",
        "Canada",
        true
    );
    final UserProfileQueryBuilder userProfileQueryBuilder = UserProfileQueryBuilder.getInstance();
    final String actualUpdateUserQuery = userProfileQueryBuilder.updateUserInfoQuery(user);
    final String expectedUpdateUserQuery = "UPDATE user SET first_name=\"viraj jigar\",last_name=\"shah\",address_first_line=\"Scotia Park\",address_street=\"brunswick street\",address_city=\"Halifax\",address_province=\"Nova Scotia\",address_zip_code=\"B3V2A8\",address_country=\"Canada\" WHERE user_id = 9;";
    Assertions.assertEquals(expectedUpdateUserQuery, actualUpdateUserQuery, "Incorrect Update Blood Bank Query.");
  }
}
