package backend.blood_donation_request.model;

import backend.authentication.blood_bank.model.BloodBank;

import java.util.Date;

/**
 * {@code BloodDonationRequest} class stores the information related to this
 * users blood donation requests.
 * This class acts as a single object/entry/model.
 */
public final class BloodDonationRequest {

  // Request id of this user's blood donation request.
  private final int requestId;

  // Unique id of this user.
  private int userId;

  // Request date of this user's blood donation request.
  private String requestDate;

  // Status Change Date of this user's blood donation request.
  private String statusChangeDate;

  // Status of this user's blood donation request.
  private String status;

  // Certificate id of this user's blood donation request
  private String certificateId;

  /**
   * @param requestId        request Id of this user's blood donation request.
   * @param userId           unique id of this user.
   * @param requestDate      request date of this user's blood donation request.
   * @param statusChangeDate status Change Date of this user's blood donation
   *                         request.
   * @param status           status of this user's blood donation request.
   * @param certificateId    certificate id of this user's blood donation
   *                         request.
   */
  public BloodDonationRequest(final int requestId,
                              final int userId,
                              final String requestDate,
                              final String statusChangeDate,
                              final String status,
                              final String certificateId) {
    this.requestId = requestId;
    this.userId = userId;
    this.requestDate = requestDate;
    this.statusChangeDate = statusChangeDate;
    this.status = status;
    this.certificateId = certificateId;
  }

  /**
   * @param userId           unique id of this user.
   * @param requestDate      request date of this user's blood donation request.
   * @param statusChangeDate status Change Date of this user's blood donation
   *                         request.
   * @param status           status of this user's blood donation request.
   * @param certificateId    certificate Id of this user's blood donation request.
   */
  public BloodDonationRequest(int userId,
                              String requestDate,
                              String statusChangeDate,
                              String status,
                              String certificateId) {
    this(-1, userId, requestDate,
        statusChangeDate, status, certificateId);
  }

  /**
   * Gets the blood donation request's unique id.
   *
   * @return blood donation request unique id.
   */
  public int getRequestId() {
    return requestId;
  }

  /**
   * Gets the unique user id of this user.
   *
   * @return this user's unique id.
   */
  public int getUserId() {
    return userId;
  }

  /**
   * Sets the unique user id for this blood donation request's unique id.
   *
   * @param userId unique user id of this user.
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Gets requestDate of this user's blood donation request.
   *
   * @return requestDate of this user's blood donation request.
   */
  public String getRequestDate() {
    return requestDate;
  }

  /**
   * Sets the requestDate of this user's blood donation request
   *
   * @param requestDate of this user's blood donation request
   */
  public void setRequestDate(String requestDate) {
    this.requestDate = requestDate;
  }

  /**
   * Gets the statusChangeDate of this user's blood donation request.
   *
   * @return statusChangeDate of this user's blood donation request.
   */
  public String getStatusChangeDate() {
    return statusChangeDate;
  }

  /**
   * Sets the statusChangeDate of this user's blood donation request.
   *
   * @param statusChangeDate of this user's blood donation request.
   */
  public void setStatusChangeDate(String statusChangeDate) {
    this.statusChangeDate = statusChangeDate;
  }

  /**
   * Gets the status of this user's blood donation request.
   *
   * @return status of this user's blood donation request.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status of this user's blood donation request.
   *
   * @param status of this user's blood donation request.
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the certificateId of this user's blood donation request.
   *
   * @return certificateId of this user's blood donation request.
   */
  public String getCertificateId() {
    return certificateId;
  }

  /**
   * Sets the certificateId of this user's blood donation request.
   *
   * @param certificateId of this user's blood donation request.
   */
  public void setCertificateId(String certificateId) {
    this.certificateId = certificateId;
  }
}
