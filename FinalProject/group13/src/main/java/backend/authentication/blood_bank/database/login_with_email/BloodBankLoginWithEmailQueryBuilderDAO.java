package backend.authentication.blood_bank.database.login_with_email;

/**
 * {@code BloodBankLoginWithEmailQueryBuilderDAO} provides a contract for the
 * blood bank login with email.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankLoginWithEmailQueryBuilderDAO {

  /**
   * Gets the query to select blood bank by email.
   *
   * @param email email of this blood bank.
   *
   * @return string query to select blood bank by email.
   */
  String selectBloodBankByEmailQuery(String email);
}
