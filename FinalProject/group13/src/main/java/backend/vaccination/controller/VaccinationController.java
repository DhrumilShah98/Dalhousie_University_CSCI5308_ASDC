package backend.vaccination.controller;

import backend.authentication.user.model.User;
import backend.user_profile_management.exception.UserProfileException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;
import backend.vaccination.database.VaccinationQueryBuilderDAO;
import backend.vaccination.database.VaccineDatabaseConstant;
import backend.vaccination.exception.VaccineException;
import backend.vaccination.model.VaccineModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * {@code VaccinationController} implements the
 * {@code VaccinationControllerDAO} to provide a concrete
 * implementation for the schedule and see the vaccination information.
 */
public final class VaccinationController implements VaccinationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // vaccination query builder instance.
  private final VaccinationQueryBuilderDAO vaccinationQueryBuilderDAO;

  /**
   * Constructs this {@code VaccinationController} instance.
   *
   * @param databaseConnectionDAO      database connection
   *                                   instance.
   * @param vaccinationQueryBuilderDAO vaccination query
   *                                   builder instance.
   */
  public VaccinationController(DatabaseConnectionDAO databaseConnectionDAO, VaccinationQueryBuilderDAO vaccinationQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.vaccinationQueryBuilderDAO = vaccinationQueryBuilderDAO;
  }

  /**
   * calculating age of the user by date of birth.
   *
   * @param dateOfBirth string dateOfBirth is user's age which is then used
   *                    for finding age of user.
   */
  private int calculateUserCurrentAge(String dateOfBirth) {
    final LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    final LocalDate currentDate = LocalDate.now();
    return Period.between(birthDate, currentDate).getYears();
  }

  /**
   * schedule vaccination for user.
   *
   * @param user user instance will use for schedule vaccination for that
   *             particular user.
   *
   * @throws VaccineException            if any error occurs while
   *                                     scheduling vaccination.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to database.
   */
  @Override
  public VaccineModel scheduleVaccination(User user) throws VaccineException, DatabaseConnectionException {

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet userVaccinatedResultSet =
             statement.executeQuery(vaccinationQueryBuilderDAO.getInformationOfDoseDate(user.getUserId()))) {

      if (userVaccinatedResultSet.next()) {
        return new VaccineModel(
            userVaccinatedResultSet.getInt(VaccineDatabaseConstant.VACCINE_ID),
            userVaccinatedResultSet.getInt(VaccineDatabaseConstant.USER_ID),
            userVaccinatedResultSet.getString(VaccineDatabaseConstant.VACCINE_TYPE),
            userVaccinatedResultSet.getString(VaccineDatabaseConstant.REGISTRATION_DATE),
            userVaccinatedResultSet.getString(VaccineDatabaseConstant.FIRST_DOSE_DATE),
            userVaccinatedResultSet.getString(VaccineDatabaseConstant.SECOND_DOSE_DATE),
            false
        );
      } else {
        return scheduleDate(user);
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * appoint new date for vaccination from this method
   *
   * @param user user instance will use for schedule vaccination for that
   *             particular user.
   *
   * @throws VaccineException            if any error occurs while
   *                                     checking user is eligible or not.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to database.
   */
  private VaccineModel scheduleDate(User user) throws VaccineException, DatabaseConnectionException {

    // calculate age of user
    final int userAge = calculateUserCurrentAge(user.getDateOfBirth());
    if (userAge >= 12) {

      // different vaccine types for user
      final String[] vaccineTypes =
          {"Pfizer-BioTech", "Moderna", "Johnsen & Jhonsen's", "Astrazeneca"};

      // calculate registration date
      final String registrationDate = LocalDate.now().toString();

      // calculate first dose date
      final String firstDoseDate = LocalDate.now().plusDays(2).toString();

      String vaccineType;
      if (userAge < 18) {
        vaccineType = vaccineTypes[0];
      } else {
        int randomLength = new Random().nextInt(vaccineTypes.length);
        vaccineType = vaccineTypes[randomLength];
      }
      String secondDoseDate;

      // calculate second dose date as per the vaccine type
      if (vaccineType.equals(vaccineTypes[0]) || vaccineType.equals(vaccineTypes[1])) {
        secondDoseDate = LocalDate.now().plusDays(28).toString();
      } else if (vaccineType.equals(vaccineTypes[3])) {
        secondDoseDate = LocalDate.now().plusDays(84).toString();
      } else {
        secondDoseDate = "Not Required";
      }

      final VaccineModel vaccineModel = new VaccineModel(
          user.getUserId(),
          vaccineType,
          registrationDate,
          firstDoseDate,
          secondDoseDate,
          true);

      try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
           final Statement statement = connection.createStatement()) {
        final String insertVaccineInformation = vaccinationQueryBuilderDAO.setVaccinationInformation(vaccineModel);
        int insertUserInfo = statement.executeUpdate(insertVaccineInformation);
        if (insertUserInfo <= 0) {
          throw new VaccineException("Sorry!! the vaccination is not schedule for you because of some issue !! please contact admin...");
        } else {
          return vaccineModel;
        }
      } catch (SQLException e) {
        throw new DatabaseConnectionException(e.getMessage(), e);
      }
    } else {
      throw new VaccineException("Sorry!! you are not eligible for vaccination.");
    }
  }
}
