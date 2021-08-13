package backend.blood_donation_request.database.new_blood_donation_request;

import backend.blood_donation_request.model.BloodDonationRequest;

/**
 * {@code NewBloodDonationRequestQueryBuilderDAO} provides a contract
 * for the new blood donation request query builder.
 */
public interface NewBloodDonationRequestQueryBuilderDAO {

  /**
   * Gets the query to insert new blood donation request for this user.
   *
   * @param bloodDonationRequest object of this user.
   *
   * @return string query to insert new blood donation request.
   */
  String insertNewBloodDonationRequestQuery(
      final BloodDonationRequest bloodDonationRequest);
}
