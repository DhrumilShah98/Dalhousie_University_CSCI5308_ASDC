package backend.blood_donation_statistic.controller.highest_blood_type_donated;

import backend.blood_donation_statistic.database.BloodDonationStatisticsDatabaseConstant;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilderDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * {@code HighestBloodTypeDonatedController} implements the
 * {@code HighestBloodTypeDonatedControllerDAO} to provide a concrete
 * implementation for calculating average blood donor age.
 */
public class HighestBloodTypeDonatedController
    implements HighestBloodTypeDonatedControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood donation statistics query builder instance.
  private final BloodDonationStatisticsQueryBuilderDAO
      bloodDonationStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code HighestBloodTypeDonatedController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodDonationStatisticsQueryBuilderDAO blood donation statistics
   *                                               query builder instance.
   */
  public HighestBloodTypeDonatedController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.bloodDonationStatisticsQueryBuilderDAO = bloodDonationStatisticsQueryBuilderDAO;
  }

  /**
   * @param allBloodDonorResultSet blood donation statistics result set.
   *
   * @return List<String> of highest blood type donated.
   *
   * @throws SQLException if any error occurs while running the query.
   */
  private List<String> prepareBloodDonorBloodGroup(
      final ResultSet allBloodDonorResultSet)
      throws SQLException {
    final List<String> bloodDonorBloodGroupList = new ArrayList<>();
    while (allBloodDonorResultSet.next()) {
      final String donorBloodGroup =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.USER_BLOOD_GROUP_COLUMN);
      bloodDonorBloodGroupList.add(donorBloodGroup);
    }
    return bloodDonorBloodGroupList;
  }

  /**
   * @param donorBloodGroupList of all blood donors.
   *
   * @return List<String> of highest donated blood group
   */
  private List<String> getHighestDonatedBloodGroup(
      final List<String> donorBloodGroupList) {
    int highest = 0;
    HashMap<String, Integer> donorBloodGroupMap = new HashMap<>();
    Set<String> bloodGroupFrequencyList = new HashSet<>(donorBloodGroupList);
    for (String bloodGroup : bloodGroupFrequencyList) {
      // here bloodGroup is the key & frequency is the value
      donorBloodGroupMap.put(bloodGroup,
          Collections.frequency(donorBloodGroupList, bloodGroup));
    }
    for (Map.Entry<String, Integer> map : donorBloodGroupMap.entrySet()) {
      if (map.getValue() >= highest) {
        highest = map.getValue();
      }
    }
    List<String> highestBloodGroup = new ArrayList<>();
    for (Map.Entry<String, Integer> map : donorBloodGroupMap.entrySet()) {
      if (highest == map.getValue()) {
        highestBloodGroup.add(map.getKey());
      }
    }
    return highestBloodGroup;
  }

  /**
   * @return List<String> of highest blood type donated.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating highest blood type
   *                                          donated.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public List<String> findHighestDonatedBloodGroup()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException {
    List<String> donorBloodGroupList;
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet allBloodDonorResultSet =
             statement.executeQuery(bloodDonationStatisticsQueryBuilderDAO.selectBloodDonationStatisticsQuery())) {
      donorBloodGroupList =
          prepareBloodDonorBloodGroup(allBloodDonorResultSet);
      List<String> highestBloodGroup =
          getHighestDonatedBloodGroup(donorBloodGroupList);
      if (highestBloodGroup.isEmpty()) {
        throw new BloodDonationStatisticsException("Error in calculating the highest donated blood group. ");
      } else {
        return highestBloodGroup;
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
