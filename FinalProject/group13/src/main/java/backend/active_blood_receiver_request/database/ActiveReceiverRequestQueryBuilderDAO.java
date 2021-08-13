package backend.active_blood_receiver_request.database;

/**
 * {@code ActiveReceiverRequestQueryBuilderDAO} provides a contract
 * for viewing all active blood receiver request.
 */
public interface ActiveReceiverRequestQueryBuilderDAO {

  /**
   * Gets the query to select active blood receiver requests.
   *
   * @return string query to select active blood receiver requests.
   */
  String selectActiveBloodReceiverRequestQuery();
}