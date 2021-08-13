package backend.blood_receiver_request.controller.create_receiver_request;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.user.model.User;
import backend.blood_receiver_request.database.BloodReceiverRequestDatabaseConstant;
import backend.blood_receiver_request.database.BloodReceiverRequestQueryBuilderDAO;
import backend.blood_receiver_request.exception.BloodReceiverRequestException;
import backend.blood_receiver_request.model.BloodReceiverRequest;
import backend.blood_receiver_request.util.BloodReceiverRequestUtil;
import database.DatabaseConnectionDAO;
import database.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * {@code CreateBloodRequestController} implements the
 * {@code CreateBloodRequestControllerDAO} to provide a concrete
 * implementation for the creation of blood receiver request.
 */
public final class CreateBloodRequestController implements CreateBloodRequestControllerDAO {

  // Database connection instance
  private final DatabaseConnectionDAO
      databaseConnectionDAO;

  // Blood receiver request query builder instance.
  private final BloodReceiverRequestQueryBuilderDAO
      bloodReceiverRequestQueryBuilderDAO;

  /**
   * Constructs this {@code BloodBankRegistrationController} instance.
   *
   * @param databaseConnectionDAO               database connection instance.
   * @param bloodReceiverRequestQueryBuilderDAO blood receiver request
   *                                            query builder instance.
   */
  public CreateBloodRequestController(final DatabaseConnectionDAO databaseConnectionDAO,
                                      final BloodReceiverRequestQueryBuilderDAO bloodReceiverRequestQueryBuilderDAO) {
    this.databaseConnectionDAO = databaseConnectionDAO;
    this.bloodReceiverRequestQueryBuilderDAO = bloodReceiverRequestQueryBuilderDAO;
  }

  /**
   * @param userId user id of the blood receiver request.
   *
   * @return {@code true} if blood receiver request already exists for given
   * user otherwise {@code false}.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  private boolean checkLatestRequest(int userId) throws DatabaseConnectionException {

    try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
         final Statement statement = connection.createStatement();
         final ResultSet bloodReceiverRequest = statement.executeQuery(bloodReceiverRequestQueryBuilderDAO.selectBloodReceiverQuery(userId))) {

      if (bloodReceiverRequest.next()) {
        final String status = bloodReceiverRequest.getString(BloodReceiverRequestDatabaseConstant.STATUS_COLUMN);
        return status.equals(BloodReceiverRequestDatabaseConstant.STATUS_COLUMN);
      } else {
        return false;
      }
    } catch (SQLException e) {
      throw new DatabaseConnectionException(e.getMessage(), e);
    }
  }

  /**
   * Validates blood receiver request fields.
   *
   * @param bloodReceiverRequest blood receiver request instance.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request.
   */
  private void validateUserFields(BloodReceiverRequest bloodReceiverRequest)
      throws BloodReceiverRequestException {

    //blood group validation
    final boolean isBloodGroupValid = (bloodReceiverRequest.getBloodGroup() != null) &&
        (!bloodReceiverRequest.getBloodGroup().trim().isEmpty()) &&
        (BloodReceiverRequestUtil.isBloodGroupValid(bloodReceiverRequest.getBloodGroup().trim()));
    if (!isBloodGroupValid) {
      throw new BloodReceiverRequestException("Invalid blood group.");
    }

    // Blood Request Quantity Validation
    final boolean isQuantityValid = (bloodReceiverRequest.getQuantity() != null) &&
        (BloodReceiverRequestUtil.isQuantityValid(bloodReceiverRequest.getQuantity()));
    if (!isQuantityValid) {
      throw new BloodReceiverRequestException("Invalid quantity.");
    }

    //blood request status validation
    final boolean isRequestStatusValid = (bloodReceiverRequest.getStatus() != null) &&
        (!bloodReceiverRequest.getStatus().trim().isEmpty()) &&
        (BloodReceiverRequestUtil.isStatusValid(bloodReceiverRequest.getStatus().trim()));
    if (!isRequestStatusValid) {
      throw new BloodReceiverRequestException("Invalid request Status.");
    }
  }

  /**
   * Performs blood receiver request.
   *
   * @param user                 user of the blood receiver request.
   * @param bloodReceiverRequest blood receiver request tp insert in the table.
   *
   * @return {@code true} if blood receiver requested successfully otherwise
   * {@code false}.
   *
   * @throws BloodReceiverRequestException if any error occurs while
   *                                       blood receiver request.
   * @throws DatabaseConnectionException   if any error occurs while
   *                                       connecting to the database.
   */
  @Override
  public boolean createRequest(User user, BloodReceiverRequest bloodReceiverRequest) throws BloodReceiverRequestException, DatabaseConnectionException {

    // user object null
    if (user == null) {
      throw new BloodReceiverRequestException("Null user.");
    }

    //receiver request null
    if (bloodReceiverRequest == null) {
      throw new BloodReceiverRequestException("Null receiver request.");
    }

    // validate user fields
    validateUserFields(bloodReceiverRequest);

    // check if same request exists
    if (checkLatestRequest(user.getUserId())) {
      throw new BloodReceiverRequestException("Request Already exists. New blood receiver request cannot be created");
    } else {
      bloodReceiverRequest.setUserId(bloodReceiverRequest.getUserId());
      bloodReceiverRequest.setBloodGroup(bloodReceiverRequest.getBloodGroup().trim());
      bloodReceiverRequest.setQuantity(bloodReceiverRequest.getQuantity());
      bloodReceiverRequest.setDateOfRequest(String.valueOf(LocalDate.now()));
      bloodReceiverRequest.setStatus(bloodReceiverRequest.getStatus().trim());
      bloodReceiverRequest.setStatusChangeDate(String.valueOf(LocalDate.now()));

      ResultSet generatedKeysResultSet = null;
      try (final Connection connection = databaseConnectionDAO.getDatabaseConnection();
           final Statement statement = connection.createStatement()) {
        final String insertRequestQuery = bloodReceiverRequestQueryBuilderDAO.insertBloodReceiverRequest(bloodReceiverRequest);
        final int requestRowInserted = statement.executeUpdate(insertRequestQuery, Statement.RETURN_GENERATED_KEYS);
        if (requestRowInserted > 0) {
          generatedKeysResultSet = statement.getGeneratedKeys();
          if (generatedKeysResultSet.next()) {
            return true;
          }
          generatedKeysResultSet.close();
          return false;
        }
        return false;
      } catch (SQLException e) {
        if (generatedKeysResultSet != null) {
          try {
            generatedKeysResultSet.close();
          } catch (SQLException e1) {
            throw new DatabaseConnectionException(e.getMessage(), e1);
          }
        }
        throw new DatabaseConnectionException(e.getMessage(), e);
      }
    }
  }
}