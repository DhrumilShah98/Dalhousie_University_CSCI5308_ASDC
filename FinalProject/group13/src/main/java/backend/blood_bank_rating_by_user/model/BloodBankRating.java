package backend.blood_bank_rating_by_user.model;

/**
 * {@code BloodBankRating} class stores the information related to this blood
 * bank rating. This class acts as a single object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRating {

  // Unique id of this blood bank.
  private final int bloodBankId;

  // Unique id of the user rating this blood bank.
  private final int userId;

  // comment given to this blood bank.
  private final String comment;

  // star given to this blood bank.
  private final float star;

  // age of user during rating.
  private final int ageDuringRating;

  // date of comment creation.
  private final String createdAt;

  /**
   * Constructs this {@code BloodBankRating} instance.
   *
   * @param bloodBankId     unique id of this blood bank.
   * @param userId          unique id of the user rating this blood bank.
   * @param comment         comment given to this blood bank.
   * @param star            star given to this blood bank.
   * @param ageDuringRating age of user during rating.
   * @param createdAt       date of comment creation.
   */
  public BloodBankRating(final int bloodBankId,
                         final int userId,
                         final String comment,
                         final float star,
                         final int ageDuringRating,
                         final String createdAt) {
    this.bloodBankId = bloodBankId;
    this.userId = userId;
    this.comment = comment;
    this.star = star;
    this.ageDuringRating = ageDuringRating;
    this.createdAt = createdAt;
  }

  /**
   * Gets the unique id of this blood bank.
   *
   * @return unique id of this blood bank.
   */
  public int getBloodBankId() {
    return this.bloodBankId;
  }

  /**
   * Gets the unique id of the user rating this blood bank.
   *
   * @return unique id of the user rating this blood bank.
   */
  public int getUserId() {
    return this.userId;
  }

  /**
   * Gets the comment given to this blood bank.
   *
   * @return comment given to this blood bank.
   */
  public String getComment() {
    return this.comment;
  }

  /**
   * Gets the star given to this blood bank.
   *
   * @return star given to this blood bank.
   */
  public float getStar() {
    return this.star;
  }

  /**
   * Gets the age of user during rating.
   *
   * @return age of user during rating.
   */
  public int getAgeDuringRating() {
    return this.ageDuringRating;
  }

  /**
   * Gets the date of comment creation.
   *
   * @return date of comment creation.
   */
  public String getCreatedAt() {
    return this.createdAt;
  }
}