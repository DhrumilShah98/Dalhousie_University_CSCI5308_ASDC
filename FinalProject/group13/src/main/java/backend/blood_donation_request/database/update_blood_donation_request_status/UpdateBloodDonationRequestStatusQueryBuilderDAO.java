package backend.blood_donation_request.database.update_blood_donation_request_status;

/**
 * {@code UpdateBloodDonationRequestStatusQueryBuilderDAO} provides a contract
 * for updating blood donation request status for this user.
 */
public interface UpdateBloodDonationRequestStatusQueryBuilderDAO {

  /**
   * Gets the query to update blood donation request of this user.
   *
   * @param userId unique id of this user.
   *
   * @return string query to update the blood donation request status of this
   * user.
   */
  String updateBloodDonationRequestStatusQuery(final int userId);
}
