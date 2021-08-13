package backend.blood_bank_profile_management.database.update_blood_bank_profile_details;

import backend.authentication.blood_bank.model.BloodBank;

/**
 * {@code UpdateBloodBankProfileDetailsQueryBuilderDAO} provides a contract
 * for updating blood bank profile details.
 */
public interface UpdateBloodBankProfileDetailsQueryBuilderDAO {

  /**
   * Gets the query to update blood bank profile details.
   *
   * @param bloodBank object of this blood bank.
   *
   * @return string query to update blood bank profile details.
   */
  String updateBloodBankProfileDetailsQuery(final BloodBank bloodBank);
}
