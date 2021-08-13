package backend.user_profile_management.controller;

import backend.authentication.user.model.User;
import backend.demographic_information.exception.DemographicInformationException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.user_profile_management.database.UserProfileQueryBuilderDAO;
import backend.user_profile_management.exception.UserProfileException;
import backend.authentication.util.RegistrationValidationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * {@code UserProfile} implements the
 * {@code UserProfileDAO} to provide a concrete
 * implementation for the update user profile.
 */
public final class UserProfile implements UserProfileDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // user profile query builder instance.
  private final UserProfileQueryBuilderDAO userProfileQueryBuilderDAO;

  /**
   * Constructs this {@code UserProfile} instance.
   *
   * @param databaseConnectionDAO      database connection
   *                                   instance.
   * @param userProfileQueryBuilderDAO user profile query
   *                                   builder instance.
   */
  public UserProfile(DatabaseConnectionDAO databaseConnectionDAO, UserProfileQueryBuilderDAO userProfileQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.userProfileQueryBuilderDAO = userProfileQueryBuilderDAO;
  }

  /**
   * validation user profile as user enter updated values.
   *
   * @param user user instance will get new value which is entered by the
   *             user.
   *
   * @throws UserProfileException if any error occurs while
   *                              updating user profile.
   */
  public void validateUserProfileFields(User user) throws UserProfileException {

    // User first name validation
    final boolean isUserFirstNameValid = (user.getFirstName() != null) &&
        (!user.getFirstName().trim().isEmpty()) &&
        (RegistrationValidationUtil.isFirstNameValid(user.getFirstName().trim()));
    if (!isUserFirstNameValid) {
      throw new UserProfileException("Invalid first name.");
    }

    // User last name validation
    final boolean isUserLastNameValid = (user.getLastName() != null) &&
        (!user.getLastName().trim().isEmpty()) &&
        (RegistrationValidationUtil.isLastNameValid(user.getLastName().trim()));
    if (!isUserLastNameValid) {
      throw new UserProfileException("Invalid last name.");
    }

    // User address first line validation
    final boolean isUserAddressFirstLineValid = (user.getAddressFirstLine() != null) &&
        (!user.getAddressFirstLine().trim().isEmpty());
    if (!isUserAddressFirstLineValid) {
      throw new UserProfileException("Invalid address first line.");
    }

    // User address street validation
    final boolean isUserAddressStreetValid = (user.getAddressStreet() != null) &&
        (!user.getAddressStreet().trim().isEmpty());
    if (!isUserAddressStreetValid) {
      throw new UserProfileException("Invalid address street.");
    }

    // User address city validation
    final boolean isUserAddressCityValid = (user.getAddressCity() != null) &&
        (!user.getAddressCity().trim().isEmpty());
    if (!isUserAddressCityValid) {
      throw new UserProfileException("Invalid address city.");
    }

    // User address province validation
    final boolean isUserAddressProvinceValid = (user.getAddressProvince() != null) &&
        (!user.getAddressProvince().trim().isEmpty());
    if (!isUserAddressProvinceValid) {
      throw new UserProfileException("Invalid address province.");
    }

    // User address zip code validation
    final boolean isUserZipCodeValid = (user.getAddressZipCode() != null) &&
        (!user.getAddressZipCode().trim().isEmpty()) &&
        (RegistrationValidationUtil.isZipCodeValid(user.getAddressZipCode().trim()));
    if (!isUserZipCodeValid) {
      throw new UserProfileException("Invalid address zip code.");
    }

    // User address country validation
    final boolean isUserAddressCountryValid = (user.getAddressCountry() != null) &&
        (!user.getAddressCountry().trim().isEmpty());
    if (!isUserAddressCountryValid) {
      throw new UserProfileException("Invalid address country.");
    }
  }

  /**
   * updating user profile as user enter updated values.
   *
   * @param user instance will get new value which is entered by the
   *             user.
   *
   * @throws UserProfileException        if any error occurs while
   *                                     updating user profile.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to database.
   */
  @Override
  public boolean update(User user) throws UserProfileException, DatabaseConnectionException {

    // User object null
    if (user == null) {
      throw new UserProfileException("Null User.");
    }

    // Validate User Profile Fields
    validateUserProfileFields(user);

    // Trim extra spaces from Blood Bank Profile Details
    user.setFirstName(user.getFirstName().trim());
    user.setLastName(user.getLastName().trim());
    user.setAddressFirstLine(user.getAddressFirstLine().trim());
    user.setAddressStreet(user.getAddressStreet().trim());
    user.setAddressCity(user.getAddressCity().trim());
    user.setAddressProvince(user.getAddressProvince().trim());
    user.setAddressZipCode(user.getAddressZipCode().trim());
    user.setAddressCountry(user.getAddressCountry().trim());

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement()) {
      final String updateUserQuery = userProfileQueryBuilderDAO.updateUserInfoQuery(user);
      final int userRowUpdated = statement.executeUpdate(updateUserQuery);

      if (userRowUpdated > 0) {
        return true;
      } else {
        throw new UserProfileException("Error while updating the profile.");
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}