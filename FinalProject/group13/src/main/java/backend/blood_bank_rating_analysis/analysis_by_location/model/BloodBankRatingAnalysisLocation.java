package backend.blood_bank_rating_analysis.analysis_by_location.model;

/**
 * {@code BloodBankRatingAnalysisLocation} class stores the information related to
 * to blood bank rating analysis by location. This class acts as a single
 * object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRatingAnalysisLocation {

  // Blood bank id.
  private final int bloodBankId;

  // Blood bank name.
  private final String bloodBankName;

  // Blood bank address province.
  private final String addressProvince;

  // Blood bank star.
  private float star;

  // Blood bank address province rank.
  private int addressProvinceRank;

  // Blood bank total rank.
  private int totalRank;

  /**
   * Constructs this {@code BloodBankRatingAnalysisLocation} instance.
   *
   * @param bloodBankId     blood bank unique id.
   * @param bloodBankName   blood bank name.
   * @param addressProvince blood bank province.
   * @param star            blood bank star.
   */
  public BloodBankRatingAnalysisLocation(final int bloodBankId,
                                         final String bloodBankName,
                                         final String addressProvince,
                                         final float star) {
    this.bloodBankId = bloodBankId;
    this.bloodBankName = bloodBankName;
    this.addressProvince = addressProvince;
    this.star = star;
  }

  /**
   * Gets this blood bank id.
   *
   * @return blood bank id.
   */
  public int getBloodBankId() {
    return this.bloodBankId;
  }

  /**
   * Gets this blood bank name.
   *
   * @return this blood bank name.
   */
  public String getBloodBankName() {
    return this.bloodBankName;
  }

  /**
   * Gets this blood bank address province.
   *
   * @return this blood bank address province.
   */
  public String getAddressProvince() {
    return this.addressProvince;
  }

  /**
   * Gets the blood bank star.
   *
   * @return blood bank star.
   */
  public float getStar() {
    return this.star;
  }

  /**
   * Sets the blood bank star.
   *
   * @param star blood bank star.
   */
  public void setStar(float star) {
    this.star = star;
  }

  /**
   * Gets this blood bank address province rank.
   *
   * @return this blood bank address province rank.
   */
  public int getAddressProvinceRank() {
    return this.addressProvinceRank;
  }

  /**
   * Sets this blood bank address province rank.
   *
   * @param addressProvinceRank this blood bank address province rank.
   */
  public void setAddressProvinceRank(final int addressProvinceRank) {
    this.addressProvinceRank = addressProvinceRank;
  }

  /**
   * Gets this blood bank total rank
   *
   * @return this blood bank total rank.
   */
  public int getTotalRank() {
    return this.totalRank;
  }

  /**
   * Sets this blood bank total rank
   *
   * @param totalRank this blood bank total rank.
   */
  public void setTotalRank(final int totalRank) {
    this.totalRank = totalRank;
  }
}