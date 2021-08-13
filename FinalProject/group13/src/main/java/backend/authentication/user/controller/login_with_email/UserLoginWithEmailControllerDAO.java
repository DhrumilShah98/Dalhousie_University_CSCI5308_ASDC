package backend.authentication.user.controller.login_with_email;

import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnectionException;

/**
 * {@code UserLoginWithEmailControllerDAO} provides a contract for the
 * user login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserLoginWithEmailControllerDAO {

  /**
   * Performs login of this user with email.
   *
   * @param email    user email.
   * @param password user password.
   *
   * @return logged-in user instance.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  User loginWithEmail(final String email,
                      final String password)
      throws UserAuthenticationException,
      DatabaseConnectionException;
}