package backend.admin_blood_donation_request.database.update_blood_donation_status;

import static backend.admin_blood_donation_request.database.BloodDonationRequestStatusDatabaseConstant.*;

import java.time.LocalDate;

import static backend.admin_blood_donation_request.database.BloodDonationRequestStatusDatabaseConstant.*;

/**
 * {@code UpdateBloodDonationStatusQueryBuilder} implements the
 * {@code UpdateBloodDonationStatusQueryBuilderDAO} to provide a concrete
 * implementation for the status update of blood donor
 * This class is implemented using the Singleton Design Pattern.
 */
public class UpdateBloodDonationStatusQueryBuilder implements UpdateBloodDonationStatusQueryBuilderDAO {

  // Static instance of this class
  private static UpdateBloodDonationStatusQueryBuilder instance;

  /**
   *  Constructs this {@code UpdateBloodDonationStatusQueryBuilder} instance.
   */
  private UpdateBloodDonationStatusQueryBuilder() {
    // Required empty private constructor
  }

  /**
   * Instantiates this {@code UpdateBloodDonationStatusQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UpdateBloodDonationStatusQueryBuilder} class.
   * */
  public static UpdateBloodDonationStatusQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UpdateBloodDonationStatusQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to update status of blood donor.
   *
   * @param requestID request id of blood donor.
   *
   * @return String query to update status of blood donor to
   * fulfilled from requested
   */
  @Override
  public String fulfilledBloodDonationRequestStatus(int requestID) {
    return "UPDATE " +
        BLOOD_DONATION_REQUEST_TABLE + " SET " +
        STATUS_COLUMN + " = \"" + STATUS_FULFILLED + "\", " +
        STATUS_CHANGE_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_DONATION_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
  }

  /**
   * Gets the query to update status of blood donor.
   *
   * @param requestID request id of blood donor.
   *
   * @return String query to update status of blood donor to
   * rejected from requested
   */
  @Override
  public String rejectedBloodDonationRequestStatus(int requestID) {
    return "UPDATE " +
        BLOOD_DONATION_REQUEST_TABLE + " SET " +
        STATUS_COLUMN + " = \"" + STATUS_REJECTED + "\", " +
        STATUS_CHANGE_DATE_COLUMN + " = \"" + String.valueOf(LocalDate.now()) + "\"" + " WHERE " +
        BLOOD_DONATION_REQUEST_ID_COLUMN + " = " + requestID +
        " AND " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
  }
}
