package backend.blood_bank_rating_by_user.controller;

import backend.blood_bank_rating_by_user.database.BloodBankRatingQueryBuilderDAO;
import backend.blood_bank_rating_by_user.exception.BloodBankRatingException;
import backend.blood_bank_rating_by_user.model.BloodBankRating;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * {@code BloodBankRatingController} implements the
 * {@code BloodBankRatingControllerDAO} to provide a concrete
 * implementation for rating this blood bank.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingController
    implements BloodBankRatingControllerDAO {

  // Database connection instance.
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood bank rating query builder instance.
  private final BloodBankRatingQueryBuilderDAO
      bloodBankRatingQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankRatingController} instance.
   *
   * @param databaseConnectionDAO          database connection instance.
   * @param bloodBankRatingQueryBuilderDAO blood bank rating query builder
   *                                       instance.
   */
  public BloodBankRatingController(final DatabaseConnectionDAO databaseConnectionDAO,
                                   final BloodBankRatingQueryBuilderDAO bloodBankRatingQueryBuilderDAO) {
    this.databaseConnectionDAO =
        databaseConnectionDAO;
    this.bloodBankRatingQueryBuilderDAO =
        bloodBankRatingQueryBuilderDAO;
  }

  /**
   * Validates the comment given by the user.
   *
   * @param comment comment given by the user.
   *
   * @return {@code true} if comment is valid otherwise {@code false}.
   */
  private boolean validateComment(final String comment) {
    final boolean isCommentEmpty = (comment != null && !comment.isEmpty());
    final boolean isCommentValid = Pattern.matches("[A-Za-z\\d ]+", comment);
    return isCommentEmpty && isCommentValid;
  }

  /**
   * Validates the stars given by the user.
   *
   * @param star star given by the user.
   *
   * @return {@code true} if start is valid otherwise {@code false}.
   */
  private boolean validateStar(final float star) {
    final boolean isStarValid = (star >= 0.0f && star <= 5.0f);
    final boolean isStarInRange = (star == 0.0f || star == 0.5f ||
        star == 1.0f || star == 1.5f ||
        star == 2.0f || star == 2.5f ||
        star == 3.0f || star == 3.5f ||
        star == 4.0f || star == 4.5f ||
        star == 5.0f);
    return isStarValid && isStarInRange;
  }

  /**
   * Calculates the age of user from date of birth.
   *
   * @param dateOfBirth user date of birth.
   *
   * @return age of the user.
   */
  private int calculateUserAgeDuringRating(final String dateOfBirth) {
    final LocalDate birthDate =
        LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    final LocalDate currentDate = LocalDate.now();
    return Period.between(birthDate, currentDate).getYears();
  }

  /**
   * Gets the rating created date.
   *
   * @return rating created date.
   */
  private String getRatingCreatedDate() {
    final Date currentDate = new Date();
    final SimpleDateFormat currentDateFormatter =
        new SimpleDateFormat("yyyy-MM-dd");
    return currentDateFormatter.format(currentDate);
  }

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
  @Override
  public boolean insertBloodBankRating(final int bloodBankId,
                                       final int userId,
                                       final String dateOfBirth,
                                       final String comment,
                                       final float star)
      throws BloodBankRatingException, DatabaseConnectionException {
    final boolean isValidComment = validateComment(comment);
    if (!isValidComment) {
      throw new BloodBankRatingException("Enter valid comment.");
    }

    final boolean isValidStar = validateStar(star);
    if (!isValidStar) {
      throw new BloodBankRatingException("Enter valid stars.");
    }

    final int userAgeDuringRating = calculateUserAgeDuringRating(dateOfBirth);
    final String ratingCreatedDate = getRatingCreatedDate();

    final BloodBankRating bloodBankRating = new BloodBankRating(
        bloodBankId,
        userId,
        comment,
        star,
        userAgeDuringRating,
        ratingCreatedDate);

    try (final Connection dbConnection =
             databaseConnectionDAO.getDatabaseConnection();
         final Statement ratingStatement =
             dbConnection.createStatement()) {
      final String insertBloodBankRatingQuery =
          bloodBankRatingQueryBuilderDAO.insertBloodBankRatingQuery(bloodBankRating);
      ratingStatement.executeUpdate(insertBloodBankRatingQuery);
      return true;
    } catch (final SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}