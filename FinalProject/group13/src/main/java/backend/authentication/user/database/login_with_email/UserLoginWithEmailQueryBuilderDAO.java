package backend.authentication.user.database.login_with_email;

import backend.authentication.user.model.User;

/**
 * {@code UserLoginWithEmailQueryBuilderDAO} provides a contract for the
 * buser login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserLoginWithEmailQueryBuilderDAO {

  /**
   * Gets the query to select user by email.
   *
   * @param email email of this user.
   *
   * @return string query to select user by email.
   */
  String selectUserByEmail(final String email);
}
