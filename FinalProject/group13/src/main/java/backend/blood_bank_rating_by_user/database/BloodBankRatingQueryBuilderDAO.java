package backend.blood_bank_rating_by_user.database;

import backend.blood_bank_rating_by_user.model.BloodBankRating;

/**
 * {@code BloodBankRatingQueryBuilderDAO} provides a contract for handling
 * queries related to blood bank rating.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingQueryBuilderDAO {

  /**
   * Gets the query to insert blood bank rating.
   *
   * @param bloodBankRating blood bank rating instance.
   *
   * @return query to insert blood bank rating.
   */
  String insertBloodBankRatingQuery(final BloodBankRating bloodBankRating);
}