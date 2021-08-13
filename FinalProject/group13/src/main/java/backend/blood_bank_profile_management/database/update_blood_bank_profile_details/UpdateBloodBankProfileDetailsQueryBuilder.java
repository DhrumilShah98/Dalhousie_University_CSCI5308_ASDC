package backend.blood_bank_profile_management.database.update_blood_bank_profile_details;

import backend.authentication.blood_bank.model.BloodBank;

import static backend.blood_bank_profile_management.database.BloodBankProfileDatabaseConstant.*;

/**
 * {@code UpdateBloodBankProfileDetailsQueryBuilder} implements the
 * {@code UpdateBloodBankProfileDetailsQueryBuilderDAO} to provide a concrete
 * implementation for updating blood bank profile details.
 * This class is implemented using the Singleton Design Pattern.
 */
public class UpdateBloodBankProfileDetailsQueryBuilder
    implements UpdateBloodBankProfileDetailsQueryBuilderDAO {

  // Static instance of this class
  private static UpdateBloodBankProfileDetailsQueryBuilder instance;

  /**
   * Constructs this {@code UpdateBloodBankProfileDetailsQueryBuilder} instance.
   */
  private UpdateBloodBankProfileDetailsQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code UpdateBloodBankProfileDetailsQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code UpdateBloodBankProfileDetailsQueryBuilder} class.
   */
  public static UpdateBloodBankProfileDetailsQueryBuilder getInstance() {
    if (instance == null) {
      instance = new UpdateBloodBankProfileDetailsQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to update blood bank profile details of this blood bank.
   *
   * @param bloodBank object of this blood bank.
   *
   * @return string query to update blood bank profile details of this blood bank.
   */
  @Override
  public String updateBloodBankProfileDetailsQuery(
      final BloodBank bloodBank) {
    return "UPDATE " +
        BLOOD_BANK_TABLE +
        " SET " +
        BLOOD_BANK_NAME_COLUMN + "=\"" + bloodBank.getName() +
        "\", " +
        BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN + "=\"" + bloodBank.getAddressFirstLine() +
        "\", " +
        BLOOD_BANK_ADDRESS_STREET_COLUMN + "=\"" + bloodBank.getAddressStreet() +
        "\", " +
        BLOOD_BANK_ADDRESS_CITY_COLUMN + "=\"" + bloodBank.getAddressCity() +
        "\", " +
        BLOOD_BANK_ADDRESS_PROVINCE_COLUMN + "=\"" + bloodBank.getAddressProvince() +
        "\", " +
        BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN + "=\"" + bloodBank.getAddressZipCode() +
        "\", " +
        BLOOD_BANK_ADDRESS_COUNTRY_COLUMN + "=\"" + bloodBank.getAddressCountry() +
        "\"" +
        " WHERE " +
        BLOOD_BANK_ID_COLUMN + "=" + bloodBank.getBloodBankId() + ";";
  }
}
