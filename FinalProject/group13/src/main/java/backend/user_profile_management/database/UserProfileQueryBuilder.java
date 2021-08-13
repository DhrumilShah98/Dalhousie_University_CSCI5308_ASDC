package backend.user_profile_management.database;

import backend.authentication.user.model.User;
import backend.authentication.user.database.UserDatabaseConstant;
import backend.vaccination.database.VaccinationQueryBuilder;

/**
 * {@code UserProfileQueryBuilder} implements the
 * {@code UserProfileQueryBuilderDAO} to provide a concrete
 * implementation for the update user profile.
 * This class is implemented using the Singleton Design Pattern.
 */
public final class UserProfileQueryBuilder implements UserProfileQueryBuilderDAO {

  // Static instance of this class
  private static UserProfileQueryBuilder instance;

  /**
   * Constructs this {@code UserProfileQueryBuilder} instance.
   */
  private UserProfileQueryBuilder() {
    // Required empty private constructor
  }

  /**
   * Instantiates this {@code UserProfileQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UserProfileQueryBuilder} class.
   */
  public static UserProfileQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UserProfileQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to update user profile.
   *
   * @param user user object for user profile update.
   *
   * @return string query to update user profile.
   */
  public String updateUserInfoQuery(User user) {

    return "UPDATE " + UserDatabaseConstant.USER_TABLE +
        " SET " +
        UserDatabaseConstant.USER_FIRST_NAME_COLUMN + "=\"" + user.getFirstName() + "\"," +
        UserDatabaseConstant.USER_LAST_NAME_COLUMN + "=\"" + user.getLastName() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_FIRST_LINE_COLUMN + "=\"" + user.getAddressFirstLine() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_STREET_COLUMN + "=\"" + user.getAddressStreet() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_CITY_COLUMN + "=\"" + user.getAddressCity() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN + "=\"" + user.getAddressProvince() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_ZIP_CODE_COLUMN + "=\"" + user.getAddressZipCode() + "\"," +
        UserDatabaseConstant.USER_ADDRESS_COUNTRY_COLUMN + "=\"" + user.getAddressCountry() + "\"" +
        " WHERE " +
        UserDatabaseConstant.USER_ID_COLUMN + " = " + user.getUserId() + ";";

  }
}
