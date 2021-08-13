package backend.vaccination.database;

import backend.authentication.user.database.UserDatabaseConstant;
import backend.authentication.user.database.login_with_contact_number.UserLoginWithContactNumberQueryBuilder;
import backend.authentication.user.model.User;
import backend.vaccination.model.VaccineModel;

/**
 * {@code VaccinationQueryBuilder} implements the
 * {@code VaccinationQueryBuilderDAO} to provide a concrete
 * implementation for the schedule and see the vaccination information.
 * This class is implemented using the Singleton Design Pattern.
 */
public final class VaccinationQueryBuilder implements VaccinationQueryBuilderDAO {

  // Static instance of this class
  private static VaccinationQueryBuilder instance;

  /**
   * Constructs this {@code VaccinationQueryBuilder} instance.
   */
  private VaccinationQueryBuilder() {
    //Required private empty constructor
  }

  /**
   * Instantiates this {@code VaccinationQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code VaccinationQueryBuilder} class.
   */
  public static VaccinationQueryBuilder getInstance() {
    if (instance == null) {
      instance = new VaccinationQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to see information of user's first dose date.
   *
   * @param id user id to track user's vaccination status.
   *
   * @return string query to get information of first dose date.
   */
  @Override
  public String getInformationOfFirstDose(int id) {
    return "SELECT "
        + VaccineDatabaseConstant.FIRST_DOSE_DATE + " FROM "
        + VaccineDatabaseConstant.VACCINE_TABLE + " WHERE "
        + VaccineDatabaseConstant.USER_ID + " = " + id + ";";
  }

  /**
   * Gets the query to insert value of user's vaccination details.
   *
   * @param vaccineModel model class is used to set the value of user's
   *                     vaccination process.
   *
   * @return string query to set value of user's vaccination details.
   */
  @Override
  public String setVaccinationInformation(VaccineModel vaccineModel) {
    return "INSERT INTO "
        + VaccineDatabaseConstant.VACCINE_TABLE + "("
        + VaccineDatabaseConstant.USER_ID + ","
        + VaccineDatabaseConstant.VACCINE_TYPE + ","
        + VaccineDatabaseConstant.REGISTRATION_DATE + ","
        + VaccineDatabaseConstant.FIRST_DOSE_DATE + ","
        + VaccineDatabaseConstant.SECOND_DOSE_DATE + ") VALUES ( "
        + vaccineModel.getUserId() + ",'"
        + vaccineModel.getVaccineType() + "','"
        + vaccineModel.getRegistrationDate() + "','"
        + vaccineModel.getFirstDoseDate() + "','"
        + vaccineModel.getSecondDoseDate() + "');";
  }

  /**
   * Gets the query to see information of user's date of taking vaccine.
   *
   * @param userId user id to track user's vaccination status.
   *
   * @return string query to get information of user's dose date.
   */
  @Override
  public String getInformationOfDoseDate(int userId) {
    return "SELECT "
        + VaccineDatabaseConstant.VACCINE_ID + ","
        + VaccineDatabaseConstant.USER_ID + ","
        + VaccineDatabaseConstant.VACCINE_TYPE + ","
        + VaccineDatabaseConstant.REGISTRATION_DATE + ","
        + VaccineDatabaseConstant.FIRST_DOSE_DATE + ","
        + VaccineDatabaseConstant.SECOND_DOSE_DATE + " FROM "
        + VaccineDatabaseConstant.VACCINE_TABLE + " WHERE "
        + VaccineDatabaseConstant.USER_ID + " = " + userId + ";";
  }
}