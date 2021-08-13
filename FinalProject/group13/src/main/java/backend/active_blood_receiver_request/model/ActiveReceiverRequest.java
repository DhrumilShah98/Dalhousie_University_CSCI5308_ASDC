package backend.active_blood_receiver_request.model;

/**
 * {@code ActiveReceiverRequest} class stores the information related to
 * all active blood receiver requests.
 * This class acts as a single object/entry/model.
 */
public class ActiveReceiverRequest {

  // Unique id of the user
  private final int userId;

  // Unique id of blood receiver requests.
  private final int requestId;

  // First name of the user.
  private final String firstName;

  // Last name of the user.
  private final String lastName;

  // Date of birth of the user.
  private final String dateOfBirth;

  // Derived age of the user.
  private final int age;

  // Blood group of the user.
  private final String bloodGroup;

  // Email of the user.
  private final String email;

  // Contact number of the user.
  private final String contactNumber;

  // Address first line of the user.
  private final String addressFirstLine;

  // Address street of the user.
  private final String addressStreet;

  // Address city of the user.
  private final String addressCity;

  // Address province of the user.
  private final String addressProvince;

  // Address zip code of the user.
  private final String addressZipCode;

  // Address country of the user.
  private final String addressCountry;

  // Status of the blood receiver request.
  private final String status;

  /**
   * @param userId           unique id of the user.
   * @param requestId        unique id of blood receiver requests.
   * @param firstName        first name of the user.
   * @param lastName         last name of the user.
   * @param dateOfBirth      date of birth of the user.
   * @param age              derived age of the user.
   * @param bloodGroup       blood group of the user.
   * @param email            email of the user.
   * @param contactNumber    contact number of the user.
   * @param addressFirstLine address first line of the user.
   * @param addressStreet    address street of the user.
   * @param addressCity      address city of the user.
   * @param addressProvince  address province of the user.
   * @param addressZipCode   address zip code of the user.
   * @param addressCountry   address country of the user.
   * @param status           status of the blood receiver request.
   */
  public ActiveReceiverRequest(int userId,
                               int requestId,
                               String firstName,
                               String lastName,
                               String dateOfBirth,
                               int age,
                               String bloodGroup,
                               String email,
                               String contactNumber,
                               String addressFirstLine,
                               String addressStreet,
                               String addressCity,
                               String addressProvince,
                               String addressZipCode,
                               String addressCountry,
                               String status) {
    this.userId = userId;
    this.requestId = requestId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.age = age;
    this.bloodGroup = bloodGroup;
    this.email = email;
    this.contactNumber = contactNumber;
    this.addressFirstLine = addressFirstLine;
    this.addressStreet = addressStreet;
    this.addressCity = addressCity;
    this.addressProvince = addressProvince;
    this.addressZipCode = addressZipCode;
    this.addressCountry = addressCountry;
    this.status = status;
  }

  /**
   * Gets the user's unique id.
   *
   * @return user's unique id.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Gets the blood receiver request's unique id.
   *
   * @return blood receiver request unique id.
   */
  public int getRequestId() {
    return requestId;
  }

  /**
   * Gets the user's first name.
   *
   * @return user's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the user's last name.
   *
   * @return user's last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the user's date of birth.
   *
   * @return user's date of birth.
   */
  public String getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Gets the user's derived age.
   *
   * @return user's derived age.
   */
  public int getAge() {
    return age;
  }

  /**
   * Gets the user's blood group.
   *
   * @return user's blood group.
   */
  public String getBloodGroup() {
    return bloodGroup;
  }

  /**
   * Gets the user's email.
   *
   * @return user's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the user's contact number.
   *
   * @return user's contact number.
   */
  public String getContactNumber() {
    return contactNumber;
  }

  /**
   * Gets the user's address first line.
   *
   * @return user's address first line.
   */
  public String getAddressFirstLine() {
    return addressFirstLine;
  }

  /**
   * Gets the user's address street.
   *
   * @return user's address street.
   */
  public String getAddressStreet() {
    return addressStreet;
  }

  /**
   * Gets the user's address city.
   *
   * @return user's address city.
   */
  public String getAddressCity() {
    return addressCity;
  }

  /**
   * Gets the user's address province.
   *
   * @return user's address province.
   */
  public String getAddressProvince() {
    return addressProvince;
  }

  /**
   * Gets the user's address zip code.
   *
   * @return user's address zip code.
   */
  public String getAddressZipCode() {
    return addressZipCode;
  }

  /**
   * Gets the user's address country.
   *
   * @return user's address country.
   */
  public String getAddressCountry() {
    return addressCountry;
  }

  /**
   * Gets the blood receiver request's status.
   *
   * @return blood receiver request's status.
   */
  public String getStatus() {
    return status;
  }
}