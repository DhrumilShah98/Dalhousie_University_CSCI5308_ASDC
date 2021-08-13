package backend.blood_donation_statistic.controller.average_blood_donor_age;

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
import java.util.List;

/**
 * {@code AverageBloodDonorAgeController} implements the
 * {@code AverageBloodDonorAgeControllerDAO} to provide a concrete
 * implementation for calculating average blood donor age.
 */
public class AverageBloodDonorAgeController
    implements AverageBloodDonorAgeControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood donation statistics query builder instance.
  private final BloodDonationStatisticsQueryBuilderDAO
      bloodDonationStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code AverageBloodDonorAgeController} instance.
   *
   * @param databaseConnectionDAO                  database connection instance.
   * @param bloodDonationStatisticsQueryBuilderDAO blood donation statistics
   *                                               query builder instance.
   */
  public AverageBloodDonorAgeController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final BloodDonationStatisticsQueryBuilderDAO bloodDonationStatisticsQueryBuilderDAO) {
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
  private List<Integer> prepareBloodDonorAverageAge(
      final ResultSet allBloodDonorResultSet)
      throws SQLException {
    final List<Integer> bloodDonorAgeList = new ArrayList<>();
    while (allBloodDonorResultSet.next()) {
      final String dateOfBirth =
          allBloodDonorResultSet.getString(BloodDonationStatisticsDatabaseConstant.USER_DATE_OF_BIRTH_COLUMN);
      final int donorAge =
          getBloodDonorAge(dateOfBirth);
      bloodDonorAgeList.add(donorAge);
    }
    return bloodDonorAgeList;
  }

  /**
   * @param dateOfBirth each blood donors date of birth.
   *
   * @return int age of the blood donor.
   */
  private int getBloodDonorAge(final String dateOfBirth) {
    final LocalDate currentDate = LocalDate.now();
    LocalDate dob = LocalDate.parse(dateOfBirth);
    Period period = Period.between(dob, currentDate);
    return Math.abs(period.getYears());
  }

  /**
   * @param allDonorAgeList list of all blood donors age.
   *
   * @return int average age of blood donors.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating blood donors average
   *                                          age.
   */
  private int getBloodDonorAverageAge(
      final List<Integer> allDonorAgeList)
      throws BloodDonationStatisticsException {
    int sumOfAge = 0;
    final int allDonorAgeListSize = allDonorAgeList.size();
    for (Integer integer : allDonorAgeList) {
      sumOfAge = sumOfAge + integer;
    }
    try {
      return sumOfAge / allDonorAgeListSize;
    } catch (ArithmeticException e) {
      throw new BloodDonationStatisticsException(e.toString());
    }
  }

  /**
   * @return int of average blood donor age.
   *
   * @throws BloodDonationStatisticsException if any error occurs while
   *                                          calculating average blood donor
   *                                          age.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  @Override
  public int viewAverageBloodDonorAge()
      throws BloodDonationStatisticsException,
      DatabaseConnectionException {
    List<Integer> allDonorAgeList;
    try (final Connection connection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement statement =
             connection.createStatement();
         final ResultSet allBloodDonorResultSet =
             statement.executeQuery(bloodDonationStatisticsQueryBuilderDAO.selectBloodDonationStatisticsQuery())) {
      allDonorAgeList = prepareBloodDonorAverageAge(allBloodDonorResultSet);
      final int allDonorAverageAge = getBloodDonorAverageAge(allDonorAgeList);
      if (allDonorAverageAge < 0) {
        throw new BloodDonationStatisticsException("Error in calculating the average blood donor age. ");
      } else {
        return allDonorAverageAge;
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}
