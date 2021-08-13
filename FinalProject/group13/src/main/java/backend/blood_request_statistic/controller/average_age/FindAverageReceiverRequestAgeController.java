package backend.blood_request_statistic.controller.average_age;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
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
import java.util.List;

/**
 * {@code FindAverageReceiverRequestAgeController} implements the
 * {@code FindAverageReceiverRequestAgeControllerDAO} to provide a concrete
 * implementation for the finding average receiver requester age.
 */
public final class FindAverageReceiverRequestAgeController implements
    FindAverageReceiverRequestAgeControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // All Statistics query builder instance.
  private final AllStatisticsQueryBuilderDAO
      allStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code FindAverageReceiverRequestAgeController} instance.
   *
   * @param databaseConnectionDAO        database connection instance.
   * @param allStatisticsQueryBuilderDAO all statistic builder
   *                                     query builder instance.
   */
  public FindAverageReceiverRequestAgeController(
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
  private List<Integer> prepareActiveBloodReceiverAvgAge(final ResultSet allBloodReceiverList)
      throws SQLException {
    final List<Integer> activeBloodReceiverAgeList = new ArrayList<>();
    while (allBloodReceiverList.next()) {
      final String dateOfBirth = allBloodReceiverList.getString(
          ReceiverRequestConstant.USER_DATE_OF_BIRTH_COLUMN);
      final int age = getAge(dateOfBirth);
      activeBloodReceiverAgeList.add(age);
    }
    return activeBloodReceiverAgeList;
  }

  /**
   * find age from date of birth
   *
   * @param dateOfBirth to find age of user
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
   * find average age from age list
   *
   * @param ageList to find average age.
   *
   * @return average age
   */
  private int getAverageAge(List<Integer> ageList) {
    int ageSum = 0;
    for (int i = 0; i < ageList.size() - 1; i++) {
      ageSum = ageSum + ageList.get(i);
    }
    return ageSum / ageList.size();
  }

  /**
   * Performs view average age of all blood receiver requests.
   *
   * @return all blood receiver request made by the user.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   * @throws ReceiverStatisticsException if any error occurs while
   *                                     performing query to the database.
   */
  @Override
  public int viewReceiverAverageAge() throws DatabaseConnectionException, ReceiverStatisticsException {
    List<Integer> receiverAgeList;

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet activeBloodReceiverResultSet = statement.executeQuery(
             allStatisticsQueryBuilderDAO.selectBloodReceiverRequestQuery())) {

      receiverAgeList = prepareActiveBloodReceiverAvgAge(activeBloodReceiverResultSet);
      int allReceiverAverageAge = getAverageAge(receiverAgeList);
      if (allReceiverAverageAge < 0) {
        throw new ReceiverStatisticsException("Error in calculating the " +
            "average blood receiver age. ");
      } else {
        return allReceiverAverageAge;
      }
    } catch (SQLException | DatabaseConnectionException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}