package backend.authentication.blood_bank.model;

/**
 * {@code BloodBank} class stores the information related to this blood bank.
 * This class acts as a single object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBank {

  // Unique id of this blood bank.
  private final int bloodBankId;

  // Name of this blood bank.
  private String name;

  // Email of this blood bank.
  private String email;

  // Password of this blood bank.
  private String password;

  // Contact number of this blood bank.
  private String contactNumber;

  // Address first line of this blood bank.
  private String addressFirstLine;

  // Address street of this blood bank.
  private String addressStreet;

  // Address city of this blood bank.
  private String addressCity;

  // Address province of this blood bank.
  private String addressProvince;

  // Address zip code of this blood bank.
  private String addressZipCode;

  // Address country of this blood bank.
  private String addressCountry;

  // Account active status of this blood bank.
  private boolean   /**
   * Constructs this {@code BloodBank} instance.
   *
   * @param bloodBankId      unique id of this blood bank.
   * @param name             name of this blood bank.
   * @param email            email of this blood bank.
   * @param password         password of this blood bank.
   * @param contactNumber    contact number of this blood bank.
   * @param addressFirstLine address first line of this blood bank.
   * @param addressStreet    address street of this blood bank.
   * @param addressCity      address city of this blood bank.
   * @param addressProvince  address province of this blood bank.
   * @param addressZipCode   address zip code of this blood bank.
   * @param addressCountry   address country of this blood bank.
   * @param isAccountActive  check if blood bank account is active or not.
   */
      isAccountActive;


  public BloodBank(final int bloodBankId,
                   final String name,
                   final String email,
                   final String password,
                   final String contactNumber,
                   final String addressFirstLine,
                   final String addressStreet,
                   final String addressCity,
                   final String addressProvince,
                   final String addressZipCode,
                   final String addressCountry,
                   final boolean isAccountActive) {
    this.bloodBankId = bloodBankId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.contactNumber = contactNumber;
    this.addressFirstLine = addressFirstLine;
    this.addressStreet = addressStreet;
    this.addressCity = addressCity;
    this.addressProvince = addressProvince;
    this.addressZipCode = addressZipCode;
    this.addressCountry = addressCountry;
    this.isAccountActive = isAccountActive;
  }

  /**
   * Constructs this {@code BloodBank} instance.
   *
   * @param name             name of this blood bank.
   * @param email            email of this blood bank.
   * @param password         password of this blood bank.
   * @param contactNumber    contact number of this blood bank.
   * @param addressFirstLine address first line of this blood bank.
   * @param addressStreet    address street of this blood bank.
   * @param addressCity      address city of this blood bank.
   * @param addressProvince  address province of this blood bank.
   * @param addressZipCode   address zip code of this blood bank.
   * @param addressCountry   address country of this blood bank.
   * @param isAccountActive  check if blood bank account is active or not.
   */
  public BloodBank(final String name,
                   final String email,
                   final String password,
                   final String contactNumber,
                   final String addressFirstLine,
                   final String addressStreet,
                   final String addressCity,
                   final String addressProvince,
                   final String addressZipCode,
                   final String addressCountry,
                   final boolean isAccountActive) {
    this(-1, name, email, password, contactNumber, addressFirstLine,
        addressStreet, addressCity, addressProvince, addressZipCode,
        addressCountry, isAccountActive);
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
   * Sets the name of this blood bank.
   *
   * @param name name of this blood bank.
   */
  public void setName(final String name) {
    this.name = name;
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
   * Sets the email of this blood bank.
   *
   * @param email of this blood bank.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Gets the password of this blood bank.
   *
   * @return password of this blood bank.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of this blood bank.
   *
   * @param password of this blood bank.
   */
  public void setPassword(final String password) {
    this.password = password;
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
   * Sets the contact number of this blood bank.
   *
   * @param contactNumber of this blood bank.
   */
  public void setContactNumber(final String contactNumber) {
    this.contactNumber = contactNumber;
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
   * Sets the address first line of this blood bank.
   *
   * @param addressFirstLine of this blood bank.
   */
  public void setAddressFirstLine(final String addressFirstLine) {
    this.addressFirstLine = addressFirstLine;
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
   * Sets the address street of this blood bank.
   *
   * @param addressStreet of this blood bank.
   */
  public void setAddressStreet(final String addressStreet) {
    this.addressStreet = addressStreet;
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
   * Sets the address city of this blood bank.
   *
   * @param addressCity of this blood bank.
   */
  public void setAddressCity(final String addressCity) {
    this.addressCity = addressCity;
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
   * Sets the address province of this blood bank.
   *
   * @param addressProvince of this blood bank.
   */
  public void setAddressProvince(final String addressProvince) {
    this.addressProvince = addressProvince;
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
   * Sets the address zip code of this blood bank.
   *
   * @param addressZipCode of this blood bank.
   */
  public void setAddressZipCode(final String addressZipCode) {
    this.addressZipCode = addressZipCode;
  }

  /**
   * Gets the address country of this blood bank.
   *
   * @return address country of this blood bank.
   */
  public String getAddressCountry() {
    return this.addressCountry;
  }

  /**
   * Sets the address country of this blood bank.
   *
   * @param addressCountry of this blood bank.
   */
  public void setAddressCountry(final String addressCountry) {
    this.addressCountry = addressCountry;
  }

  /**
   * Gets the account status of this blood bank.
   *
   * @return account status of this blood bank.
   */
  public boolean isAccountActive() {
    return this.isAccountActive;
  }


  /**
   * Sets the account status of this blood bank.
   *
   * @param accountActive of this blood bank.
   */
  public void setAccountActive(final boolean accountActive) {
    this.isAccountActive = accountActive;
  }
}