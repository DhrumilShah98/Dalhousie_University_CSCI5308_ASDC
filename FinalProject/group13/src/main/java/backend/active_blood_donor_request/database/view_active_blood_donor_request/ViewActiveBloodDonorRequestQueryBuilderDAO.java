package backend.active_blood_donor_request.database.view_active_blood_donor_request;

/**
 * {@code ViewActiveBloodDonorRequestQueryBuilderDAO} provides a contract
 * for viewing all active blood donation request.
 */
public interface ViewActiveBloodDonorRequestQueryBuilderDAO {

  /**
   * Gets the query to select active blood donation requests.
   *
   * @return string query to select active blood donation requests.
   */
  String selectActiveBloodDonorRequestQuery();
}
