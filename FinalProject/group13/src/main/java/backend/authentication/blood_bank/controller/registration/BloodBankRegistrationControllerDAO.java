package backend.authentication.blood_bank.controller.registration;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.authentication.blood_bank.model.BloodBank;
import database.DatabaseConnectionException;

/**
 * {@code BloodBankRegistrationControllerDAO} provides a contract for the
 * blood bank registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRegistrationControllerDAO {

  /**
   * Performs registration of this blood bank.
   *
   * @param bloodBank            blood bank to insert in the table.
   * @param securityQuestion1Id  security question 1 id of this blood bank.
   * @param securityQuestion1Ans security question 1 answer of this blood bank.
   * @param securityQuestion2Id  security question 2 id of this blood bank.
   * @param securityQuestion2Ans security question 2 answer of this blood bank.
   *
   * @return {@code true} if blood bank registered successfully otherwise
   * {@code false}.
   *
   * @throws BloodBankAuthenticationException if any error occurs while
   *                                          blood bank authentication.
   * @throws DatabaseConnectionException      if any error occurs while
   *                                          connecting to the database.
   */
  boolean register(final BloodBank bloodBank,
                   final int securityQuestion1Id,
                   final String securityQuestion1Ans,
                   final int securityQuestion2Id,
                   final String securityQuestion2Ans)
      throws BloodBankAuthenticationException, DatabaseConnectionException;
}