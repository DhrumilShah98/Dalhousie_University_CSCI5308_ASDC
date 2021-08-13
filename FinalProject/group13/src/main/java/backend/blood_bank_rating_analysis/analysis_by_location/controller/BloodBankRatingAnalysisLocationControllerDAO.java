package backend.blood_bank_rating_analysis.analysis_by_location.controller;

import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import backend.blood_bank_rating_analysis.analysis_by_location.model.BloodBankRatingAnalysisLocation;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code BloodBankRatingAnalysisLocationControllerDAO} provide a
 * contract for rating analysis by location.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingAnalysisLocationControllerDAO {

  /**
   * Gets the blood bank ratings analysis by location.
   *
   * @return list of blood banks with province and country rank.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  List<BloodBankRatingAnalysisLocation> getBloodBankRatings()
      throws DatabaseConnectionException;
}