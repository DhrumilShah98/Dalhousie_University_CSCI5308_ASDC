package backend.blood_receiver_request.database;

import backend.blood_receiver_request.model.BloodReceiverRequest;

/**
 * {@code BloodReceiverRequestQueryBuilderDAO} provides a contract for the
 * blood receiver request.
 */
public interface BloodReceiverRequestQueryBuilderDAO {

  /**
   * Gets the query to insert this blood receiver request in the table.
   *
   * @param bloodReceiverRequest blood receiver request to insert in the table.
   *
   * @return string query to insert this blood receiver request in the table.
   */
  String insertBloodReceiverRequest(BloodReceiverRequest bloodReceiverRequest);


  /**
   * Gets the query to select this blood receiver request from the table.
   *
   * @param userId to select blood receiver request in the table.
   *
   * @return string query to select this blood receiver request from the table.
   */
  String selectBloodReceiverQuery(int userId);

  /**
   * Gets the query to update this blood receiver request from the table.
   *
   * @param userId to update blood receiver request in the table.
   *
   * @return string query to update this blood receiver request from the table.
   */
  String updateRequestStatusQuery(int userId);
}