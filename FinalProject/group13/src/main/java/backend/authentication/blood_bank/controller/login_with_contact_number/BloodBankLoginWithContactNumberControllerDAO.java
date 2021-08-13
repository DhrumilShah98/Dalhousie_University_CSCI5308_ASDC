package backend.authentication.blood_bank.controller.login_with_contact_number;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnectionException;

/**
 * {@code BloodBankLoginWithEmailControllerDAO} provides a contract for the
 * blood bank login with contact number.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankLoginWithContactNumberControllerDAO {

  /**
   * Performs login of this blood bank with contact number.
   *
   * @param contactNumber blood bank contact number.
   * @param password      blood bank password.
   *
   * @return logged-in blood bank instance.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  BloodBank loginWithContactNumber(final String contactNumber,
                                   final String password)
      throws BloodBankAuthenticationException, DatabaseConnectionException;
}