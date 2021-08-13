package backend.blood_bank_view_all.controller;

import backend.blood_bank_view_all.model.BloodBankView;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code BloodBankViewControllerDAO} provides a contract for viewing
 * all blood banks.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public interface BloodBankViewControllerDAO {

  /**
   * Gets all blood banks.
   *
   * @return list of all blood banks.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  List<BloodBankView> getAllBloodBanks()
      throws DatabaseConnectionException;
}