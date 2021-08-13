package backend.user_profile_management.database;

import backend.authentication.user.model.User;

/**
 * {@code UserProfileQueryBuilderDAO} provides a contract for the
 * user to update his profile.
 */
public interface UserProfileQueryBuilderDAO {

  /**
   * Gets the query to update user profile.
   *
   * @param user user object for user profile update.
   *
   * @return string query to update user profile.
   */
  String updateUserInfoQuery(User user);
}
