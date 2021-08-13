package backend.blood_request_statistic.controller.classification_on_age;

import backend.blood_request_statistic.database.AllStatisticsQueryBuilderDAO;
import backend.blood_request_statistic.database.ReceiverRequestConstant;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code CountReceiverRequestByAgeGroupController} implements the
 * {@code CountReceiverRequestByAgeGroupControllerDAO} to provide a concrete
 * implementation for the finding classification of receiver age.
 */
public final class CountReceiverRequestByAgeGroupController implements
    CountReceiverRequestByAgeGroupControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // All Statistics query builder instance.
  private final AllStatisticsQueryBuilderDAO
      allStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code CountReceiverRequestByAgeGroupController} instance.
   *
   * @param databaseConnectionDAO        database connection instance.
   * @param allStatisticsQueryBuilderDAO all statistic builder
   *                                     query builder instance.
   */
  public CountReceiverRequestByAgeGroupController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final AllStatisticsQueryBuilderDAO allStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.allStatisticsQueryBuilderDAO = allStatisticsQueryBuilderDAO;
  }

  /**
   * find age list from all blood receiver list
   *
   * @param allBloodReceiverList all blood receiver list to make a age list
   *
   * @return active blood receiver age list
   *
   * @throws SQLException if any error occurs while
   *                      performing query to the database.
   */
  private List<Integer> prepareBloodReceiverAge(final ResultSet allBloodReceiverList)
      throws SQLException {
    final List<Integer> bloodReceiverAgeList = new ArrayList<>();
    while (allBloodReceiverList.next()) {
      final String dateOfBirth = allBloodReceiverList.getString(
          ReceiverRequestConstant.USER_DATE_OF_BIRTH_COLUMN);
      final int age = getAge(dateOfBirth);
      bloodReceiverAgeList.add(age);
    }
    return bloodReceiverAgeList;
  }

  /**
   * find age from date of birth.
   *
   * @param dateOfBirth to find age from it.
   *
   * @return years
   */
  private int getAge(String dateOfBirth) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate dob = LocalDate.parse(dateOfBirth);
    Period period = Period.between(dob, currentDate);
    return Math.abs(period.getYears());
  }


  /**
   * perform classification by age group with count
   *
   * @param bloodReceiverAgeList to classify into age groups
   *
   * @return usersGroupAge map
   */
  private Map<String, Float> getAverageAgeClassification(List<Integer> bloodReceiverAgeList) {
    int group18TO25TotalUsers = 0;
    int group26TO40TotalUsers = 0;
    int group41TO60TotalUsers = 0;
    int group61AboveTotalUsers = 0;
    Map<String, Float> usersGroupAge = new HashMap<>();
    usersGroupAge.put("18 - 25", 0f);
    usersGroupAge.put("26 - 40", 0f);
    usersGroupAge.put("41 - 60", 0f);
    usersGroupAge.put("Above 60", 0f);
    for (Integer userAge : bloodReceiverAgeList) {
      if (userAge >= 18 && userAge <= 25) {
        group18TO25TotalUsers++;
      } else if (userAge >= 26 && userAge <= 40) {
        group26TO40TotalUsers++;
      } else if (userAge >= 41 && userAge <= 60) {
        group41TO60TotalUsers++;
      } else {
        group61AboveTotalUsers++;
      }
    }
    float totalCount = (group18TO25TotalUsers + group26TO40TotalUsers + group41TO60TotalUsers + group61AboveTotalUsers);
    usersGroupAge.put("18 - 25", (group18TO25TotalUsers / totalCount) * 100);
    usersGroupAge.put("26 - 40", (group26TO40TotalUsers / totalCount) * 100);
    usersGroupAge.put("41 - 60", (group41TO60TotalUsers / totalCount) * 100);
    usersGroupAge.put("Above 60", (group61AboveTotalUsers / totalCount) * 100);
    return usersGroupAge;
  }

  /**
   * Performs view age group classification of all blood receiver requests.
   *
   * @return age group classification of all blood receiver requests.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public Map<String, Float> viewReceiverAgeClassification() throws DatabaseConnectionException {
    List<Integer> receiverAgeList;

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet BloodReceiverResultSet = statement.executeQuery(
             allStatisticsQueryBuilderDAO.selectBloodReceiverRequestQuery())) {

      receiverAgeList = prepareBloodReceiverAge(BloodReceiverResultSet);
      return getAverageAgeClassification(receiverAgeList);
    } catch (SQLException | DatabaseConnectionException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}