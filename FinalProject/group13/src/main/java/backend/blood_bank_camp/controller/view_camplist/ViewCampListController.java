package backend.blood_bank_camp.controller.view_camplist;

import backend.blood_bank_camp.database.camp_database.CampDatabaseConstant;
import backend.blood_bank_camp.database.camp_database.CampQueryBuilderDAO;
import backend.blood_bank_camp.exception.CampRegistrationException;
import backend.blood_bank_camp.model.Camp;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ViewCampListController} implements the
 * {@code ViewCampListControllerDAO} to provide a concrete
 * implementation for the All camp list.
 */
public final class ViewCampListController implements ViewCampListControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO databaseConnectionDAO;

  // Camp query builder instance.
  private final CampQueryBuilderDAO campQueryBuilderDAO;

  /**
   * Constructs this {@code ViewCampListController} instance.
   *
   * @param databaseConnectionDAO database connection instance.
   * @param campQueryBuilderDAO   camp query builder instance.
   */
  public ViewCampListController(final DatabaseConnectionDAO databaseConnectionDAO,
                                final CampQueryBuilderDAO campQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.campQueryBuilderDAO = campQueryBuilderDAO;
  }

  /**
   * @param AllCampResultSet to make list of camp
   *
   * @return AllCampList
   *
   * @throws SQLException if any error occurs while
   *                      performing query to database.
   */
  public List<Camp> prepareAllCampList(final ResultSet AllCampResultSet) throws SQLException {
    final List<Camp> AllCampList = new ArrayList<>();
    while (AllCampResultSet.next()) {
      final int campId = AllCampResultSet.getInt(CampDatabaseConstant.CAMP_ID_COLUMN);
      final String organizer = AllCampResultSet.getString(CampDatabaseConstant.ORGANIZER_NAME_COLUMN);
      final int bloodBankId = AllCampResultSet.getInt(CampDatabaseConstant.BLOOD_BANK_ID_COLUMN);
      final String date = AllCampResultSet.getString(CampDatabaseConstant.DATE_COLUMN);
      final String time = AllCampResultSet.getString(CampDatabaseConstant.TIME_COLUMN);
      final int availableCapacity = AllCampResultSet.getInt(CampDatabaseConstant.CAMP_AVAILABLE_CAPACITY);
      final String venue = AllCampResultSet.getString(CampDatabaseConstant.VENUE_COLUMN);
      final String city = AllCampResultSet.getString(CampDatabaseConstant.CITY_COLUMN);
      final String contact = AllCampResultSet.getString(CampDatabaseConstant.CONTACT_NUMBER_COLUMN);

      final Camp camp = new Camp(campId, organizer, bloodBankId, date, time, availableCapacity, venue, city, contact);
      AllCampList.add(camp);
    }
    return AllCampList;
  }

  /**
   * @return All camp list.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public final List<Camp> viewAllCamp() throws DatabaseConnectionException {

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet AllCampResultSet = statement.executeQuery(campQueryBuilderDAO.selectAllCampQuery())) {

      return prepareAllCampList(AllCampResultSet);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}