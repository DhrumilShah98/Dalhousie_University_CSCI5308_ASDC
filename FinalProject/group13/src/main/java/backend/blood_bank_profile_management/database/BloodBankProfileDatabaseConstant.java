package backend.blood_bank_profile_management.database;

import backend.authentication.blood_bank.model.BloodBank;

/**
 * {@code BloodBankProfileDatabaseConstant} stores the information related
 * to the blood bank profile.
 */
public class BloodBankProfileDatabaseConstant {

  // Blood bank table name.
  public static final String BLOOD_BANK_TABLE =
      "blood_bank";

  // Blood bank table unique id column.
  public static final String BLOOD_BANK_ID_COLUMN =
      "blood_bank_id";

  // Blood bank table name column.
  public static final String BLOOD_BANK_NAME_COLUMN =
      "name";

  // Blood bank table address first line column.
  public static final String BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN =
      "address_first_line";

  // Blood bank table address street column.
  public static final String BLOOD_BANK_ADDRESS_STREET_COLUMN =
      "address_street";

  // Blood bank table address city column.
  public static final String BLOOD_BANK_ADDRESS_CITY_COLUMN =
      "address_city";

  // Blood bank table address province column.
  public static final String BLOOD_BANK_ADDRESS_PROVINCE_COLUMN =
      "address_province";

  // Blood bank table address zip code column.
  public static final String BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN =
      "address_zip_code";

  // Blood bank table address country column.
  public static final String BLOOD_BANK_ADDRESS_COUNTRY_COLUMN =
      "address_country";

  // Blood bank table active column.
  public static final String USER_ACCOUNT_ACTIVE_COLUMN =
      "account_active";
}
