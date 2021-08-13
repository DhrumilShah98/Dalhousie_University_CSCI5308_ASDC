package backend.demographic_information.database;

import backend.authentication.user.database.UserDatabaseConstant;
import backend.authentication.blood_bank.database.BloodBankDatabaseConstant;
import backend.vaccination.database.VaccinationQueryBuilder;

/**
 * {@code DemographicInformationQueryBuilder} implements the
 * {@code DemographicInformationQueryBuilderDAO} to provide a concrete
 * implementation for the fetching information of active user and bloodbank
 * number from particular province.
 * This class is implemented using the Singleton Design Pattern.
 */
public final class DemographicInformationQueryBuilder implements DemographicInformationQueryBuilderDAO {

  // Static instance of this class
  private static DemographicInformationQueryBuilder instance;

  /**
   * Constructs this {@code DemographicInformationQueryBuilder} instance.
   */
  private DemographicInformationQueryBuilder() {
    //Required private empty constructor
  }

  /**
   * Instantiates this {@code DemographicInformationQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code DemographicInformationQueryBuilder} class.
   */
  public static DemographicInformationQueryBuilder getInstance() {
    if (instance == null) {
      instance = new DemographicInformationQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to see information of active user's status in
   * particular province.
   *
   * @param provinceName province name is used to get the inforamtion of
   *                     that region.
   *
   * @return string query to get information of active user's status in that
   * province.
   */
  @Override
  public String fetchActiveUserInProvince(String provinceName) {
    return "SELECT COUNT("
        + UserDatabaseConstant.USER_ID_COLUMN + ") FROM "
        + UserDatabaseConstant.USER_TABLE + " WHERE "
        + UserDatabaseConstant.USER_ADDRESS_PROVINCE_COLUMN + " = \"" + provinceName + "\";";
  }

  /**
   * Gets the query to see information of active bloodbank's status in
   * particular province.
   *
   * @param provinceName province name is used to get the information of
   *                     that region.
   *
   * @return string query to get information of active bloodbank's status of
   * that province.
   */
  @Override
  public String fetchActiveBloodBankInProvince(String provinceName) {
    return "SELECT COUNT("
        + BloodBankDatabaseConstant.BLOOD_BANK_ID_COLUMN + ") FROM "
        + BloodBankDatabaseConstant.BLOOD_BANK_TABLE + " WHERE "
        + BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN + " = \"" + provinceName + "\";";
  }
}
