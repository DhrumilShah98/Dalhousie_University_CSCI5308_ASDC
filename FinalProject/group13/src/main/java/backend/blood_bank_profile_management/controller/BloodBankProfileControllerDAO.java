package backend.blood_bank_profile_management.controller;

import backend.authentication.blood_bank.model.BloodBank;
import backend.blood_bank_profile_management.exception.BloodBankProfileException;
import database.DatabaseConnectionException;

/**
 * {@code BloodBankProfileControllerDAO} provides a contract for updating the
 * blood bank profile details of this blood bank.
 */
public interface BloodBankProfileControllerDAO {

  /**
   * @param bloodBank object of this blood bank.
   *
   * @return boolean true/false returned for successful update.
   *
   * @throws BloodBankProfileException   if any error occurs while updating
   *                                     blood bank profile details.
   * @throws DatabaseConnectionException if any error occurs while connecting
   *                                     to the database.
   */
  boolean updateBloodBankProfileDetails(BloodBank bloodBank)
      throws BloodBankProfileException, DatabaseConnectionException;
}
