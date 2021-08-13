package backend.blood_request_statistic.controller.number_of_active_receiver;

import backend.blood_request_statistic.database.AllStatisticsQueryBuilderDAO;
import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * {@code ActiveReceiverNumberController} implements the
 * {@code ActiveReceiverNumberControllerDAO} to provide a concrete
 * implementation for the finding average receiver requester age.
 */
public final class ActiveReceiverNumberController implements ActiveReceiverNumberControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // All Statistics query builder instance.
  private final AllStatisticsQueryBuilderDAO
      allStatisticsQueryBuilderDAO;

  /**
   * Constructs this {@code ActiveReceiverNumberController} instance.
   *
   * @param databaseConnectionDAO        database connection instance.
   * @param allStatisticsQueryBuilderDAO all statistic builder
   *                                     query builder instance.
   */
  public ActiveReceiverNumberController(
      final DatabaseConnectionDAO databaseConnectionDAO,
      final AllStatisticsQueryBuilderDAO allStatisticsQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.allStatisticsQueryBuilderDAO = allStatisticsQueryBuilderDAO;
  }

  /**
   * perform total count of active request.
   *
   * @param allBloodReceiverList to find count.
   *
   * @return total number of active requests
   *
   * @throws SQLException if any error occurs while
   *                      connecting to the database.
   */
  private int activeReceiverRequestNumber(final ResultSet allBloodReceiverList) throws SQLException {
    int number = 0;
    while (allBloodReceiverList.next()) {
      number++;
    }
    return number;
  }

  /**
   * Performs view active receiver number from all blood receiver requests.
   *
   * @return count of all blood receiver requests.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  @Override
  public int viewActiveReceiverNumber() throws DatabaseConnectionException {
    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet activeBloodReceiverResultSet = statement.executeQuery(allStatisticsQueryBuilderDAO.selectActiveBloodReceiverRequestQuery())) {

      return activeReceiverRequestNumber(activeBloodReceiverResultSet);
    } catch (SQLException | DatabaseConnectionException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }
}