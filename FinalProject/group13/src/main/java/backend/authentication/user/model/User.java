package backend.authentication.user.model;

/**
 * {@code User} class stores the information related to this user.
 * This class acts as a single object/entry/model.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class User {

  // Unique id of this user.
  private final int userId;

  // First name of this user.
  private String firstName;

  // Last name of this user.
  private String lastName;

  // Gender of this user.
  private String gender;

  // Date of birth of this user.
  private String dateOfBirth;

  // Email of this user.
  private String email;

  // Password of this user.
  private String password;

  // Contact number of this user.
  private String contactNumber;

  // Blood group of this user.
  private String bloodGroup;

  // Address first line of this user.
  private String addressFirstLine;

  // Address street of this user.
  private String addressStreet;

  // Address city of this user.
  private String addressCity;

  // Address province of this user.
  private String addressProvince;

  // Address zip code of this user.
  private String addressZipCode;

  // Address country of this user.
  private String addressCountry;

  // Account active flag of this user.
  private final boolean isAccountActive;

  /**
   * Constructs this {@code User} instance.
   *
   * @param userId           unique id of this user.
   * @param firstName        first name of this user.
   * @param lastName         last name of this user.
   * @param gender           gender of this user.
   * @param dateOfBirth      date of birth of this user.
   * @param email            email of this user.
   * @param password         password of this user.
   * @param contactNumber    contact number of this user.
   * @param bloodGroup       blood group of this user.
   * @param addressFirstLine address first line of this user.
   * @param addressStreet    address street of this user.
   * @param addressCity      address city of this user.
   * @param addressProvince  address province of this user.
   * @param addressZipCode   address zip code of this user.
   * @param addressCountry   address country of this user.
   * @param isAccountActive  account active flag of this user.
   */
  public User(final int userId,
              final String firstName,
              final String lastName,
              final String gender,
              final String dateOfBirth,
              final String email,
              final String password,
              final String contactNumber,
              final String bloodGroup,
              final String addressFirstLine,
              final String addressStreet,
              final String addressCity,
              final String addressProvince,
              final String addressZipCode,
              final String addressCountry,
              final boolean isAccountActive) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.contactNumber = contactNumber;
    this.bloodGroup = bloodGroup;
    this.addressFirstLine = addressFirstLine;
    this.addressStreet = addressStreet;
    this.addressCity = addressCity;
    this.addressProvince = addressProvince;
    this.addressZipCode = addressZipCode;
    this.addressCountry = addressCountry;
    this.isAccountActive = isAccountActive;
  }

  /**
   * @param firstName        first name of this user.
   * @param lastName         last name of this user.
   * @param gender           gender of this user.
   * @param dateOfBirth      date of birth of this user.
   * @param email            email of this user.
   * @param password         password of this user.
   * @param contactNumber    contact number of this user.
   * @param bloodGroup       blood group of this user.
   * @param addressFirstLine address first line of this user.
   * @param addressStreet    address street of this user.
   * @param addressCity      address city of this user.
   * @param addressProvince  address province of this user.
   * @param addressZipCode   address zip code of this user.
   * @param addressCountry   address country of this user.
   * @param isAccountActive  account active flag of this user.
   */
  public User(final String firstName,
              final String lastName,
              final String gender,
              final String dateOfBirth,
              final String email,
              final String password,
              final String contactNumber,
              final String bloodGroup,
              final String addressFirstLine,
              final String addressStreet,
              final String addressCity,
              final String addressProvince,
              final String addressZipCode,
              final String addressCountry,
              final boolean isAccountActive) {
    this(-1, firstName, lastName, gender, dateOfBirth, email,
        password, contactNumber, bloodGroup, addressFirstLine,
        addressStreet, addressCity, addressProvince, addressZipCode,
        addressCountry, isAccountActive);
  }

  /**
   * Gets the unique id of this user
   *
   * @return user unique id.
   */
  public int getUserId() {
    return this.userId;
  }

  /**
   * Gets the first name of this user
   *
   * @return user first name.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Sets the first name of this user.
   *
   * @param firstName name of this user.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of this user
   *
   * @return user last name.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Sets the last name of this user.
   *
   * @param lastName name of this user.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the gender of this user
   *
   * @return user gender.
   */
  public String getGender() {
    return this.gender;
  }

  /**
   * Sets the gender of this user.
   *
   * @param gender name of this user.
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * Gets the dateOfBirth of this user
   *
   * @return user dateOfBirth.
   */
  public String getDateOfBirth() {
    return this.dateOfBirth;
  }

  /**
   * Sets the dateOfBirth of this user.
   *
   * @param dateOfBirth name of this user.
   */
  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * Gets the email of this user
   *
   * @return user email.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Sets the email of this user.
   *
   * @param email name of this user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of this user
   *
   * @return user password.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of this user.
   *
   * @param password name of this user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the contactNumber of this user
   *
   * @return user contactNumber.
   */
  public String getContactNumber() {
    return this.contactNumber;
  }

  /**
   * Sets the contactNumber of this user.
   *
   * @param contactNumber name of this user.
   */
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  /**
   * Gets the blood group of this user
   *
   * @return user blood group.
   */
  public String getBloodGroup() {
    return this.bloodGroup;
  }

  /**
   * Sets the bloodGroup of this user.
   *
   * @param bloodGroup name of this user.
   */
  public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  /**
   * Gets the address first line of this user
   *
   * @return user address first line.
   */
  public String getAddressFirstLine() {
    return this.addressFirstLine;
  }

  /**
   * Sets the addressFirstLine of this user.
   *
   * @param addressFirstLine name of this user.
   */
  public void setAddressFirstLine(String addressFirstLine) {
    this.addressFirstLine = addressFirstLine;
  }

  /**
   * Gets the address street of this user
   *
   * @return user address street.
   */
  public String getAddressStreet() {
    return this.addressStreet;
  }

  /**
   * Sets the addressStreet of this user.
   *
   * @param addressStreet name of this user.
   */
  public void setAddressStreet(String addressStreet) {
    this.addressStreet = addressStreet;
  }

  /**
   * Gets the address city of this user
   *
   * @return user address city.
   */
  public String getAddressCity() {
    return this.addressCity;
  }

  /**
   * Sets the addressCity of this user.
   *
   * @param addressCity name of this user.
   */
  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  /**
   * Gets the address province of this user
   *
   * @return user address province.
   */
  public String getAddressProvince() {
    return this.addressProvince;
  }

  /**
   * Sets the addressProvince of this user.
   *
   * @param addressProvince name of this user.
   */
  public void setAddressProvince(String addressProvince) {
    this.addressProvince = addressProvince;
  }

  /**
   * Gets the address zip code of this user
   *
   * @return user address zip code.
   */
  public String getAddressZipCode() {
    return this.addressZipCode;
  }

  /**
   * Sets the addressZipCode of this user.
   *
   * @param addressZipCode name of this user.
   */
  public void setAddressZipCode(String addressZipCode) {
    this.addressZipCode = addressZipCode;
  }

  /**
   * Gets the address country of this user
   *
   * @return user address country.
   */
  public String getAddressCountry() {
    return this.addressCountry;
  }

  /**
   * Sets the addressCountry of this user.
   *
   * @param addressCountry name of this user.
   */
  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  /**
   * Gets the account active flag of this user
   *
   * @return user account active flag.
   */
  public boolean isAccountActive() {
    return this.isAccountActive;
  }
}