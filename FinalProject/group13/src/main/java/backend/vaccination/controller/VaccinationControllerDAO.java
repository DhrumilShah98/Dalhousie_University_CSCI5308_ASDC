package backend.vaccination.controller;

import backend.authentication.user.model.User;
import backend.user_profile_management.exception.UserProfileException;
import database.DatabaseConnectionException;
import backend.vaccination.exception.VaccineException;
import backend.vaccination.model.VaccineModel;

/**
 * {@code VaccinationControllerDAO} provides a contract for the schedule
 * vaccination for user.
 */
public interface VaccinationControllerDAO {

  /**
   * Performs schedule vaccination for user.
   *
   * @param user user instance for fetching age to decide which vaccine
   *             we can give to them.
   *
   * @return vaccine model class with all the values like vaccine type,
   * registration date, first dose date, second dose date.
   *
   * @throws VaccineException            if any error occurs while
   *                                     scheduling vaccination for user.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  VaccineModel scheduleVaccination(User user) throws VaccineException,
      DatabaseConnectionException;
}
