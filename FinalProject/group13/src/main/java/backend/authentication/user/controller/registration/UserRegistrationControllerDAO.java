package backend.authentication.user.controller.registration;

import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnectionException;

/**
 * {@code UserRegistrationControllerDAO} provides a contract for the
 * user registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserRegistrationControllerDAO {

  /**
   * Performs registration of this user.
   *
   * @param user                 user to insert in the table.
   * @param securityQuestion1Id  security question 1 id of this user.
   * @param securityQuestion1Ans security question 1 answer of this user.
   * @param securityQuestion2Id  security question 2 id of this user.
   * @param securityQuestion2Ans security question 2 answer of this user.
   *
   * @return {@code true} if user registered successfully otherwise
   * {@code false}.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  boolean register(final User user,
                   final int securityQuestion1Id,
                   final String securityQuestion1Ans,
                   final int securityQuestion2Id,
                   final String securityQuestion2Ans)
      throws UserAuthenticationException,
      DatabaseConnectionException;
}