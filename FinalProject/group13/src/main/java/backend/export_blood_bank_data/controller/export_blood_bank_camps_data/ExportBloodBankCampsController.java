package backend.export_blood_bank_data.controller.export_blood_bank_camps_data;

import backend.blood_bank_camp.controller.view_camplist.ViewCampListController;
import backend.blood_bank_camp.model.Camp;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * {@code ExportBloodBankCampsController} implements the
 * {@code ExportBloodBankCampsControllerDAO} to provide a concrete
 * implementation for exporting blood bank data.
 */
public class ExportBloodBankCampsController
    implements ExportBloodBankCampsControllerDAO {

  // View camp list controller object
  private final ViewCampListController viewCampList;

  /**
   * Constructs this {@code ExportBloodBankCampsController} instance.
   *
   * @param viewCampList view camp list instance.
   */
  public ExportBloodBankCampsController(
      final ViewCampListController viewCampList) {
    this.viewCampList = viewCampList;
  }

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data successfully exported.
   *
   * @throws ExportBloodBankDataException if any error occurs while exporting
   *                                      blood bank data.
   * @throws DatabaseConnectionException  if any error occurs while connecting
   *                                      to the database.
   */
  @Override
  public boolean exportBloodBankCampsOrganizedData(
      final String fileName)
      throws ExportBloodBankDataException,
      DatabaseConnectionException {
    final File file = new File(fileName);
    final List<Camp> campList = viewCampList.viewAllCamp();

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========Blood Bank Organized Camps Data========\n\n");

      fileWriter.write("Camps Organized List\n");
      for (Camp camp : campList) {
        fileWriter.write("Camp Id: " + camp.getCampId() + "\n");
        fileWriter.write("Organizer: " + camp.getOrganizerName() + "\n");
        fileWriter.write("Blood Bank Id: " + camp.getBloodBankId() + "\n");
        fileWriter.write("Date: " + camp.getDate() + "\n");
        fileWriter.write("Time: " + camp.getTime() + "\n");
        fileWriter.write("Available Capacity: " + camp.getAvailableCapacity() + "\n");
        fileWriter.write("Venue: " + camp.getVenue() + "\n");
        fileWriter.write("City: " + camp.getCity() + "\n");
        fileWriter.write("Contact: " + camp.getContactNumber() + "\n");
      }
      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportBloodBankDataException(e.getMessage());
    }
  }
}
