package backend.blood_bank_rating_analysis.analysis_by_location.database;

/**
 * {@code BloodBankRatingAnalysisLocationQueryBuilderDAO} provides a contract
 * to get ratings of blood bank.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingAnalysisLocationQueryBuilderDAO {

  /**
   * Gets the rating of blood banks
   *
   * @return query to get blood bank ratings.
   */
  String getBloodBankRatingsQuery();
}
