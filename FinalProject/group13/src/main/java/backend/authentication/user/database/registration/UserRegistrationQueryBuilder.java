package backend.authentication.user.database.registration;

import backend.authentication.user.model.User;

import static backend.authentication.user.database.UserDatabaseConstant.USER_ID_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_FIRST_NAME_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_LAST_NAME_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_GENDER_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_EMAIL_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_PASSWORD_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_CONTACT_NUMBER_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_BLOOD_GROUP_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_FIRST_LINE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_STREET_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_CITY_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_COUNTRY_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ADDRESS_ZIP_CODE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_ACCOUNT_ACTIVE_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_TABLE;
import static backend.authentication.user.database.UserDatabaseConstant.USER_SECURITY_QUESTION_USER_TABLE;
import static backend.authentication.user.database.UserDatabaseConstant.SECURITY_QUESTION_ID_COLUMN;
import static backend.authentication.user.database.UserDatabaseConstant.USER_SECURITY_QUESTION_ANS_COLUMN;

/**
 * {@code UserRegistrationQueryBuilder} implements the
 * {@code UserRegistrationQueryBuilderDAO} to provide a concrete
 * implementation for the user registration.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserRegistrationQueryBuilder
    implements UserRegistrationQueryBuilderDAO {

  // Static instance of this class
  private static UserRegistrationQueryBuilder instance;

  /**
   * Constructs this {@code UserRegistrationQueryBuilder} instance.
   */
  private UserRegistrationQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code UserRegistrationQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UserRegistrationQueryBuilder} class.
   */
  public static UserRegistrationQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UserRegistrationQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert this user in the table.
   *
   * @param user user to insert in the table.
   *
   * @return string query to insert this user in the table.
   */
  @Override
  public String insertUserQuery(final User user) {
    return "INSERT INTO " + USER_TABLE + "(" +
        USER_FIRST_NAME_COLUMN + ", " +
        USER_LAST_NAME_COLUMN + ", " +
        USER_GENDER_COLUMN + ", " +
        USER_DATE_OF_BIRTH_COLUMN + ", " +
        USER_EMAIL_COLUMN + ", " +
        USER_PASSWORD_COLUMN + ", " +
        USER_CONTACT_NUMBER_COLUMN + ", " +
        USER_BLOOD_GROUP_COLUMN + ", " +
        USER_ADDRESS_FIRST_LINE_COLUMN + ", " +
        USER_ADDRESS_STREET_COLUMN + ", " +
        USER_ADDRESS_CITY_COLUMN + ", " +
        USER_ADDRESS_PROVINCE_COLUMN + ", " +
        USER_ADDRESS_ZIP_CODE_COLUMN + ", " +
        USER_ADDRESS_COUNTRY_COLUMN + ", " +
        USER_ACCOUNT_ACTIVE_COLUMN + ") " +
        "VALUES (" +
        "\"" + user.getFirstName() + "\", " +
        "\"" + user.getLastName() + "\", " +
        "\"" + user.getGender() + "\", " +
        "\"" + user.getDateOfBirth() + "\", " +
        "\"" + user.getEmail() + "\", " +
        "\"" + user.getPassword() + "\", " +
        "\"" + user.getContactNumber() + "\", " +
        "\"" + user.getBloodGroup() + "\", " +
        "\"" + user.getAddressFirstLine() + "\", " +
        "\"" + user.getAddressStreet() + "\", " +
        "\"" + user.getAddressCity() + "\", " +
        "\"" + user.getAddressProvince() + "\", " +
        "\"" + user.getAddressZipCode() + "\", " +
        "\"" + user.getAddressCountry() + "\", " +
        user.isAccountActive() +
        ");";
  }

  /**
   * Gets the query to insert security questions and answers for this user in
   * the table.
   *
   * @param userId        unique id of this user.
   * @param securityQ1Id  security question 1 id of this user.
   * @param securityQ1Ans security question 1 answer of this user.
   * @param securityQ2Id  security question 2 id of this user.
   * @param securityQ2Ans security question 2 answer of this user.
   *
   * @return string query to insert security questions and answers for this user in
   * the table.
   */
  @Override
  public String insertUserSecurityQAQuery(final int userId,
                                          final int securityQ1Id,
                                          final String securityQ1Ans,
                                          final int securityQ2Id,
                                          final String securityQ2Ans) {
    return "INSERT INTO " + USER_SECURITY_QUESTION_USER_TABLE + "(" +
        USER_ID_COLUMN + ", " +
        SECURITY_QUESTION_ID_COLUMN + ", " +
        USER_SECURITY_QUESTION_ANS_COLUMN + ") " +
        "VALUES " +
        "(" + userId + ", " + securityQ1Id + ", \"" + securityQ1Ans + "\")," +
        "(" + userId + ", " + securityQ2Id + ", \"" + securityQ2Ans + "\");";
  }
}