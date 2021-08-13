package backend.admin_blood_donation_request.model;

/**
 * {@code BloodDonationRequestStatus} class stores information related to
 * blood donation request status of the application
 * this class acts as a single object/entry/model.
 */
public class BloodDonationRequestStatus {

  // Request id of blood donor
  private final int requestId;

  // User id of blood donor
  private final int userId;

  // Request date of blood donor
  private String requestDate;

  //  Status change date of blood donor
  private String statusChangeDate;

  // Current status of blood donor
  private String status;

  /**
   *
   * @param requestId         Request id of blood donor
   * @param userId            User id of blood donor
   * @param requestDate       Request date of blood donor
   * @param statusChangeDate  Status change date of blood donor
   * @param status            Current status of blood donor
   */
  public BloodDonationRequestStatus(final int requestId,
                                    final int userId,
                                    final String requestDate,
                                    final String statusChangeDate,
                                    final String status) {
      this.requestId = requestId;
      this.userId = userId;
      this.requestDate = requestDate;
      this.statusChangeDate = statusChangeDate;
      this.status = status;

  }

  /**
   *
   * @param requestDate       Request date of blood donor
   * @param statusChangeDate  Status change date of blood donor
   * @param status            Current status of blood donor
   * @param userId            User id of blood donor
   */
  public BloodDonationRequestStatus(String requestDate, String statusChangeDate, String status, int userId) {
    this(-1, userId, requestDate, statusChangeDate, status);

  }

  /**
   * Gets user id of blood donor
   *
   * @return user id of blood donor
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Gets request id of blood donor
   *
   * @return request id of blood donor
   */
  public int getRequestId() {
    return requestId;
  }

  /**
   * Gets request date of blood donor
   *
   * @return request date of blood donor
   */
  public String getRequestDate() {
    return requestDate;
  }

  /**
   * Gets status changed date of blood donor
   *
   * @return status changed date of blood donor
   */
  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  /**
   * Sets status change date of blood donor
   *
   * @param statusChangeDate status change date of blood donor
   */
  public void setStatusChangeDate(String statusChangeDate) {
    this.statusChangeDate = statusChangeDate;
  }

  /**
   * Gets status of blood donor
   *
   * @return status of blood donor
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets status of blood donor
   *
   * @param status status of blood donor
   */
  public void setStatus(String status) {
    this.status = status;
  }
}
