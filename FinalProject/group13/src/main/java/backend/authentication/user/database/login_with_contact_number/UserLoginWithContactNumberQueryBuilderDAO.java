package backend.authentication.user.database.login_with_contact_number;

import backend.authentication.user.model.User;

/**
 * {@code UserLoginWithContactNumberQueryBuilderDAO} provides a contract for the
 * user login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserLoginWithContactNumberQueryBuilderDAO {

  /**
   * Gets the query to select user by email.
   *
   * @param contactNumber contact number of this user.
   *
   * @return string query to select user by email.
   */
  String selectUserByContactNumber(String contactNumber);
}