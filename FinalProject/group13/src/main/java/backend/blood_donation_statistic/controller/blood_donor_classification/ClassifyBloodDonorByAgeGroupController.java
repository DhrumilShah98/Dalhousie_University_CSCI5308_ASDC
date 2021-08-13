package backend.blood_donation_statistic.controller.blood_donor_classification;

import backend.blood_donation_statistic.database.BloodDonationStatisticsDatabaseConstant;
import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilderDAO;
import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
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
 * {@code ClassifyBloodDonorByAgeGroupController} implements the
 * {@code ClassifyBloodDonorByAgeGroupControllerDAO} to provide a concrete
 * implementation for classifying blood donors by age group.
 */
public class ClassifyBloodDonorByAgeGroupController
    implements ClassifyBloodDonorByAgeGroupControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood donation statistics query builder instance.
  private final BloodDonationStatisticsQueryBuilderDAO
      bloodDonationStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code ClassifyBloodDonorByAgeGroupController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodDonationStatisticsQueryBuilderDAO blood donation statistics
   *                                               query builder instance.
   */
  public ClassifyBloodDonorByAgeGroupController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodDonationStatisticsQueryBuilderDAO =
        bloodDonationStatisticsQueryBuilderDAO;
  }

  /**
   * @param allBloodDonorResultSet blood donation statistics result set.
   *
   * @return List<Integer> of blood donors age.
   *
   * @throws SQLException if any error occurs while running the query.
   */
  private List<Integer> prepareBloodDonorAge(ResultSet allBloodDonorResultSet)
      throws SQLException {
    final List<Integer> bloodDonorAgeList = new ArrayList<>();
    while (allBloodDonorResultSet.next()) {
      final String dateOfBirth =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN);
      final int age = getAge(dateOfBirth);
      bloodDonorAgeList.add(age);
    }
    return bloodDonorAgeList;
  }

  /**
   * @param bloodDonorAgeList list of all blood donors age.
   *
   * @return Map<String, Float> of blood donor classification by age.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          connecting to the database.
   */
  private Map<String, Float> getAverageAgeClassification(
      final List<Integer> bloodDonorAgeList) throws BloodDonationStatisticsException {
    int group18TO25TotalUsers = 0;
    int group26TO40TotalUsers = 0;
    int group41TO60TotalUsers = 0;
    int group61AboveTotalUsers = 0;
    Map<String, Float> bloodDonorAgeGroup = new HashMap<>();
    bloodDonorAgeGroup.put("18 - 25", 0f);
    bloodDonorAgeGroup.put("26 - 40", 0f);
    bloodDonorAgeGroup.put("41 - 60", 0f);
    bloodDonorAgeGroup.put("Above 60", 0f);
    for (Integer bloodDonorAge : bloodDonorAgeList) {
      if (bloodDonorAge >= 18 && bloodDonorAge <= 25) {
        group18TO25TotalUsers++;
      } else if (bloodDonorAge >= 26 && bloodDonorAge <= 40) {
        group26TO40TotalUsers++;
      } else if (bloodDonorAge >= 41 & bloodDonorAge <= 60) {
        group41TO60TotalUsers++;
      } else {
        group61AboveTotalUsers++;
      }
    }
    float totalCount = (
        group18TO25TotalUsers +
            group26TO40TotalUsers +
            group41TO60TotalUsers +
            group61AboveTotalUsers);
    try {
      float group18TO25TotalUsersPercentage =
          (group18TO25TotalUsers / totalCount) * 100;
      float group26TO40TotalUsersPercentage =
          (group26TO40TotalUsers / totalCount) * 100;
      float group41TO60TotalUsersPercentage =
          (group41TO60TotalUsers / totalCount) * 100;
      float group61AboveTotalUsersPercentage =
          (group61AboveTotalUsers / totalCount) * 100;
      bloodDonorAgeGroup.put("18 - 25", group18TO25TotalUsersPercentage);
      bloodDonorAgeGroup.put("26 - 40", group26TO40TotalUsersPercentage);
      bloodDonorAgeGroup.put("41 - 60", group41TO60TotalUsersPercentage);
      bloodDonorAgeGroup.put("Above 60", group61AboveTotalUsersPercentage);
      return bloodDonorAgeGroup;
    } catch (ArithmeticException e) {
      throw new BloodDonationStatisticsException(e.toString());
    }
  }

  /**
   * @param dateOfBirth each blood donors date of birth.
   *
   * @return int age of the blood donor.
   */
  private int getAge(String dateOfBirth) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate dob = LocalDate.parse(dateOfBirth);
    Period period = Period.between(dob, currentDate);
    return Math.abs(period.getYears());
  }

  /**
   * @return Map<String, Float> of blood donor classification by age.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          classifying blood donor by age
   *                                          group.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public Map<String, Float> bloodDonorAgeClassification()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException {
    List<Integer> bloodDonorAgeList;
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet allBloodDonorResultSet =
             statement.executeQuery(bloodDonationStatisticsQueryBuilderDAO.selectBloodDonationStatisticsQuery())) {
      bloodDonorAgeList = prepareBloodDonorAge(allBloodDonorResultSet);
      return getAverageAgeClassification(bloodDonorAgeList);
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
