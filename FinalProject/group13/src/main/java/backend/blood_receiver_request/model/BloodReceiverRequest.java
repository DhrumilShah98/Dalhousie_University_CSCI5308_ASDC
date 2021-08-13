package backend.blood_receiver_request.model;

import java.time.LocalDate;

/**
 * {@code BloodReceiverRequest} class stores the information related to this
 * blood receiver request.
 * This class acts as a single object/entry/model.
 */
public final class BloodReceiverRequest {

  // Unique id of this blood receiver request.
  private final int requestId;

  // Unique id of this blood receiving request user.
  private int userId;

  // Blood group requires in this blood receiver request.
  private String bloodGroup;

  // Quantity of this blood receiver request.
  private String quantity;

  // date of this blood receiver request.
  private String dateOfRequest;

  // status of this blood receiver request.
  private String status;

  // date of status change of this blood receiver request.
  private String statusChangeDate;

  /**
   * @param requestId        Unique id of this blood receiver request.
   * @param userId           Unique id of this blood receiving request user.
   * @param bloodGroup       Blood group requires in this blood receiver request.
   * @param quantity         Quantity of this blood receiver request.
   * @param dateOfRequest    date of this blood receiver request.
   * @param status           status of this blood receiver request.
   * @param statusChangeDate date of status change of this blood receiver request.
   */
  public BloodReceiverRequest(final int requestId,
                              final int userId,
                              final String bloodGroup,
                              final String quantity,
                              final String dateOfRequest,
                              final String status,
                              final String statusChangeDate) {
    this.requestId = requestId;
    this.userId = userId;
    this.bloodGroup = bloodGroup;
    this.quantity = String.valueOf(quantity);
    this.status = status;
    this.statusChangeDate = statusChangeDate;
    this.dateOfRequest = dateOfRequest;
  }

  /**
   * @param userId           Unique id of this blood receiving request user.
   * @param bloodGroup       Blood group requires in this blood receiver request.
   * @param quantity         Quantity of this blood receiver request.
   * @param dateOfRequest    date of this blood receiver request.
   * @param status           status of this blood receiver request.
   * @param statusChangeDate date of status change of this blood receiver request.
   */
  public BloodReceiverRequest(final int userId,
                              final String bloodGroup,
                              final String quantity,
                              final String dateOfRequest,
                              final String status,
                              final String statusChangeDate) {
    this(-1, userId, bloodGroup, quantity, dateOfRequest, status, statusChangeDate);
  }

  /**
   * Gets the unique id of this blood receiver request.
   *
   * @return requestId of this blood receiver request.
   */
  public int getRequestId() {
    return requestId;
  }

  /**
   * Gets the Unique id of this blood receiving request user.
   *
   * @return Unique id of this blood receiving request user.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Sets the Unique id of this blood receiving request user.
   *
   * @param userId of this blood receiver request.
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Gets the Blood group requires in this blood receiver request.
   *
   * @return bloodGroup requires in this blood receiver request.
   */
  public String getBloodGroup() {
    return bloodGroup;
  }

  /**
   * Sets the Blood group requires in this blood receiver request.
   *
   * @param bloodGroup requires in this blood receiver request.
   */
  public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
  }

  /**
   * Gets the Quantity of this blood receiver request.
   *
   * @return quantity of this blood receiver request.
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets the Quantity of this blood receiver request.
   *
   * @param quantity of this blood receiver request.
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Gets the date of this blood receiver request.
   *
   * @return dateOfRequest of this blood receiver request.
   */
  public String getDateOfRequest() {
    return dateOfRequest;
  }

  /**
   * Sets the date of this blood receiver request.
   *
   * @param dateOfRequest of this blood receiver request.
   */
  public void setDateOfRequest(String dateOfRequest) {
    this.dateOfRequest = dateOfRequest;
  }

  /**
   * Gets the status of this blood receiver request.
   *
   * @return status of this blood receiver request.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status of this blood receiver request.
   *
   * @param status of this blood receiver request.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the status change date of this blood receiver request.
   *
   * @return statusChangeDate of this blood receiver request.
   */
  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  /**
   * Sets the status change date of this blood receiver request.
   *
   * @param statusChangeDate of this blood receiver request.
   */
  public void setStatusChangeDate(String statusChangeDate) {
    this.statusChangeDate = statusChangeDate;
  }
}