package backend.export_blood_bank_data.controller.export_blood_bank_stock_data;

import backend.blood_stock_management.controller.view_blood_stock.ViewBloodStockController;
import backend.blood_stock_management.exception.BloodStockException;
import backend.blood_stock_management.model.BloodStock;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;
import frontend.session.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * {@code ExportBloodBankStockDataController} implements the
 * {@code ExportBloodBankStockDataControllerDAO} to provide a concrete
 * implementation for exporting blood bank stock data.
 */
public class ExportBloodBankStockDataController
    implements ExportBloodBankStockDataControllerDAO {

  // View blood stock data instance.
  private final ViewBloodStockController
      viewBloodStockController;

  /**
   * Constructs this {@code ExportBloodBankStockDataController} instance.
   *
   * @param viewBloodStockController view blood stock controller instance.
   */
  public ExportBloodBankStockDataController(
      final ViewBloodStockController viewBloodStockController) {
    this.viewBloodStockController = viewBloodStockController;
  }

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data successfully exported.
   *
   * @throws ExportBloodBankDataException if any error occurs while
   *                                      exporting blood bank data.
   * @throws BloodStockException          if any error occurs while fetching
   *                                      blood stock data.
   * @throws DatabaseConnectionException  if any error occurs while
   *                                      connecting to the database.
   */
  @Override
  public boolean exportBloodStockData(final String fileName)
      throws ExportBloodBankDataException,
      BloodStockException,
      DatabaseConnectionException {
    final File file = new File(fileName);
    final List<BloodStock> bloodStockList =
        viewBloodStockController.viewBloodStock(Session.BLOOD_BANK.getBloodBankId());

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========Blood Bank Stock Data========\n\n");

      fileWriter.write("\n");
      for (BloodStock bloodStock : bloodStockList) {
        fileWriter.write("Blood Stock Id: " + bloodStock.getBloodStockId() + "\n");
        fileWriter.write("Blood Bank Id: " + bloodStock.getBloodBankId() + "\n");
        fileWriter.write("Blood Group: " + bloodStock.getBloodGroup() + "\n");
        fileWriter.write("Quantity: " + bloodStock.getQuantity() + "\n");
        fileWriter.write("Threshold: " + bloodStock.getThreshold() + "\n");
        fileWriter.write("Unit Price: " + bloodStock.getUnitPrice() + "\n");
      }
      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportBloodBankDataException(e.getMessage());
    }
  }
}
