package backend.authentication.user.database.login_with_contact_number;

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

/**
 * {@code UserLoginWithContactNumberQueryBuilder} implements the
 * {@code UserLoginWithContactNumberQueryBuilderDAO} to provide a concrete
 * implementation for the user login with email.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserLoginWithContactNumberQueryBuilder
    implements UserLoginWithContactNumberQueryBuilderDAO {

  // Static instance of this class
  private static UserLoginWithContactNumberQueryBuilder instance;

  /**
   * Constructs this {@code UserLoginWithContactNumberQueryBuilder} instance.
   */
  private UserLoginWithContactNumberQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code UserLoginWithContactNumberQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UserLoginWithContactNumberQueryBuilder} class.
   */
  public static UserLoginWithContactNumberQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UserLoginWithContactNumberQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select user by contact number.
   *
   * @param contactNumber contact number of this user.
   *
   * @return tring query to select user by email.
   */
  @Override
  public String selectUserByContactNumber(final String contactNumber) {
    return "SELECT " +
        USER_ID_COLUMN + ", " +
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
        USER_ACCOUNT_ACTIVE_COLUMN +
        " FROM " +
        USER_TABLE +
        " WHERE " +
        USER_CONTACT_NUMBER_COLUMN + "=\"" + contactNumber + "\";";
  }
}