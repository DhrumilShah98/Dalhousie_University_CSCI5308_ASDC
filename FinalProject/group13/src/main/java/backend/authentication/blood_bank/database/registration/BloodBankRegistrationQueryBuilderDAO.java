package backend.authentication.blood_bank.database.registration;

import backend.authentication.blood_bank.model.BloodBank;

/**
 * {@code BloodBankRegistrationQueryBuilderDAO} provides a contract for the
 * blood bank registration.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankRegistrationQueryBuilderDAO {

  /**
   * Gets the query to insert this blood bank in the table.
   *
   * @param bloodBank blood bank to insert in the table.
   *
   * @return string query to insert this blood bank in the table.
   */
  String insertBloodBankQuery(final BloodBank bloodBank);

  /**
   * Gets the query to insert security questions and answers for this blood
   * bank in the table.
   *
   * @param bloodBankId   unique id of this blood bank.
   * @param securityQ1Id  security question 1 id of this blood bank.
   * @param securityQ1Ans security question 1 answer of this blood bank.
   * @param securityQ2Id  security question 2 id of this blood bank.
   * @param securityQ2Ans security question 2 answer of this blood bank.
   *
   * @return string query to insert security questions and answers for this
   * blood bank in the table.
   */
  String insertBloodBankSecurityQAQuery(final int bloodBankId,
                                        final int securityQ1Id,
                                        final String securityQ1Ans,
                                        final int securityQ2Id,
                                        final String securityQ2Ans);

  /**
   * Gets the stored procedure query to insert this blood bank in the table.
   *
   * @param bloodBank     blood bank to insert in the table.
   * @param securityQ1Id  security question 1 id of this blood bank.
   * @param securityQ1Ans security question 1 answer of this blood bank.
   * @param securityQ2Id  security question 2 id of this blood bank.
   * @param securityQ2Ans security question 2 answer of this blood bank.
   *
   * @return string stored procedure query to insert this blood bank in the
   * table.
   */
  String storedProcedureForBloodBankRegistrationQuery(final BloodBank bloodBank,
                                                      final int securityQ1Id,
                                                      final String securityQ1Ans,
                                                      final int securityQ2Id,
                                                      final String securityQ2Ans);
}