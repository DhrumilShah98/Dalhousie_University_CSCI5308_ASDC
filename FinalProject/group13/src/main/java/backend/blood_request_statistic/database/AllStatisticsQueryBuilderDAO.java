package backend.blood_request_statistic.database;

/**
 * {@code AllStatisticsQueryBuilderDAO} provides a contract for the
 * blood receiver request statistics.
 */
public interface AllStatisticsQueryBuilderDAO {

  /**
   * Gets the query to select this blood receiver request in the table.
   *
   * @return string query to select this blood receiver request in the table.
   */
  String selectBloodReceiverRequestQuery();

  /**
   * Gets the query to select this active blood receiver request in the table.
   *
   * @return string query to select this active blood receiver request in the
   * table.
   */
  String selectActiveBloodReceiverRequestQuery();
}