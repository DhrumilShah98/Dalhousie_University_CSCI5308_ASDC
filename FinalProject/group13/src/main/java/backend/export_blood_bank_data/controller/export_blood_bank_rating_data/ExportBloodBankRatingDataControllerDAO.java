package backend.export_blood_bank_data.controller.export_blood_bank_rating_data;

import backend.blood_bank_rating_analysis.analysis_by_age_group.exception.BloodBankRatingAnalysisAgeGroupException;
import backend.blood_bank_rating_analysis.analysis_by_location.exception.BloodBankRatingAnalysisLocationException;
import database.DatabaseConnectionException;
import backend.export_blood_bank_data.exception.ExportBloodBankDataException;

/**
 * {@code ExportBloodBankRatingDataControllerDAO} provides a contract for
 * exporting blood bank rating data.
 */
public interface ExportBloodBankRatingDataControllerDAO {

  /**
   * @param fileName of the file where data will be exported.
   *
   * @return boolean true/false if data exported successfully.
   *
   * @throws ExportBloodBankDataException             if any error occurs while
   *                                                  exporting blood bank data.
   * @throws BloodBankRatingAnalysisAgeGroupException if any error occurs
   *                                                  while fetching blood bank
   *                                                  ratings.
   * @throws DatabaseConnectionException              if any error occurs while
   *                                                  connecting to the database.
   * @throws BloodBankRatingAnalysisLocationException if any error occurs
   *                                                  while fetching blood
   *                                                  bank ratings.
   */
  boolean exportBloodBankRatingData(final String fileName)
      throws ExportBloodBankDataException,
      BloodBankRatingAnalysisAgeGroupException,
      DatabaseConnectionException,
      BloodBankRatingAnalysisLocationException;
}
