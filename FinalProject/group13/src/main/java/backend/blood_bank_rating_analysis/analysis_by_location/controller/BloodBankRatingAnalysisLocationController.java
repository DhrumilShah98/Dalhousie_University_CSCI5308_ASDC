package backend.blood_bank_rating_analysis.analysis_by_location.controller;

import backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationQueryBuilderDAO;
import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import backend.blood_bank_rating_analysis.analysis_by_location.model.BloodBankRatingAnalysisLocation;
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

import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_NAME_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_RATING_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.STAR_COLUMN;

/**
 * {@code BloodBankRatingAnalysisLocationController} implements the
 * {@code BloodBankRatingAnalysisLocationControllerDAO} to provide a concrete
 * implementation for rating analysis by location.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisLocationController
    implements BloodBankRatingAnalysisLocationControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank rating analysis by location query builder instance.
  private final BloodBankRatingAnalysisLocationQueryBuilderDAO
      bloodBankRatingAnalysisLocationQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankRatingAnalysisAgeGroupController} instance.
   *
   * @param databaseConnectionDAO                          database connection
   *                                                       instance.
   * @param bloodBankRatingAnalysisLocationQueryBuilderDAO blood bank
   *                                                       rating analysis by
   *                                                       location
   *                                                       query builder
   *                                                       instance.
   */
  public BloodBankRatingAnalysisLocationController(final DatabaseConnectionDAO databaseConnectionDAO,
                                                   final BloodBankRatingAnalysisLocationQueryBuilderDAO bloodBankRatingAnalysisLocationQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankRatingAnalysisLocationQueryBuilderDAO =
        bloodBankRatingAnalysisLocationQueryBuilderDAO;
  }

  /**
   * Prepares a list of blood bank rating analysis by age group instances.
   *
   * @param ratingByLocationResultSet blood bank rating result set.
   *
   * @return a list of blood bank rating analysis by age group instances.
   *
   * @throws SQLException if any error occurs while accessing the result set.
   */
  private List<BloodBankRatingAnalysisLocation> prepareBloodBankRating(final ResultSet ratingByLocationResultSet)
      throws SQLException {
    final List<BloodBankRatingAnalysisLocation> bloodBankRatingList = new ArrayList<>();
    while (ratingByLocationResultSet.next()) {
      final int bloodBankId =
          ratingByLocationResultSet.getInt(BLOOD_BANK_ID_COLUMN);
      final String bloodBankName =
          ratingByLocationResultSet.getString(BLOOD_BANK_NAME_COLUMN);
      final String addressProvince =
          ratingByLocationResultSet.getString(BLOOD_BANK_ADDRESS_PROVINCE_COLUMN);
      final float star =
          ratingByLocationResultSet.getFloat(STAR_COLUMN);
      final BloodBankRatingAnalysisLocation bloodStock =
          new BloodBankRatingAnalysisLocation(
              bloodBankId, bloodBankName, addressProvince, star);
      bloodBankRatingList.add(bloodStock);
    }
    return bloodBankRatingList;
  }

  /**
   * Analyze blood bank rating by location.
   * Perform analysis on basis of province rank and country rank.
   *
   * @param bloodBankRatingList a list of blood bank ratings.
   *
   * @return list of blood banks with province and country rank.
   */
  private List<BloodBankRatingAnalysisLocation> analyzeBloodBankRating(final List<BloodBankRatingAnalysisLocation> bloodBankRatingList) {
    //group stars
    final Map<Integer, BloodBankRatingAnalysisLocation> bloodBankByIdMap =
        new HashMap<>();
    for (final BloodBankRatingAnalysisLocation bloodBankRatingAnalysisLocation :
        bloodBankRatingList) {
      final int currentBloodBankId = bloodBankRatingAnalysisLocation.getBloodBankId();
      if (bloodBankByIdMap.containsKey(currentBloodBankId)) {
        final BloodBankRatingAnalysisLocation bloodBankRatingAnalysisLocationMapEntry =
            bloodBankByIdMap.get(currentBloodBankId);
        final float bloodBankMapEntryStar =
            bloodBankRatingAnalysisLocationMapEntry.getStar();
        final float currentBloodBankStar =
            bloodBankRatingAnalysisLocation.getStar();
        final float bloodBankTotalStar =
            bloodBankMapEntryStar + currentBloodBankStar;
        bloodBankRatingAnalysisLocationMapEntry.setStar(bloodBankTotalStar);
        bloodBankByIdMap.put(currentBloodBankId, bloodBankRatingAnalysisLocationMapEntry);
      } else {
        bloodBankByIdMap.put(currentBloodBankId, bloodBankRatingAnalysisLocation);
      }
    }
    //group provinces
    final Map<String, Integer> provinceLastRankEncounteredMap = new HashMap<>();
    for (final BloodBankRatingAnalysisLocation bloodBankRatingAnalysisLocation :
        bloodBankRatingList) {
      final String addressProvince =
          bloodBankRatingAnalysisLocation.getAddressProvince();
      if (!provinceLastRankEncounteredMap.containsKey(addressProvince)) {
        provinceLastRankEncounteredMap.put(addressProvince, 0);
      }
    }
    //calculate relative starts
    final List<BloodBankRatingAnalysisLocation> bloodBankRatingListGrouped =
        new ArrayList<>();
    final int totalRatings =
        bloodBankRatingList.size();
    for (final Map.Entry<Integer, BloodBankRatingAnalysisLocation> bloodBankEntry :
        bloodBankByIdMap.entrySet()) {
      final BloodBankRatingAnalysisLocation currentBloodBankRating =
          bloodBankEntry.getValue();
      final float currentBloodBankStar = currentBloodBankRating.getStar();
      final float relativeBloodBankStar = currentBloodBankStar / totalRatings;
      currentBloodBankRating.setStar(relativeBloodBankStar);
      bloodBankEntry.setValue(currentBloodBankRating);
      bloodBankRatingListGrouped.add(currentBloodBankRating);
    }
    //sort starts by rank (Highest rank first)
    final int bloodBankRatingListGroupedSize = bloodBankRatingListGrouped.size();
    for (int i = 0; i < bloodBankRatingListGroupedSize - 1; ++i) {
      for (int j = 0; j < bloodBankRatingListGroupedSize - i - 1; ++j) {
        final BloodBankRatingAnalysisLocation rating1 = bloodBankRatingListGrouped.get(j);
        final BloodBankRatingAnalysisLocation rating2 = bloodBankRatingListGrouped.get(j + 1);
        if (rating1.getStar() < rating2.getStar()) {
          bloodBankRatingListGrouped.set(j, rating2);
          bloodBankRatingListGrouped.set(j + 1, rating1);
        }
      }
    }
    //apply rank
    for (int i = 0; i < bloodBankRatingListGrouped.size(); i++) {
      final BloodBankRatingAnalysisLocation bloodBankRating =
          bloodBankRatingListGrouped.get(i);
      final int totalRank = i + 1;
      bloodBankRating.setTotalRank(totalRank);
      final int lastEncounteredRankInProvince =
          provinceLastRankEncounteredMap.get(bloodBankRating.getAddressProvince());
      final int bloodBankProvinceRank = lastEncounteredRankInProvince + 1;
      bloodBankRating.setAddressProvinceRank(bloodBankProvinceRank);
      provinceLastRankEncounteredMap.put(bloodBankRating.getAddressProvince(), bloodBankProvinceRank);
    }
    return bloodBankRatingListGrouped;
  }

  /**
   * Gets the blood bank ratings analysis by location.
   *
   * @return list of blood banks with province and country rank.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public List<BloodBankRatingAnalysisLocation> getBloodBankRatings()
      throws DatabaseConnectionException {
    try (final Connection dbConnection = databaseConnectionDAO.getDatabaseConnection();
         final Statement ratingByLocationStatement = dbConnection.createStatement();
         final ResultSet ratingByLocationResultSet =
             ratingByLocationStatement.executeQuery(
                 bloodBankRatingAnalysisLocationQueryBuilderDAO
                     .getBloodBankRatingsQuery())) {
      final List<BloodBankRatingAnalysisLocation> bloodBankRatingAnalysisLocations =
          prepareBloodBankRating(ratingByLocationResultSet);
      return analyzeBloodBankRating(bloodBankRatingAnalysisLocations);
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}