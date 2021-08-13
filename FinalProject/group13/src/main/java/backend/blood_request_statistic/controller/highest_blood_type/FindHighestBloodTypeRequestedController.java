package backend.blood_request_statistic.controller.highest_blood_type;

import backend.blood_request_statistic.database.AllStatisticsQueryBuilderDAO;
import backend.blood_request_statistic.database.ReceiverRequestConstant;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * {@code FindHighestBloodTypeRequestedController} implements the
 * {@code FindHighestBloodTypeRequestedControllerDAO} to provide a concrete
 * implementation for the finding highest requested blood type.
 */
public final class FindHighestBloodTypeRequestedController implements
    FindHighestBloodTypeRequestedControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // All Statistics query builder instance.
  private final AllStatisticsQueryBuilderDAO
      allStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code FindHighestBloodTypeRequestedController} instance.
   *
   * @param databaseConnectionDAO        database connection instance.
   * @param allStatisticsQueryBuilderDAO all statistic builder
   *                                     query builder instance.
   */
  public FindHighestBloodTypeRequestedController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final AllStatisticsQueryBuilderDAO allStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.allStatisticsQueryBuilderDAO = allStatisticsQueryBuilderDAO;
  }

  /**
   * find blood group list from all blood receiver list
   *
   * @param allBloodReceiverList all blood receiver list to make a blood group list
   *
   * @return all active receiver blood group list
   *
   * @throws SQLException if any error occurs while
   *                      performing query to the database.
   */
  private List<String> prepareActiveBloodReceiverBloodType(final ResultSet allBloodReceiverList)
      throws SQLException {
    final List<String> activeBloodRequestBloodGroupList = new ArrayList<>();
    while (allBloodReceiverList.next()) {
      final String bloodGroup = allBloodReceiverList.getString(
          ReceiverRequestConstant.BLOOD_GROUP_COLUMN);

      activeBloodRequestBloodGroupList.add(bloodGroup);
    }
    return activeBloodRequestBloodGroupList;
  }

  /**
   * find highest blood type from the list
   *
   * @param bloodGroup List to find count of each blood group.
   *
   * @return highest blood type requested.
   */
  public static List<String> getHighestBloodType(List<String> bloodGroup) {
    int highest = 0;
    HashMap<String, Integer> frequency = new HashMap<>();
    Set<String> frequencyList = new HashSet<>(bloodGroup);
    for (String group : frequencyList) {
      frequency.put(group, Collections.frequency(bloodGroup, group)); //create blood group as a key and frequency as value
    }
    List<String> highestType = new ArrayList<>();
    for (Map.Entry<String, Integer> b : frequency.entrySet()) {
      if (b.getValue() >= highest) {
        highest = b.getValue();
      }
    }

    //add all the highest blood group type in list
    for (Map.Entry<String, Integer> b : frequency.entrySet()) {
      if (highest == b.getValue()) {
        highestType.add(b.getKey());
      }
    }
    return highestType;
  }

  /**
   * Finds highest requested blood type of all blood receiver requests.
   *
   * @return highest requested blood type.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   * @throws ReceiverStatisticsException if any error occurs while
   *                                     performing query in the database.
   */
  @Override
  public List<String> findHighestRequestedBloodGroup() throws
      DatabaseConnectionException, ReceiverStatisticsException {
    List<String> receiverAgeList;

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet BloodReceiverResultSet = statement.executeQuery(
             allStatisticsQueryBuilderDAO.selectBloodReceiverRequestQuery())) {

      receiverAgeList = prepareActiveBloodReceiverBloodType(BloodReceiverResultSet);
      List<String> highestBloodType = getHighestBloodType(receiverAgeList);
      if (highestBloodType.isEmpty()) {
        throw new ReceiverStatisticsException("Error in calculating the " +
            "highest receiver requested blood group. ");
      } else {
        return highestBloodType;
      }
    } catch (SQLException | DatabaseConnectionException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}