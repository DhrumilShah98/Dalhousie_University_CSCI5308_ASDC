package backend.authentication.user.database.registration;

import backend.authentication.user.model.User;

/**
 * {@code UserRegistrationQueryBuilderDAO} provides a contract for the
 * user registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface UserRegistrationQueryBuilderDAO {

  /**
   * Gets the query to insert this user in the table.
   *
   * @param user user to insert in the table.
   *
   * @return string query to insert this user in the table.
   */
  String insertUserQuery(final User user);

  /**
   * @param userId        unique id of this user.
   * @param securityQ1Id  security question 1 id of this user.
   * @param securityQ1Ans security question 1 answer of this user.
   * @param securityQ2Id  security question 2 id of this user.
   * @param securityQ2Ans security question 2 answer of this user.
   *
   * @return string query to insert security questions and answers for this
   * user in the table.
   */
  String insertUserSecurityQAQuery(final int userId,
                                   final int securityQ1Id,
                                   final String securityQ1Ans,
                                   final int securityQ2Id,
                                   final String securityQ2Ans);
}
