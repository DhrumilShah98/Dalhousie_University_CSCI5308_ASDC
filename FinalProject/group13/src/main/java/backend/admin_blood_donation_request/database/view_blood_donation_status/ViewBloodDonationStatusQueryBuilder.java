package backend.admin_blood_donation_request.database.view_blood_donation_status;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import database.DatabaseConnectionException;

import static backend.admin_blood_donation_request.database.BloodDonationRequestStatusDatabaseConstant.*;

/**
 * {@code ViewBloodDonationStatusQueryBuilder} implements the
 * {@code ViewBloodDonationStatusQueryBuilderDAO} to provide a concrete
 * implementation for the status viewing of blood donor
 * This class is implemented using the Singleton Design Pattern.
 */
public class ViewBloodDonationStatusQueryBuilder implements ViewBloodDonationStatusQueryBuilderDAO {

  // Static instance of this class
  private static ViewBloodDonationStatusQueryBuilder instance;

  /**
   * Constructs this {@code ViewBloodDonationStatusQueryBuilder} instance.
   */
  private ViewBloodDonationStatusQueryBuilder() {
    // Required empty private constructor
  }

  /**
   * Instantiates this {@code ViewBloodDonationStatusQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code ViewBloodDonationStatusQueryBuilder} class.
   */
  public static ViewBloodDonationStatusQueryBuilder getInstance() {
    if (instance == null) {
      instance = new ViewBloodDonationStatusQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to view status of blood donor.
   *
   * @return String query to select status of blood donor.
   *
   * @throws DatabaseConnectionException  when error occurs while connecting
   * to the database.
   *
   * @throws BloodDonationStatisticsException when error occurs while
   * fetching blood donation statistics.
   */
  @Override
  public String viewBloodDonationStatusQuery() throws DatabaseConnectionException, BloodDonationStatisticsException {
    return "SELECT " +
        BLOOD_DONATION_REQUEST_ID_COLUMN + ", " +
        USER_ID_COLUMN + ", " +
        REQUEST_DATE_COLUMN + ", " +
        STATUS_CHANGE_DATE_COLUMN + ", " +
        STATUS_COLUMN +
        " FROM " +
        BLOOD_DONATION_REQUEST_TABLE +
        " WHERE " +
        STATUS_COLUMN + " = \"" + STATUS_REQUEST + "\";";
  }
}
