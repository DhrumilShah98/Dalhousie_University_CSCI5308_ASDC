package backend.export_blood_bank_data.controller.export_blood_bank_camps_data;

import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;

/**
 * {@code ExportBloodBankCampsControllerDAO} provides a contract for
 * exporting blood bank camp data.
 */
public interface ExportBloodBankCampsControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws ExportBloodBankDataException if any error occurs while exporting
   *                                      blood bank data.
   * @throws DatabaseConnectionException  if any error occurs while connecting
   *                                      to the database.
   */
  boolean exportBloodBankCampsOrganizedData(final String fileName)
      throws ExportBloodBankDataException,
      DatabaseConnectionException;
}
