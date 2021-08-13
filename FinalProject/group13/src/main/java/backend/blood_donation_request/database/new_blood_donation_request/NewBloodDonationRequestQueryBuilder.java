package backend.blood_donation_request.database.new_blood_donation_request;

import backend.blood_donation_request.model.BloodDonationRequest;

import java.time.LocalDate;

import static backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant.*;
import static backend.blood_donation_request.database.BloodDonationRequestDatabaseConstant.CERTIFICATE_ID_COLUMN;

/**
 * {@code NewBloodDonationRequestQueryBuilder} implements the
 * {@code NewBloodDonationRequestQueryBuilderDAO} to provide a concrete
 * implementation for creating new blood donation request.
 * This class is implemented using the Singleton Design Pattern.
 */
public class NewBloodDonationRequestQueryBuilder
    implements NewBloodDonationRequestQueryBuilderDAO {

  // Static instance of this class
  private static NewBloodDonationRequestQueryBuilder instance;

  /**
   * Constructs this {@code NewBloodDonationRequestQueryBuilder} instance.
   */
  private NewBloodDonationRequestQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code NewBloodDonationRequestQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code NewBloodDonationRequestQueryBuilder} class.
   */
  public static NewBloodDonationRequestQueryBuilder getInstance() {
    if (instance == null) {
      instance = new NewBloodDonationRequestQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert new blood donation request of this user.
   *
   * @param bloodDonationRequest object of this user.
   *
   * @return string query to insert new blood donation request of this user.
   */
  @Override
  public String insertNewBloodDonationRequestQuery(
      final BloodDonationRequest bloodDonationRequest) {
    return "INSERT INTO " +
        BLOOD_DONATION_REQUEST_TABLE +
        "(" +
        USER_ID_COLUMN +
        ", " +
        REQUEST_DATE_COLUMN +
        ", " +
        STATUS_CHANGE_DATE_COLUMN +
        ", " +
        STATUS_COLUMN +
        ", " +
        CERTIFICATE_ID_COLUMN +
        ") " +
        "VALUES (" +
        "\"" + bloodDonationRequest.getUserId() + "\", " +
        "\"" + LocalDate.now() + "\", " +
        "\"" + LocalDate.now() + "\", " +
        "\"" + bloodDonationRequest.getStatus() + "\", " +
        "\"\");";
  }
}
