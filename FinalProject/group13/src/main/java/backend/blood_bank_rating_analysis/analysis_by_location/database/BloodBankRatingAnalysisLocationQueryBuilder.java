package backend.blood_bank_rating_analysis.analysis_by_location.database;

import backend.blood_bank_rating_analysis.analysis_by_age_group.database.BloodBankRatingAnalysisAgeGroupQueryBuilder;

import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_NAME_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.BLOOD_BANK_RATING_TABLE;
import static backend.blood_bank_rating_analysis.analysis_by_location.database.BloodBankRatingAnalysisLocationConstant.STAR_COLUMN;

/**
 * {@code BloodBankRatingAnalysisLocationQueryBuilder} implements the
 * {@code BloodBankRatingAnalysisLocationQueryBuilderDAO} to provide a concrete
 * implementation for handling queries related to blood bank rating by location.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisLocationQueryBuilder implements
    BloodBankRatingAnalysisLocationQueryBuilderDAO {

  // Static instance of this class.
  private static BloodBankRatingAnalysisLocationQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankRatingAnalysisLocationQueryBuilder}
   * instance.
   */
  private BloodBankRatingAnalysisLocationQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankRatingAnalysisLocationQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankRatingAnalysisLocationQueryBuilder}
   * class.
   */
  public static BloodBankRatingAnalysisLocationQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankRatingAnalysisLocationQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the rating of blood banks
   *
   * @return query to get blood bank ratings.
   */
  @Override
  public String getBloodBankRatingsQuery() {
    return "SELECT " +
        BLOOD_BANK_TABLE + "." + BLOOD_BANK_ID_COLUMN + ", " +
        BLOOD_BANK_TABLE + "." + BLOOD_BANK_NAME_COLUMN + ", " +
        BLOOD_BANK_TABLE + "." + BLOOD_BANK_ADDRESS_PROVINCE_COLUMN + ", " +
        BLOOD_BANK_RATING_TABLE + "." + STAR_COLUMN +
        " FROM " +
        BLOOD_BANK_TABLE + ", " + BLOOD_BANK_RATING_TABLE +
        " WHERE " +
        BLOOD_BANK_TABLE + "." + BLOOD_BANK_ID_COLUMN + " = " +
        BLOOD_BANK_RATING_TABLE + "." + BLOOD_BANK_ID_COLUMN + ";";
  }
}