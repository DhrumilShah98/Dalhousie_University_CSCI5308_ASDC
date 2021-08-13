package backend.blood_bank_camp.model;

/**
 * {@code Camp} class stores the information related to this camp.
 * This class acts as a single object/entry/model.
 */
public final class Camp {

  // Unique id of this camp.
  private final int campId;

  // Name of this organizer name.
  private String organizerName;

  // Unique id of this blood bank.
  private int bloodBankId;

  // Date of camp.
  private String date;

  // Time of this camp.
  private String time;

  // Available capacity in this camp.
  private int availableCapacity;

  // Venue of this camp.
  private String venue;

  // City of this camp.
  private String city;

  // Contact number of this camp organizer.
  private String contactNumber;

  /**
   * Constructs this {@code Camp} instance.
   *
   * @param campId            Unique id of this camp.
   * @param organizerName     Name of this organizer name.
   * @param bloodBankId       Unique id of this blood bank.
   * @param date              Date of camp.
   * @param time              Time of this camp.
   * @param availableCapacity Available capacity in this camp.
   * @param venue             Venue of this camp.
   * @param city              City of this camp.
   * @param contactNumber     Contact number of this camp organizer.
   */
  public Camp(final int campId,
              final String organizerName,
              final int bloodBankId,
              final String date,
              final String time,
              final int availableCapacity,
              final String venue,
              final String city,
              final String contactNumber) {
    this.campId = campId;
    this.organizerName = organizerName;
    this.bloodBankId = bloodBankId;
    this.date = date;
    this.time = time;
    this.availableCapacity = availableCapacity;
    this.venue = venue;
    this.city = city;
    this.contactNumber = contactNumber;
  }

  /**
   * Constructs this {@code Camp} instance.
   *
   * @param organizerName     Name of this organizer name.
   * @param bloodBankId       Unique id of this blood bank.
   * @param date              Date of camp.
   * @param time              Time of this camp.
   * @param availableCapacity Available capacity in this camp.
   * @param venue             Venue of this camp.
   * @param city              City of this camp.
   * @param contactNumber     Contact number of this camp organizer.
   */
  public Camp(final String organizerName,
              final int bloodBankId,
              final String date,
              final String time,
              final int availableCapacity,
              final String venue,
              final String city,
              final String contactNumber) {
    this(-1, organizerName, bloodBankId, date,
        time, availableCapacity, venue, city, contactNumber);
  }

  /**
   * Gets the unique id of this camp.
   *
   * @return unique id of camp.
   */
  public int getCampId() {
    return campId;
  }

  /**
   * Gets the organizer name of this camp.
   *
   * @return camp organizer name.
   */
  public String getOrganizerName() {
    return organizerName;
  }

  /**
   * Sets the organizer name of this camp.
   *
   * @param organizerName of camp.
   */
  public void setOrganizerName(String organizerName) {
    this.organizerName = organizerName;
  }

  /**
   * Gets unique id of this blood bank.
   *
   * @return blood bank unique id
   */
  public int getBloodBankId() {
    return bloodBankId;
  }

  /**
   * Sets unique id of this blood bank.
   *
   * @param bloodBankId of camp.
   */
  public void setBloodBankId(int bloodBankId) {
    this.bloodBankId = bloodBankId;
  }

  /**
   * Gets date of this camp.
   *
   * @return date of this camp.
   */
  public String getDate() {
    return date;
  }

  /**
   * Sets date of this camp.
   *
   * @param date of this camp
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Gets time of this camp.
   *
   * @return time of this camp.
   */
  public String getTime() {
    return time;
  }

  /**
   * Sets time of this camp.
   *
   * @param time of this camp.
   */
  public void setTime(String time) {
    this.time = time;
  }

  /**
   * Gets available capacity of this camp.
   *
   * @return available capacity of this camp.
   */
  public int getAvailableCapacity() {
    return availableCapacity;
  }

  /**
   * Sets available capacity of this camp.
   *
   * @param availableCapacity of this camp.
   */
  public void setAvailableCapacity(int availableCapacity) {
    this.availableCapacity = availableCapacity;
  }

  /**
   * Gets venue of this camp.
   *
   * @return venue of this camp.
   */
  public String getVenue() {
    return venue;
  }

  /**
   * Sets venue of this camp.
   *
   * @param venue of this camp.
   */
  public void setVenue(String venue) {
    this.venue = venue;
  }

  /**
   * Gets city of this camp.
   *
   * @return city of this camp.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets city of this camp.
   *
   * @param city of this camp.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets contact number of this camp organizer.
   *
   * @return contact number of this camp organizer.
   */
  public String getContactNumber() {
    return contactNumber;
  }

  /**
   * Sets contact number of this camp organizer.
   *
   * @param contactNumber of this camp organizer.
   */
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }
}