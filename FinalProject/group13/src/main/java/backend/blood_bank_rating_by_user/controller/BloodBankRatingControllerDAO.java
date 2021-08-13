package backend.blood_bank_rating_by_user.controller;

import backend.blood_bank_rating_by_user.exception.BloodBankRatingException;
import database.DatabaseConnectionException;

/**
 * {@code BloodBankRatingControllerDAO} provides a contract for rating the
 * blood bank.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRatingControllerDAO {

  /**
   * Inserts the blood bank rating given by the user.
   *
   * @param bloodBankId unique id of this blood bank.
   * @param userId      unique id of the user.
   * @param dateOfBirth user date of birth.
   * @param comment     comment given by user.
   * @param star        star given by user.
   *
   * @return {@code true} if rating is added otherwise {@code false}.
   *
   * @throws BloodBankRatingException    if any error occurs while rating this
   *                                     blood bank.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  boolean insertBloodBankRating(final int bloodBankId,
                                final int userId,
                                final String dateOfBirth,
                                final String comment,
                                final float star)
      throws BloodBankRatingException, DatabaseConnectionException;
}