package backend.blood_bank_camp.database.camp_database;

import backend.blood_bank_camp.model.Camp;

import static backend.blood_bank_camp.database.camp_database.CampDatabaseConstant.*;

/**
 * {@code CampQueryBuilder} implements the
 * {@code CampQueryBuilderDAO} to provide a concrete
 * implementation for the camp registration.
 * This class is implemented using the Singleton Design Pattern.
 */
public class CampQueryBuilder implements CampQueryBuilderDAO {

  // Static instance of this class
  private static CampQueryBuilder instance;

  /**
   * Constructs this {@code CampQueryBuilder} instance.
   */
  private CampQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code CampQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code CampQueryBuilder} class.
   */
  public static CampQueryBuilder getInstance() {
    if (instance == null) {
      instance = new CampQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert this camp in the table.
   *
   * @param camp to insert in the table.
   *
   * @return string query to insert this camp in the table.
   */
  @Override
  public String insertCampQuery(Camp camp) {
    return "INSERT INTO " + CAMP_TABLE + "(" +
        ORGANIZER_NAME_COLUMN + ", " +
        BLOOD_BANK_ID_COLUMN + ", " +
        DATE_COLUMN + ", " +
        TIME_COLUMN + ", " +
        CAMP_AVAILABLE_CAPACITY + ", " +
        VENUE_COLUMN + ", " +
        CITY_COLUMN + ", " +
        CONTACT_NUMBER_COLUMN + ")" +
        "VALUES (" +
        "\"" + camp.getOrganizerName() + "\", " +
        "\"" + camp.getBloodBankId() + "\", " +
        "\"" + camp.getDate() + "\", " +
        "\"" + camp.getTime() + "\", " +
        "\"" + camp.getAvailableCapacity() + "\", " +
        "\"" + camp.getVenue() + "\", " +
        "\"" + camp.getCity() + "\", " +
        "\"" + camp.getContactNumber() + "\")";
  }

  /**
   * Gets the query to select this camp in the table.
   *
   * @return string query to select this camp in the table.
   */
  @Override
  public String selectAllCampQuery() {
    return "SELECT " +
        CAMP_ID_COLUMN + ", " +
        ORGANIZER_NAME_COLUMN + ", " +
        BLOOD_BANK_ID_COLUMN + ", " +
        DATE_COLUMN + ", " +
        TIME_COLUMN + ", " +
        CAMP_AVAILABLE_CAPACITY + "," +
        VENUE_COLUMN + ", " +
        CITY_COLUMN + ", " +
        CONTACT_NUMBER_COLUMN +
        " FROM " +
        CAMP_TABLE + ";";
  }
}
