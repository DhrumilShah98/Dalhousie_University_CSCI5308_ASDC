package backend.vaccination.database;

import backend.vaccination.model.VaccineModel;

/**
 * {@code VaccinationQueryBuilderDAO} provides a contract for the
 * user to get or insert vaccination information.
 */
public interface VaccinationQueryBuilderDAO {

  /**
   * Gets the query to see information of user's first dose date.
   *
   * @param id user id to track user's vaccination status.
   *
   * @return string query to get information of first dose date.
   */
  String getInformationOfFirstDose(int id);

  /**
   * Gets the query to insert value of user's vaccination details.
   *
   * @param vaccineModel model class is used to set the value of user's
   *                     vaccination process.
   *
   * @return string query to set value of user's vaccination details.
   */
  String setVaccinationInformation(VaccineModel vaccineModel);

  /**
   * Gets the query to see information of user's date of taking vaccine.
   *
   * @param id user id to track user's vaccination status.
   *
   * @return string query to get information of user's dose date.
   */
  String getInformationOfDoseDate(int id);
}
