package backend.blood_bank_rating_analysis.analysis_by_age_group.database;

import backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationQueryBuilder;

import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.STAR_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.AGE_DURING_RATING_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.BLOOD_BANK_RATING_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupConstant.BLOOD_BANK_ID_COLUMN;

/**
 * {@code BloodBankRatingAnalysisAgeGroupQueryBuilder} implements the
 * {@code BloodBankRatingAnalysisAgeGroupQueryBuilderDAO} to provide a concrete
 * implementation for handling queries related to blood bank rating by age
 * group.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisAgeGroupQueryBuilder
    implements BloodBankRatingAnalysisAgeGroupQueryBuilderDAO {

  // Static instance of this class.
  private static BloodBankRatingAnalysisAgeGroupQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankRatingAnalysisAgeGroupQueryBuilder}
   * instance.
   */
  private BloodBankRatingAnalysisAgeGroupQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankRatingAnalysisAgeGroupQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankRatingAnalysisAgeGroupQueryBuilder}
   * class.
   */
  public static BloodBankRatingAnalysisAgeGroupQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankRatingAnalysisAgeGroupQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the rating of blood bank by blood bank id.
   *
   * @param bloodBankId blood bank unique id.
   *
   * @return query to get blood bank ratings.
   */
  @Override
  public String getBloodBankRatingByBloodBankId(final int bloodBankId) {
    return "SELECT " +
        STAR_COLUMN + ", " +
        AGE_DURING_RATING_COLUMN +
        " FROM " +
        BLOOD_BANK_RATING_TABLE +
        " WHERE " +
        BLOOD_BANK_ID_COLUMN + " = " + bloodBankId + ";";
  }
}