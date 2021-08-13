
package backend.vaccination.model;

/**
 * {@code Vaccination} class stores the information related to vaccination process.
 * This class acts as a single object/entry/model.
 */
public final class VaccineModel {

  // unique id of this Vaccine table.
  private final int vaccineId;

  // user id for reference.
  private final int userId;

  // vaccine type.
  private final String vaccineType;

  // date of registration.
  private final String registrationDate;

  // date of first dose.
  private final String firstDoseDate;

  // date of second dose.
  private final String secondDoseDate;

  // checking that user is already registered or not.
  private final boolean isRegisteredFirstTime;

  /**
   * Constructs this {@code User} instance.
   *
   * @param vaccineId             unique id of this Vaccine table.
   * @param userId                user id for reference.
   * @param registrationDate      date of registration.
   * @param firstDoseDate         date of first dose.
   * @param secondDoseDate        date of second dose.
   * @param vaccineType           vaccine type.
   * @param isRegisteredFirstTime checking that user is already registered or not.
   */
  public VaccineModel(int vaccineId, int userId, String vaccineType, String registrationDate, String firstDoseDate, String secondDoseDate, boolean isRegisteredFirstTime) {
    this.vaccineId = vaccineId;
    this.userId = userId;
    this.vaccineType = vaccineType;
    this.registrationDate = registrationDate;
    this.firstDoseDate = firstDoseDate;
    this.secondDoseDate = secondDoseDate;
    this.isRegisteredFirstTime = isRegisteredFirstTime;
  }

  /**
   * Constructs this {@code User} instance.
   *
   * @param userId                user id for reference.
   * @param registrationDate      date of registration.
   * @param firstDoseDate         date of first dose.
   * @param secondDoseDate        date of second dose.
   * @param vaccineType           vaccine type.
   * @param isRegisteredFirstTime checking that user is already registered or not.
   */
  public VaccineModel(int userId, String vaccineType, String registrationDate, String firstDoseDate, String secondDoseDate, boolean isRegisteredFirstTime) {
    this(-1, userId, vaccineType, registrationDate, firstDoseDate, secondDoseDate, isRegisteredFirstTime);
  }

  /**
   * get the user id
   *
   * @return id of user
   */
  public int getUserId() {
    return userId;
  }

  /**
   * get the vaccine type for that particular user
   *
   * @return allocated vaccine for user
   */
  public String getVaccineType() {
    return vaccineType;
  }


  /**
   * get the registration date of vaccine for user
   *
   * @return registration date
   */
  public String getRegistrationDate() {
    return registrationDate;
  }

  /**
   * get the first dose date of vaccine for user
   *
   * @return allocated first dose date to user
   */
  public String getFirstDoseDate() {
    return firstDoseDate;
  }

  /**
   * get the second dose date of vaccine for user
   *
   * @return allocated second dose date to user
   */
  public String getSecondDoseDate() {
    return secondDoseDate;
  }

  /**
   * checking is user registered for vaccine or not
   *
   * @return {@code true} if user visit first time to schedule the vaccination
   * else {@code false}
   */
  public boolean isRegisteredFirstTime() {
    return isRegisteredFirstTime;
  }

  /**
   * toString method of model class
   *
   * @return  String object of vaccine ID, user ID, vaccine type,
   * registration date, fisrt dose date, second dose date.
   */
  @Override
  public String toString() {
    return "VaccineModel{" +
        "vaccineId=" + vaccineId +
        ", userId=" + userId +
        ", vaccineType='" + vaccineType + '\'' +
        ", registrationDate='" + registrationDate + '\'' +
        ", firstDoseDate='" + firstDoseDate + '\'' +
        ", secondDoseDate='" + secondDoseDate + '\'' +
        '}';
  }
}