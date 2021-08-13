package backend.blood_bank_rating_analysis.analysis_by_age_group.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * {@code BloodBankRatingAnalysisAgeGroup} class stores the information
 * related to this blood bank rating. This class acts as a single
 * object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisAgeGroup {

  // star value of rating.
  private final float star;

  // age value.
  private final int age;

  /**
   * Constructs this {@code BloodBankRatingAnalysisAgeGroup} instance.
   *
   * @param star star value of rating.
   * @param age  age value.
   */
  public BloodBankRatingAnalysisAgeGroup(final float star,
                                         final int age) {
    this.star = star;
    this.age = age;
  }

  /**
   * Gets the star value of rating.
   *
   * @return star value of rating.
   */
  public float getStar() {
    return star;
  }

  /**
   * Gets the age value.
   *
   * @return age value of rating.
   */
  public int getAge() {
    return age;
  }
}