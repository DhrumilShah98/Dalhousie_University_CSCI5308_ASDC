package backend.demographic_information.database;

/**
 * {@code DemographicInformationQueryBuilderDAO} provides a contract for the
 * user to fetch active users and bloodbanks information.
 */
public interface DemographicInformationQueryBuilderDAO {

  /**
   * Gets the query to see information of active user's status in
   * particular province.
   *
   * @param provinceName province name is used to get the inforamtion of
   *                     that region.
   *
   * @return string query to get information of active user's status in that
   * province.
   */
  String fetchActiveUserInProvince(String provinceName);

  /**
   * Gets the query to see information of active bloodbank's status in
   * particular province.
   *
   * @param provinceName province name is used to get the information of
   *                     that region.
   *
   * @return string query to get information of active bloodbank's status in
   * that province.
   */
  String fetchActiveBloodBankInProvince(String provinceName);
}
