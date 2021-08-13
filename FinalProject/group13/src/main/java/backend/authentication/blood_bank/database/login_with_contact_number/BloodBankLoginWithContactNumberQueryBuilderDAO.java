package backend.authentication.blood_bank.database.login_with_contact_number;

import backend.authentication.blood_bank.model.BloodBank;

/**
 * {@code BloodBankLoginWithContactNumberQueryBuilderDAO} provides a contract
 * for the blood bank login with contact number.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankLoginWithContactNumberQueryBuilderDAO {

  /**
   * Gets the query to select blood bank by contact number.
   *
   * @param contactNumber contact number of this blood bank.
   *
   * @return string query to select blood bank by email.
   */
  String selectBloodBankByContactNumberQuery(String contactNumber);
}