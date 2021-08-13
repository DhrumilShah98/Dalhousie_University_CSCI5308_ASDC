package backend.blood_receiver_request.database;

import backend.blood_receiver_request.model.BloodReceiverRequest;
import backend.blood_receiver_request.util.BloodRequestStatusUtil;

import java.time.LocalDate;

import static backend.blood_receiver_request.database.BloodReceiverRequestDatabaseConstant.*;

/**
 * {@code BloodReceiverRequestQueryBuilder} implements the
 * {@code BloodReceiverRequestQueryBuilderDAO} to provide a concrete
 * implementation for the blood receiver request.
 * This class is implemented using the Singleton Design Pattern.
 */
public class BloodReceiverRequestQueryBuilder implements BloodReceiverRequestQueryBuilderDAO {

  // Static instance of this class
  private static BloodReceiverRequestQueryBuilder instance;

  /**
   * Constructs this {@code BloodReceiverRequestQueryBuilder} instance.
   */
  public BloodReceiverRequestQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodReceiverRequestQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodReceiverRequestQueryBuilder} class.
   */
  public static BloodReceiverRequestQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodReceiverRequestQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert this blood receiver request in the table.
   *
   * @param bloodReceiverRequest blood receiver request to insert in the table.
   *
   * @return string query to insert this blood receiver request in the table.
   */
  @Override
  public String insertBloodReceiverRequest(BloodReceiverRequest bloodReceiverRequest) {
    return "INSERT INTO " + BLOOD_RECEIVER_REQUEST_TABLE + "(" +
        USER_ID_COLUMN + ", " +
        BLOOD_GROUP_COLUMN + ", " +
        QUANTITY_COLUMN + ", " +
        DATE_REQUEST_COLUMN + ", " +
        STATUS_COLUMN + ", " +
        STATUS_CHANGED_COLUMN + ") " +
        "VALUES (" +
        "\"" + bloodReceiverRequest.getUserId() + "\", " +
        "\"" + bloodReceiverRequest.getBloodGroup() + "\", " +
        "\"" + bloodReceiverRequest.getQuantity() + "\", " +
        "\"" + LocalDate.now() + "\", " +
        "\"" + bloodReceiverRequest.getStatus() + "\", " +
        "\"" + LocalDate.now() +
        "\");";
  }

  /**
   * Gets the query to select this blood receiver request from the table.
   *
   * @param userId to select blood receiver request in the table.
   *
   * @return string query to select this blood receiver request from the table.
   */
  @Override
  public String selectBloodReceiverQuery(int userId) {
    return "SELECT " +
        REQUEST_ID_COLUMN + ", " +
        USER_ID_COLUMN + ", " +
        BLOOD_GROUP_COLUMN + ", " +
        QUANTITY_COLUMN + ", " +
        DATE_REQUEST_COLUMN + ", " +
        STATUS_COLUMN + ", " +
        STATUS_CHANGED_COLUMN +
        " FROM " +
        BLOOD_RECEIVER_REQUEST_TABLE +
        " WHERE " +
        USER_ID_COLUMN + " = " + userId + ";";
  }

  /**
   * Gets the query to update this blood receiver request from the table.
   *
   * @param userId to update blood receiver request in the table.
   *
   * @return string query to update this blood receiver request from the table.
   */
  @Override
  public String updateRequestStatusQuery(int userId) {
    return "UPDATE " + BLOOD_RECEIVER_REQUEST_TABLE +
        " SET " +
        STATUS_COLUMN + " = \"" + BloodRequestStatusUtil.REQUEST_STATUS + "\", " +
        STATUS_CHANGED_COLUMN + " = \"" + LocalDate.now() + "\"" +
        " WHERE " +
        USER_ID_COLUMN + " = " + userId +
        " AND " +
        STATUS_COLUMN + " = \"" + BloodRequestStatusUtil.ACTIVE_STATUS + "\";";
  }
}