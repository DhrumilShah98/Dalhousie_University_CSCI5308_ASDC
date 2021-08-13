package backend.authentication.blood_bank.controller.login_with_email;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnectionException;

/**
 * {@code BloodBankLoginWithEmailControllerDAO} provides a contract for the
 * blood bank login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankLoginWithEmailControllerDAO {

  /**
   * Performs login of this blood bank with email.
   *
   * @param email    blood bank email.
   * @param password blood bank password.
   *
   * @return logged-in blood bank instance.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  BloodBank loginWithEmail(final String email, final String password)
      throws BloodBankAuthenticationException, DatabaseConnectionException;
}