package backend.export_blood_bank_data.controller.export_blood_bank_stock_data;

import backend.blood_stock_management.exception.BloodStockException;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;

/**
 * {@code ExportBloodBankStockDataControllerDAO} provides a contract for
 * exporting blood bank stock data.
 */
public interface ExportBloodBankStockDataControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws ExportBloodBankDataException if any error occurs while
   *                                      exporting blood bank data.
   * @throws BloodStockException          if any error occurs while fetching
   *                                      blood stock data.
   * @throws DatabaseConnectionException  if any error occurs while
   *                                      connecting to the database.
   */
  boolean exportBloodStockData(final String fileName)
      throws ExportBloodBankDataException,
      BloodStockException,
      DatabaseConnectionException;
}
