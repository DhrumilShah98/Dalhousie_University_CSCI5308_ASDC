package backend.blood_bank_rating_analysis.analysis_by_age_group.database;

/**
 * {@code BloodBankRatingAnalysisAgeGroupQueryBuilderDAO} provides a contract
 * to get ratings of blood bank.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingAnalysisAgeGroupQueryBuilderDAO {

  /**
   * Gets the rating of blood bank by blood bank id.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return query to get blood bank ratings.
   */
  String getBloodBankRatingByBloodBankId(int bloodBankId);
}
