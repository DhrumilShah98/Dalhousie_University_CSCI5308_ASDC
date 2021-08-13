package backend.authentication.user.controller.registration;

import backend.authentication.user.database.registration.UserRegistrationQueryBuilderDAO;
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

/**
 * {@code UserRegistrationController} implements the
 * {@code UserRegistrationControllerDAO} to provide a concrete
 * implementation for the user registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class UserRegistrationController
    implements UserRegistrationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // User registration query builder instance.
  private final UserRegistrationQueryBuilderDAO
      userRegistrationQueryBuilderDAO;

  /**
   * Constructs this {@code UserRegistrationController} instance.
   *
   * @param databaseConnectionDAO           database connection instance.
   * @param userRegistrationQueryBuilderDAO user registration
   *                                        query builder instance.
   */
  public UserRegistrationController(final DatabaseConnectionDAO
                                        databaseConnectionDAO,
                                    final UserRegistrationQueryBuilderDAO
                                        userRegistrationQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.userRegistrationQueryBuilderDAO =
        userRegistrationQueryBuilderDAO;
  }

  /**
   * Validates user fields.
   *
   * @param user                 user instance.
   * @param securityQuestion1Ans security question 1 answer.
   * @param securityQuestion2Ans security question 2 answer.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   */
  private void validateUserFields(final User user,
                                  final String securityQuestion1Ans,
                                  final String securityQuestion2Ans)
      throws UserAuthenticationException {
    // User first name validation
    final boolean isUserFirstNameValid = (user.getFirstName() != null) &&
        (!user.getFirstName().trim().isEmpty()) &&
        (RegistrationValidationUtil.isFirstNameValid(user.getFirstName().trim()));
    if (!isUserFirstNameValid) {
      throw new UserAuthenticationException("Invalid first name.");
    }
    // User last name validation
    final boolean isUserLastNameValid = (user.getLastName() != null) &&
        (!user.getLastName().trim().isEmpty()) &&
        (RegistrationValidationUtil.isLastNameValid(user.getLastName().trim()));
    if (!isUserLastNameValid) {
      throw new UserAuthenticationException("Invalid last name.");
    }
    // User gender validation
    final boolean isUserGenderValid = (user.getGender() != null) &&
        (!user.getGender().trim().isEmpty()) &&
        (RegistrationValidationUtil.isGenderValid(user.getGender().trim()));
    if (!isUserGenderValid) {
      throw new UserAuthenticationException("Invalid gender.");
    }
    // User DOB validation
    final boolean isUserDOBValid = (user.getDateOfBirth() != null) &&
        (!user.getDateOfBirth().trim().isEmpty()) &&
        (RegistrationValidationUtil.isDateFormatValid(user.getDateOfBirth().trim()));
    if (!isUserDOBValid) {
      throw new UserAuthenticationException("Invalid date of birth.");
    }
    // User age validation
    final boolean isUserAgeValid = RegistrationValidationUtil.isAgeValid(user.getDateOfBirth());
    if (!isUserAgeValid) {
      throw new UserAuthenticationException("Invalid age. Must be greater or equal to 18 years");
    }
    // User email validation
    final boolean isUserEmailValid = (user.getEmail() != null) &&
        (!user.getEmail().trim().isEmpty()) &&
        (RegistrationValidationUtil.isEmailValid(user.getEmail().trim()));
    if (!isUserEmailValid) {
      throw new UserAuthenticationException("Invalid email.");
    }
    // User password validation
    final boolean isUserPasswordValid = (user.getPassword() != null) &&
        (!user.getPassword().trim().isEmpty()) &&
        (RegistrationValidationUtil.isPasswordValid(user.getPassword().trim()));
    if (!isUserPasswordValid) {
      throw new UserAuthenticationException("Invalid password.");
    }
    // User contact number validation
    final boolean isUserContactNumberValid = (user.getContactNumber() != null) &&
        (!user.getContactNumber().trim().isEmpty()) &&
        (RegistrationValidationUtil.isContactNumberValid(user.getContactNumber().trim()));
    if (!isUserContactNumberValid) {
      throw new UserAuthenticationException("Invalid contact number.");
    }
    // User blood group validation
    final boolean isUserBloodGroupValid = (user.getBloodGroup() != null) &&
        (!user.getBloodGroup().trim().isEmpty()) &&
        (RegistrationValidationUtil.isBloodGroupValid(user.getBloodGroup().trim()));
    if (!isUserBloodGroupValid) {
      throw new UserAuthenticationException("Invalid blood group.");
    }
    // User address first line validation
    final boolean isUserAddressFirstLineValid = (user.getAddressFirstLine() != null) &&
        (!user.getAddressFirstLine().trim().isEmpty());
    if (!isUserAddressFirstLineValid) {
      throw new UserAuthenticationException("Invalid address first line.");
    }
    // User address street validation
    final boolean isUserAddressStreetValid = (user.getAddressStreet() != null) &&
        (!user.getAddressStreet().trim().isEmpty());
    if (!isUserAddressStreetValid) {
      throw new UserAuthenticationException("Invalid address street.");
    }
    // User address city validation
    final boolean isUserAddressCityValid = (user.getAddressCity() != null) &&
        (!user.getAddressCity().trim().isEmpty());
    if (!isUserAddressCityValid) {
      throw new UserAuthenticationException("Invalid address city.");
    }
    // User address province validation
    final boolean isUserAddressProvinceValid = (user.getAddressProvince() != null) &&
        (!user.getAddressProvince().trim().isEmpty());
    if (!isUserAddressProvinceValid) {
      throw new UserAuthenticationException("Invalid address province.");
    }
    // User address zip code validation
    final boolean isUserZipCodeValid = (user.getAddressZipCode() != null) &&
        (!user.getAddressZipCode().trim().isEmpty()) &&
        (RegistrationValidationUtil.isZipCodeValid(user.getAddressZipCode().trim()));
    if (!isUserZipCodeValid) {
      throw new UserAuthenticationException("Invalid address zip code.");
    }
    // User address country validation
    final boolean isUserAddressCountryValid = (user.getAddressCountry() != null) &&
        (!user.getAddressCountry().trim().isEmpty());
    if (!isUserAddressCountryValid) {
      throw new UserAuthenticationException("Invalid address country.");
    }
    // User security question 1 ans validation
    final boolean isUserSQ1AnsValid = (securityQuestion1Ans != null) &&
        (!securityQuestion1Ans.trim().isEmpty());
    if (!isUserSQ1AnsValid) {
      throw new UserAuthenticationException("Invalid security question 1 answer.");
    }
    // User security question 2 ans validation
    final boolean isUserSQ2AnsValid = (securityQuestion2Ans != null) &&
        (!securityQuestion2Ans.trim().isEmpty());
    if (!isUserSQ2AnsValid) {
      throw new UserAuthenticationException("Invalid security question 2 answer.");
    }
  }

  /**
   * Performs registration of this user.
   *
   * @param user                 user to insert in the table.
   * @param securityQuestion1Id  security question 1 id of this user.
   * @param securityQuestion1Ans security question 1 answer of this user.
   * @param securityQuestion2Id  security question 2 id of this user.
   * @param securityQuestion2Ans security question 2 answer of this user.
   *
   * @return {@code true} if user registered successfully otherwise {@code
   * false}.
   *
   * @throws UserAuthenticationException if any error occurs while
   *                                     user authentication.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public boolean register(final User user,
                          final int securityQuestion1Id,
                          String securityQuestion1Ans,
                          final int securityQuestion2Id,
                          String securityQuestion2Ans)
      throws UserAuthenticationException, DatabaseConnectionException {
    if (user == null) {
      throw new UserAuthenticationException("Null user.");
    }
    validateUserFields(user, securityQuestion1Ans, securityQuestion2Ans);
    user.setFirstName(user.getFirstName().trim());
    user.setLastName(user.getLastName().trim());
    user.setGender(user.getGender().trim());
    user.setDateOfBirth(user.getDateOfBirth().trim());
    user.setEmail(user.getEmail().trim());
    user.setContactNumber(user.getContactNumber().trim());
    user.setBloodGroup(user.getBloodGroup().trim());
    user.setAddressFirstLine(user.getAddressFirstLine().trim());
    user.setAddressStreet(user.getAddressStreet().trim());
    user.setAddressCity(user.getAddressCity().trim());
    user.setAddressProvince(user.getAddressProvince().trim());
    user.setAddressZipCode(user.getAddressZipCode().trim());
    user.setAddressCountry(user.getAddressCountry().trim());
    securityQuestion1Ans = securityQuestion1Ans.trim();
    securityQuestion2Ans = securityQuestion2Ans.trim();
    final String passwordSha256Hash = HashAlgorithmUtil.getSHA256Hash(user.getPassword().trim());
    if (passwordSha256Hash == null) {
      throw new UserAuthenticationException("Failed to hash password. Please select a different password.");
    }
    user.setPassword(passwordSha256Hash);
    ResultSet generatedKeysResultSet = null;
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      final String insertUserQuery = userRegistrationQueryBuilderDAO.insertUserQuery(user);
      final int userRowInserted = statement.executeUpdate(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
      if (userRowInserted > 0) {
        generatedKeysResultSet = statement.getGeneratedKeys();
        if (generatedKeysResultSet.next()) {
          final int userID = generatedKeysResultSet.getInt(1);
          final String insertUserSecurityQAQuery = userRegistrationQueryBuilderDAO
              .insertUserSecurityQAQuery(userID,
                  securityQuestion1Id,
                  securityQuestion1Ans,
                  securityQuestion2Id,
                  securityQuestion2Ans);
          final int userQARowsInserted = statement.executeUpdate(insertUserSecurityQAQuery);
          if (userQARowsInserted > 0) {
            return true;
          }
        }
        generatedKeysResultSet.close();
        return false;
      }
      return false;
    } catch (final SQLException e) {
      if (generatedKeysResultSet != null) {
        try {
          generatedKeysResultSet.close();
        } catch (final SQLException e1) {
          throw new DatabaseConnectionException(e.getMessage(), e1);
        }
      }
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}