package backend.blood_bank_camp.controller.view_camplist;

import backend.blood_bank_camp.exception.CampRegistrationException;
import backend.blood_bank_camp.model.Camp;
import database.DatabaseConnectionException;

import java.util.List;

/**
 * {@code ViewCampListControllerDAO} provides a contract for the
 * all camp list view.
 */
public interface ViewCampListControllerDAO {

  /**
   * Performs all camp list view.
   *
   * @return All camp list.
   *
   * @throws DatabaseConnectionException if any error occurs while
   *                                     connecting to the database.
   */
  List<Camp> viewAllCamp() throws DatabaseConnectionException;
}
