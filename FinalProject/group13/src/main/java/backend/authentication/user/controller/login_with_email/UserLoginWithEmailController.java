package backend.authentication.user.controller.login_with_email;

import backend.authentication.user.database.UserDatabaseConstant;
import backend.authentication.user.database.login_with_email.UserLoginWithEmailQueryBuilderDAO;
import backend.authentication.user.exception.UserAuthenticationException;
import backend.authentication.user.model.User;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.authentication.util.HashAlgorithmUtil;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

/**
 * {@code UserLoginWithEmailController} implements the
 * {@code UserLoginWithEmailControllerDAO} to provide a concrete
 * implementation for the user login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserLoginWithEmailController
    implements UserLoginWithEmailControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // User login with email query builder instance.
  private final UserLoginWithEmailQueryBuilderDAO
      userLoginWithEmailQueryBuilderDAO;

  /**
   * Constructs this {@code UserLoginWithEmailController} instance.
   *
   * @param databaseConnectionDAO             database connection instance.
   * @param userLoginWithEmailQueryBuilderDAO user login with email
   *                                          query builder instance.
   */
  public UserLoginWithEmailController(final DatabaseConnectionDAO
                                          databaseConnectionDAO,
                                      final UserLoginWithEmailQueryBuilderDAO
                                          userLoginWithEmailQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.userLoginWithEmailQueryBuilderDAO =
        userLoginWithEmailQueryBuilderDAO;
  }

  /**
   * Validate login credentials of this user.
   *
   * @param email    email address of this user.
   * @param password password of this user.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   */
  private void validateLoginWithEmailCredentials(final String email,
                                                 final String password)
      throws UserAuthenticationException {
    final boolean isUserEmailValid = (email != null) &&
        (!email.trim().isEmpty()) &&
        (RegistrationValidationUtil.isEmailValid(email.trim()));
    final boolean isUserPasswordValid = (password != null) &&
        (!password.trim().isEmpty()) &&
        (RegistrationValidationUtil.isPasswordValid(password.trim()));
    if (!isUserEmailValid || !isUserPasswordValid) {
      throw new UserAuthenticationException("Invalid email and/or password");
    }
  }

  /**
   * Prepare user instance after login credentials are correct.
   *
   * @param resultSet result set of logged-in user.
   *
   * @return user instance of logged-in user.
   *
   * @throws SQLException if any error occurs while user authentication.
   */
  private User prepareUser(final ResultSet resultSet)
      throws SQLException {
    User user;
    final int id =
        resultSet.getInt(USER_ID_COLUMN);
    final String firstName =
        resultSet.getString(USER_FIRST_NAME_COLUMN);
    final String lastName =
        resultSet.getString(USER_LAST_NAME_COLUMN);
    final String gender =
        resultSet.getString(USER_GENDER_COLUMN);
    final String dateOfBirth =
        resultSet.getString(USER_DATE_OF_BIRTH_COLUMN);
    final String email =
        resultSet.getString(USER_EMAIL_COLUMN);
    final String password =
        resultSet.getString(USER_PASSWORD_COLUMN);
    final String contactNum =
        resultSet.getString(USER_CONTACT_NUMBER_COLUMN);
    final String bloodGroup =
        resultSet.getString(USER_BLOOD_GROUP_COLUMN);
    final String addFirstLine =
        resultSet.getString(USER_ADDRESS_FIRST_LINE_COLUMN);
    final String addStreet =
        resultSet.getString(USER_ADDRESS_STREET_COLUMN);
    final String addCity =
        resultSet.getString(USER_ADDRESS_CITY_COLUMN);
    final String addProvince =
        resultSet.getString(USER_ADDRESS_PROVINCE_COLUMN);
    final String addZipCode =
        resultSet.getString(USER_ADDRESS_ZIP_CODE_COLUMN);
    final String addCountry =
        resultSet.getString(USER_ADDRESS_COUNTRY_COLUMN);
    user = new User(
        id,
        firstName,
        lastName,
        gender,
        dateOfBirth,
        email,
        password,
        contactNum,
        bloodGroup,
        addFirstLine,
        addStreet,
        addCity,
        addProvince,
        addZipCode,
        addCountry,
        true
    );
    return user;
  }

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
  @Override
  public User loginWithEmail(final String email, final String password)
      throws UserAuthenticationException, DatabaseConnectionException {
    validateLoginWithEmailCredentials(email, password);
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet userResultSet =
             statement.executeQuery(userLoginWithEmailQueryBuilderDAO.selectUserByEmail(email))) {
      if (userResultSet == null) {
        throw new UserAuthenticationException("Invalid email and/or password");
      }
      if (userResultSet.next()) {
        final boolean isPasswordValid =
            HashAlgorithmUtil.validateSHA256Hash(password, userResultSet.getString(USER_PASSWORD_COLUMN));
        if (!isPasswordValid) {
          throw new UserAuthenticationException("Invalid email and/or password");
        }
        final boolean isAccountActive =
            userResultSet.getBoolean(USER_ACCOUNT_ACTIVE_COLUMN);
        if (!isAccountActive) {
          throw new UserAuthenticationException("Account not active");
        }
        return prepareUser(userResultSet);
      }
      return null;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}