package backend.blood_bank_view_all.database;

/**
 * {@code BloodBankViewQueryBuilderDAO} provides a contract for viewing all
 * the blood banks.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankViewQueryBuilderDAO {

  /**
   * Gets the query to view all the blood banks.
   *
   * @return query to view all the blood banks.
   */
  String selectAllBloodBanksQuery();
}