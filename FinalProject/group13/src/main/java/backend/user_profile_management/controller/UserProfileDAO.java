package backend.user_profile_management.controller;

import backend.authentication.user.model.User;
import backend.demographic_information.exception.DemographicInformationException;
import backend.user_profile_management.exception.UserProfileException;
import database.DatabaseConnectionException;

/**
 * {@code UserProfileDAO} provides a contract for the updating user profile.
 */
public interface UserProfileDAO {

  /**
   * Performs updating user profile.
   *
   * @param user user instance for updating profile.
   *
   * @return {@code true} if user profile update successfully else
   * {@code false}.
   *
   * @throws UserProfileException        if any error occurs while
   *                                     updating user profile.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  boolean update(User user) throws UserProfileException, DatabaseConnectionException;

}