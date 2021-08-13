package backend.admin_blood_receiver_request.model;

/**
 * {@code BloodReceiverRequestStatus} class stores information related to
 * blood receiver request status of the application
 * this class acts as a single object/entry/model.
 */
public class BloodReceiverRequestStatus {

  // Request id of blood receiver.
  private final int requestId;

  // Request date of blood receiver
  private String requestDate;

  // Status change date of blood receiver
  private String statusChangeDate;

  // Status of blood receiver
  private String status;

  /**
   *
   * @param requestId         Request id of blood receiver.
   * @param requestDate       Request date of blood receiver
   * @param statusChangeDate  Status change date of blood receiver
   * @param status            Status of blood receiver
   */
  public BloodReceiverRequestStatus(final int requestId,
                                    final String requestDate,
                                    final String statusChangeDate,
                                    final String status) {
      this.requestId = requestId;
      this.requestDate = requestDate;
      this.statusChangeDate = statusChangeDate;
      this.status = status;
  }

  /**
   *
   * @param requestId         Request id of blood receiver.
   * @param userId            User id of blood receiver.
   * @param requestDate       Request date of blood receiver.
   * @param statusChangeDate  Status change date of blood receiver.
   * @param status            Status of blood receiver.
   */
  public BloodReceiverRequestStatus(int requestId, int userId, String requestDate, String statusChangeDate, String status) {
    this(-1, requestDate, statusChangeDate, status);

  }

  /**
   * Gets request id of blood receiver.
   *
   * @return request id of blood receiver.
   */
  public int getRequestId() {
    return requestId;
  }

  /**
   * Gets request date of blood receiver
   *
   * @return request date of blood receiver
   */
  public String getRequestDate() {
    return requestDate;
  }

  /**
   * Sets request date of blood receiver
   *
   * @param requestDate request date of blood receiver
   */
  public void setRequestDate(String requestDate) {
    this.requestDate = requestDate;
  }

  /**
   * Gets status changed date of blood receiver
   *
   * @return status changed date of blood receiver
   */
  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  /**
   * Sets status change date of blood receiver
   *
   * @param statusChangeDate status change date of blood receiver
   */
  public void setStatusChangeDate(String statusChangeDate) {
    this.statusChangeDate = statusChangeDate;
  }

  /**
   * Gets status of blood receiver
   *
   * @return status of blood receiver
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets status of blood receiver
   *
   * @param status status of blood receiver
   */
  public void setStatus(String status) {
    this.status = status;
  }
}


