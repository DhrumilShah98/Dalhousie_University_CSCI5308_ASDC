package backend.blood_bank_rating_analysis.analysis_by_age_group.controller;

import backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupQueryBuilderDAO;
import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import backend.blood_bank_rating_analysis.analysis_by_age_group.model.BloodBankRatingAnalysisAgeGroup;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.AGE_DURING_RATING_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.STAR_COLUMN;

/**
 * {@code BloodBankRatingAnalysisAgeGroupController} implements the
 * {@code BloodBankRatingAnalysisAgeGroupControllerDAO} to provide a concrete
 * implementation for rating analysis by age group.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisAgeGroupController
    implements BloodBankRatingAnalysisAgeGroupControllerDAO {

  // Age group between 18 to 25.
  private static final String _18_TO_25 = "18 - 25";

  // Age group between 26 to 40.
  private static final String _26_TO_40 = "26 - 40";

  // Age group between 41 to 60.
  private static final String _41_TO_60 = "41 - 60";

  // Age group above 60.
  private static final String ABOVE_60 = "Above 60";

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank rating analysis by age group query builder instance.
  private final BloodBankRatingAnalysisAgeGroupQueryBuilderDAO
      bloodBankRatingAnalysisAgeGroupQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankRatingAnalysisAgeGroupController} instance.
   *
   * @param databaseConnectionDAO                          database connection
   *                                                       instance.
   * @param bloodBankRatingAnalysisAgeGroupQueryBuilderDAO blood bank
   *                                                       rating analysis by
   *                                                       age group
   *                                                       query builder
   *                                                       instance.
   */
  public BloodBankRatingAnalysisAgeGroupController(final DatabaseConnectionDAO databaseConnectionDAO,
                                                   final BloodBankRatingAnalysisAgeGroupQueryBuilderDAO
                                                       bloodBankRatingAnalysisAgeGroupQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankRatingAnalysisAgeGroupQueryBuilderDAO =
        bloodBankRatingAnalysisAgeGroupQueryBuilderDAO;
  }

  /**
   * Prepares a list of blood bank rating analysis by age group instances.
   *
   * @param bloodBankRatingResultSet blood bank rating result set.
   *
   * @return a list of blood bank rating analysis by age group instances.
   *
   * @throws SQLException if any error occurs while accessing the result set.
   */
  private List<BloodBankRatingAnalysisAgeGroup> prepareBloodBankRating(final ResultSet bloodBankRatingResultSet)
      throws SQLException {
    final List<BloodBankRatingAnalysisAgeGroup> bloodBankRatingList = new ArrayList<>();
    while (bloodBankRatingResultSet.next()) {
      final int ageDuringRating = bloodBankRatingResultSet
          .getInt(AGE_DURING_RATING_COLUMN);
      final float star = bloodBankRatingResultSet
          .getFloat(STAR_COLUMN);
      final BloodBankRatingAnalysisAgeGroup bloodStock =
          new BloodBankRatingAnalysisAgeGroup(star, ageDuringRating);
      bloodBankRatingList.add(bloodStock);
    }
    return bloodBankRatingList;
  }

  /**
   * Analyze blood bank rating by age group.
   * Perform analysis on basis of {@code 4} age groups.
   *
   * @param bloodBankRatingList a list of blood bank rating analysis age group.
   *
   * @return map of blood bank rating analysis by age group.
   */
  private Map<String, Float> analyzeBloodBankRating(final List<BloodBankRatingAnalysisAgeGroup> bloodBankRatingList) {
    // sort the ratings in ascending order.
    final int bloodBankRatingListSize = bloodBankRatingList.size();
    for (int i = 0; i < bloodBankRatingListSize - 1; ++i) {
      for (int j = 0; j < bloodBankRatingListSize - i - 1; ++j) {
        final BloodBankRatingAnalysisAgeGroup rating1 =
            bloodBankRatingList.get(j);
        final BloodBankRatingAnalysisAgeGroup rating2 =
            bloodBankRatingList.get(j + 1);
        if (rating1.getStar() > rating2.getStar()) {
          bloodBankRatingList.set(j, rating2);
          bloodBankRatingList.set(j + 1, rating1);
        }
      }
    }
    // perform age group analysis from rating and prepare a hash map.
    int group18TO25TotalUsers = 0;
    int group26TO40TotalUsers = 0;
    int group41TO60TotalUsers = 0;
    int group61AboveTotalUsers = 0;
    final Map<String, Float> usersGroupRating = new HashMap<>();
    usersGroupRating.put(_18_TO_25, 0f);
    usersGroupRating.put(_26_TO_40, 0f);
    usersGroupRating.put(_41_TO_60, 0f);
    usersGroupRating.put(ABOVE_60, 0f);
    for (final BloodBankRatingAnalysisAgeGroup bloodBankRatingAnalysisAgeGroup :
        bloodBankRatingList) {
      final int userAge = bloodBankRatingAnalysisAgeGroup.getAge();
      final float userStars = bloodBankRatingAnalysisAgeGroup.getStar();
      if (userAge >= 18 && userAge <= 25) {
        group18TO25TotalUsers++;
        Float totalGroupStars = usersGroupRating.get(_18_TO_25);
        totalGroupStars += userStars;
        usersGroupRating.put(_18_TO_25, totalGroupStars);
      } else if (userAge >= 26 && userAge <= 40) {
        group26TO40TotalUsers++;
        Float totalGroupStars = usersGroupRating.get(_26_TO_40);
        totalGroupStars += userStars;
        usersGroupRating.put(_26_TO_40, totalGroupStars);
      } else if (userAge >= 41 && userAge <= 60) {
        group41TO60TotalUsers++;
        Float totalGroupStars = usersGroupRating.get(_41_TO_60);
        totalGroupStars += userStars;
        usersGroupRating.put(_41_TO_60, totalGroupStars);
      } else {
        group61AboveTotalUsers++;
        Float totalGroupStars = usersGroupRating.get(ABOVE_60);
        totalGroupStars += userStars;
        usersGroupRating.put(ABOVE_60, totalGroupStars);
      }
    }
    // perform relative age group analysis for age group 18 to 25.
    float group18TO25UsersRating = usersGroupRating.get(_18_TO_25);
    if (group18TO25TotalUsers > 0) {
      float group18TO25RelativeRating =
          group18TO25UsersRating / group18TO25TotalUsers;
      usersGroupRating.put(_18_TO_25, group18TO25RelativeRating);
    } else {
      usersGroupRating.put(_18_TO_25, -1f);
    }
    // perform relative age group analysis for age group 26 to 40.
    float group26TO40UsersRating = usersGroupRating.get(_26_TO_40);
    if (group26TO40TotalUsers > 0) {
      float group26TO40RelativeRating =
          group26TO40UsersRating / group26TO40TotalUsers;
      usersGroupRating.put(_26_TO_40, group26TO40RelativeRating);
    } else {
      usersGroupRating.put(_26_TO_40, -1f);
    }
    // perform relative age group analysis for age group 41 to 60.
    float group41TO60UsersRating = usersGroupRating.get(_41_TO_60);
    if (group41TO60TotalUsers > 0) {
      float group41TO60RelativeRating =
          group41TO60UsersRating / group41TO60TotalUsers;
      usersGroupRating.put(_41_TO_60, group41TO60RelativeRating);
    } else {
      usersGroupRating.put(_41_TO_60, -1f);
    }
    // perform relative age group analysis for age group above 60.
    float group61AboveUsersRating = usersGroupRating.get(ABOVE_60);
    if (group61AboveTotalUsers > 0) {
      float group61AboveRelativeRating =
          group61AboveUsersRating / group61AboveTotalUsers;
      usersGroupRating.put(ABOVE_60, group61AboveRelativeRating);
    } else {
      usersGroupRating.put(ABOVE_60, -1f);
    }
    //return the performed analysis by age group.
    return usersGroupRating;
  }

  /**
   * Gets the blood bank ratings analysis by age group.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return map of blood bank rating analysis by age group.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public Map<String, Float> getBloodBankRatings(final int bloodBankId)
      throws DatabaseConnectionException {
    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement bloodBankRatingStatement =
             dbConnection.createStatement();
         final ResultSet bloodBankRatingResultSet =
             bloodBankRatingStatement.executeQuery(
                 bloodBankRatingAnalysisAgeGroupQueryBuilderDAO
                     .getBloodBankRatingByBloodBankId(bloodBankId))) {
      final List<BloodBankRatingAnalysisAgeGroup>
          bloodBankRatingAnalysisAgeGroups =
          prepareBloodBankRating(bloodBankRatingResultSet);
      return analyzeBloodBankRating(bloodBankRatingAnalysisAgeGroups);
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}