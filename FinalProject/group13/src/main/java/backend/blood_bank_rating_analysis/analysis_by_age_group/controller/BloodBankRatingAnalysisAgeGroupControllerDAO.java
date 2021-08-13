package backend.blood_bank_rating_analysis.analysis_by_age_group.controller;

import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import database.DatabaseConnectionException;

import java.util.Map;

/**
 * {@code BloodBankRatingAnalysisAgeGroupControllerDAO} provide a
 * contract for rating analysis by age group.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingAnalysisAgeGroupControllerDAO {

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
  Map<String, Float> getBloodBankRatings(final int bloodBankId)
      throws DatabaseConnectionException;
}
