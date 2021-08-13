package backend.blood_bank_camp.controller.camp_organize;

import backend.authentication.blood_bank.exception.BloodBankAuthenticationException;
import backend.blood_bank_camp.exception.CampRegistrationException;
import backend.blood_bank_camp.model.Camp;
import database.DatabaseConnectionException;

/**
 * {@code CampOrganizeControllerDAO} provides a contract for the
 * camp registration.
 */
public interface CampOrganizeControllerDAO {

  /**
   * Performs registration of this blood bank.
   *
   * @param camp camp to insert in the table.
   *
   * @return {@code true} if blood bank registered successfully otherwise
   * {@code false}.
   *
   * @throws CampRegistrationException   if any error occurs while
   *                                     camp register.
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  boolean register(Camp camp) throws CampRegistrationException, DatabaseConnectionException;
}