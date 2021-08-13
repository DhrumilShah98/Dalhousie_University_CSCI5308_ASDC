package backend.authentication.user.controller.login_with_contact_number;

import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnectionException;

/**
 * {@code UserLoginWithContactNumberControllerDAO} provides a contract for the
 * user login with contact number.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserLoginWithContactNumberControllerDAO {

  /**
   * Performs login of this user with contact number.
   *
   * @param contactNumber user contact number.
   * @param password      user password.
   *
   * @return logged-in user instance.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     *           user authentication.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  User loginWithContactNumber(final String contactNumber,
                              final String password)
      throws UserAuthenticationException,
      DatabaseConnectionException;
}