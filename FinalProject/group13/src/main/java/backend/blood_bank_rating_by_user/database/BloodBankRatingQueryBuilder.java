package backend.blood_bank_rating_by_user.database;

import backend.blood_bank_rating_by_user.model.BloodBankRating;

import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.BLOOD_BANK_RATING_TABLE;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.BLOOD_BANK_ID_COLUMN;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.USER_ID_COLUMN;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.COMMENT_COLUMN;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.STAR_COLUMN;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.AGE_DURING_RATING_COLUMN;
import static backend.blood_bank_rating_by_user.database.BloodBankRatingConstant.CREATE_AT_COLUMN;

/**
 * {@code BloodBankRatingQueryBuilder} implements the
 * {@code BloodBankRatingQueryBuilderDAO} to provide a concrete
 * implementation for handling queries related to blood bank rating.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingQueryBuilder
    implements BloodBankRatingQueryBuilderDAO {

  // Static instance of this class.
  private static BloodBankRatingQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankRatingQueryBuilder} instance.
   */
  private BloodBankRatingQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankRatingQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankRatingQueryBuilder} class.
   */
  public static BloodBankRatingQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankRatingQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert blood bank rating.
   *
   * @param bloodBankRating blood bank rating instance.
   *
   * @return query to insert blood bank rating.
   */
  @Override
  public String insertBloodBankRatingQuery(final BloodBankRating bloodBankRating) {
    return "INSERT INTO " + BLOOD_BANK_RATING_TABLE + " (" +
        BLOOD_BANK_ID_COLUMN + ", " +
        USER_ID_COLUMN + ", " +
        COMMENT_COLUMN + ", " +
        STAR_COLUMN + ", " +
        AGE_DURING_RATING_COLUMN + ", " +
        CREATE_AT_COLUMN + ")" +
        " VALUES (" +
        bloodBankRating.getBloodBankId() + ", " +
        bloodBankRating.getUserId() + ", " +
        "\"" + bloodBankRating.getComment() + "\", " +
        bloodBankRating.getStar() + ", " +
        bloodBankRating.getAgeDuringRating() + ", " +
        "\"" + bloodBankRating.getCreatedAt() + "\");";
  }
}