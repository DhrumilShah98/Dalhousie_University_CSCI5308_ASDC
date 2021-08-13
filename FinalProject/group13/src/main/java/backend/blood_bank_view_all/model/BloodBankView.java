package backend.blood_bank_view_all.model;

/**
 * {@code BloodBankView} class stores the information related to this blood
 * bank. This class acts as a single object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankView {

  // Unique id of this blood bank.
  private final int bloodBankId;

  // Name of this blood bank.
  private final String name;

  // Email of this blood bank.
  private final String email;

  // Contact number of this blood bank.
  private final String contactNumber;

  // Address first line of this blood bank.
  private final String addressFirstLine;

  // Address street of this blood bank.
  private final String addressStreet;

  // Address city of this blood bank.
  private final String addressCity;

  // Address province of this blood bank.
  private final String addressProvince;

  // Address zip code of this blood bank.
  private final String addressZipCode;

  // Address country of this blood bank.
  private final String addressCountry;

  /**
   * Constructs this {@code BloodBankView} instance.
   *
   * @param bloodBankId      unique id of this blood bank.
   * @param name             name of this blood bank.
   * @param email            email of this blood bank.
   * @param contactNumber    contact number of this blood bank.
   * @param addressFirstLine address first line of this blood bank.
   * @param addressStreet    address street of this blood bank.
   * @param addressCity      address city of this blood bank.
   * @param addressProvince  address province of this blood bank.
   * @param addressZipCode   address zip code of this blood bank.
   * @param addressCountry   address country of this blood bank.
   */
  public BloodBankView(final int bloodBankId,
                       final String name,
                       final String email,
                       final String contactNumber,
                       final String addressFirstLine,
                       final String addressStreet,
                       final String addressCity,
                       final String addressProvince,
                       final String addressZipCode,
                       final String addressCountry) {
    this.bloodBankId = bloodBankId;
    this.name = name;
    this.email = email;
    this.contactNumber = contactNumber;
    this.addressFirstLine = addressFirstLine;
    this.addressStreet = addressStreet;
    this.addressCity = addressCity;
    this.addressProvince = addressProvince;
    this.addressZipCode = addressZipCode;
    this.addressCountry = addressCountry;
  }

  /**
   * Gets the unique id of this blood bank.
   *
   * @return blood bank unique id.
   */
  public int getBloodBankId() {
    return this.bloodBankId;
  }

  /**
   * Gets the name of this blood bank.
   *
   * @return name of this blood bank.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the email of this blood bank.
   *
   * @return email of this blood bank.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets the contact number of this blood bank.
   *
   * @return contact number of this blood bank.
   */
  public String getContactNumber() {
    return this.contactNumber;
  }

  /**
   * Gets the address first line of this blood bank.
   *
   * @return address first line of this blood bank.
   */
  public String getAddressFirstLine() {
    return this.addressFirstLine;
  }

  /**
   * Gets the address street of this blood bank.
   *
   * @return address street of this blood bank.
   */
  public String getAddressStreet() {
    return this.addressStreet;
  }

  /**
   * Gets the address city of this blood bank.
   *
   * @return address city of this blood bank.
   */
  public String getAddressCity() {
    return this.addressCity;
  }

  /**
   * Gets the address province of this blood bank.
   *
   * @return address province of this blood bank.
   */
  public String getAddressProvince() {
    return this.addressProvince;
  }

  /**
   * Gets the address zip code of this blood bank.
   *
   * @return address zip code of this blood bank.
   */
  public String getAddressZipCode() {
    return this.addressZipCode;
  }

  /**
   * Gets the address country of this blood bank.
   *
   * @return address country of this blood bank.
   */
  public String getAddressCountry() {
    return this.addressCountry;
  }
}