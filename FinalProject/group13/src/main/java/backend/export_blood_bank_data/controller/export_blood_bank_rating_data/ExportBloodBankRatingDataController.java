package backend.export_blood_bank_data.controller.export_blood_bank_rating_data;

import backend.blood_bank_rating_analysis.analysis_by_age_group.controller.BloodBankRatingAnalysisAgeGroupController;
import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import backend.blood_bank_rating_analysis.analysis_by_location.controller.BloodBankRatingAnalysisLocationController;
import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import backend.blood_bank_rating_analysis.analysis_by_location.model.BloodBankRatingAnalysisLocation;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;
import frontend.session.Session;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * {@code ExportBloodBankRatingDataController} implements the
 * {@code ExportBloodBankRatingDataControllerDAO} to provide a concrete
 * implementation for exporting blood bank rating data.
 */
public class ExportBloodBankRatingDataController
    implements ExportBloodBankRatingDataControllerDAO {

  // Blood bank rating analysis of age group instance.
  private final BloodBankRatingAnalysisAgeGroupController
      bloodBankRatingAnalysisAgeGroupController;

  // Blood bank rating analysis of location instance.
  private final BloodBankRatingAnalysisLocationController
      bloodBankRatingAnalysisLocationController;

  /**
   * Constructs this {@code ExportBloodBankRatingDataController} instance.
   *
   * @param bloodBankRatingAnalysisAgeGroupController blood bank rating
   *                                                  analysis of age group
   *                                                  instance.
   * @param bloodBankRatingAnalysisLocationController blood bank rating
   *                                                  analysis of location
   *                                                  instance.
   */
  public ExportBloodBankRatingDataController(
      final BloodBankRatingAnalysisAgeGroupController
          bloodBankRatingAnalysisAgeGroupController,
      final BloodBankRatingAnalysisLocationController
          bloodBankRatingAnalysisLocationController) {
    this.bloodBankRatingAnalysisAgeGroupController =
        bloodBankRatingAnalysisAgeGroupController;
    this.bloodBankRatingAnalysisLocationController =
        bloodBankRatingAnalysisLocationController;
  }

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data successfully exported.
   *
   * @throws ExportBloodBankDataException             if any error occurs while
   *                                                  exporting blood bank data.
   * @throws BloodBankRatingAnalysisAgeGroupException if any error occurs
   *                                                  while fetching blood bank
   *                                                  ratings.
   * @throws DatabaseConnectionException              if any error occurs while
   *                                                  connecting to the database.
   * @throws BloodBankRatingAnalysisLocationException if any error occurs
   *                                                  while fetching blood bank
   *                                                  ratings.
   */
  @Override
  public boolean exportBloodBankRatingData(final String fileName)
      throws ExportBloodBankDataException,
      BloodBankRatingAnalysisAgeGroupException,
      DatabaseConnectionException,
      BloodBankRatingAnalysisLocationException {
    final File file = new File(fileName);
    final Map<String, Float> bloodBankRatingByAge =
        bloodBankRatingAnalysisAgeGroupController.getBloodBankRatings(Session.BLOOD_BANK.getBloodBankId());
    final List<BloodBankRatingAnalysisLocation> bloodBankRatingByLocation =
        bloodBankRatingAnalysisLocationController.getBloodBankRatings();

    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write("========Blood Bank Rating Data========\n\n");

      fileWriter.write("Blood Bank Rating Analysis by Age\n");
      for (Map.Entry<String, Float> entry : bloodBankRatingByAge.entrySet()) {
        fileWriter.write(entry.getKey() + "  " + entry.getValue() + "\n");
      }

      fileWriter.write("Blood Bank Rating Analysis by Location\n");
      for (BloodBankRatingAnalysisLocation bloodBankRatingAnalysisLocation : bloodBankRatingByLocation) {
        fileWriter.write("Blood Bank Id: " + bloodBankRatingAnalysisLocation.getBloodBankId() + "\n");
        fileWriter.write("Blood Bank Name: " + bloodBankRatingAnalysisLocation.getBloodBankName() + "\n");
        fileWriter.write("Address Province: " + bloodBankRatingAnalysisLocation.getAddressProvince() + "\n");
        fileWriter.write("Star: " + bloodBankRatingAnalysisLocation.getStar() + "\n");
      }
      fileWriter.flush();
      return true;
    } catch (IOException e) {
      throw new ExportBloodBankDataException(e.getMessage());
    }
  }
}
