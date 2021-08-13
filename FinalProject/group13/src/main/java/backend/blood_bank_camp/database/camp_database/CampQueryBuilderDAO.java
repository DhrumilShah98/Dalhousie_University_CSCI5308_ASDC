package backend.blood_bank_camp.database.camp_database;

import backend.blood_bank_camp.model.Camp;

/**
 * {@code CampQueryBuilderDAO} provides a contract for the
 * blood bank registration.
 */
public interface CampQueryBuilderDAO {

  /**
   * Gets the query to insert this camp in the table.
   *
   * @param camp to insert in the table.
   *
   * @return string query to insert this camp in the table.
   */
  String insertCampQuery(Camp camp);

  /**
   * Gets the query to select this camp in the table.
   *
   * @return string query to select this camp in the table.
   */
  String selectAllCampQuery();
}
