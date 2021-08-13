package backend.blood_donation_request.database.view_previous_blood_donation_request;

/**
 * {@code ViewPreviousBloodDonationRequestQueryBuilderDAO} provides a contract
 * for viewing previous blood donation requests of this user.
 */
public interface ViewPreviousBloodDonationRequestQueryBuilderDAO {

  /**
   * Gets the query to select previous blood donation requests of this user.
   *
   * @param userId unique id of this user.
   *
   * @return string query to select previous blood donation requests of this
   * user.
   */
  String selectPreviousBloodDonationQuery(final int userId);
}
