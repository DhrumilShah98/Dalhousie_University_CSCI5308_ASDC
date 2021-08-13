package backend.authentication.blood_bank.database;

/**
 * {@code BloodBankDatabaseConstant} stores the information related to the
 * blood bank database.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankDatabaseConstant {

  // Blood bank table.
  public static final String BLOOD_BANK_TABLE =
      "blood_bank";

  // Blood bank unique id column.
  public static final String BLOOD_BANK_ID_COLUMN =
      "blood_bank_id";

  // Blood bank name column.
  public static final String BLOOD_BANK_NAME_COLUMN =
      "name";

  // Blood bank email column.
  public static final String BLOOD_BANK_EMAIL_COLUMN =
      "email";

  // Blood bank password column.
  public static final String BLOOD_BANK_PASSWORD_COLUMN =
      "password";

  // Blood bank contact number column.
  public static final String BLOOD_BANK_CONTACT_NUMBER_COLUMN =
      "contact_number";

  // Blood bank address first line column.
  public static final String BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN =
      "address_first_line";

  // Blood bank address street column.
  public static final String BLOOD_BANK_ADDRESS_STREET_COLUMN =
      "address_street";

  // Blood bank address city column.
  public static final String BLOOD_BANK_ADDRESS_CITY_COLUMN =
      "address_city";

  // Blood bank address province column.
  public static final String BLOOD_BANK_ADDRESS_PROVINCE_COLUMN =
      "address_province";

  // Blood bank address zip code column.
  public static final String BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN =
      "address_zip_code";

  // Blood bank address country column.
  public static final String BLOOD_BANK_ADDRESS_COUNTRY_COLUMN =
      "address_country";

  // Blood bank account active status column.
  public static final String BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN =
      "account_active";

  // Blood bank security question id column.
  public static final String SECURITY_QUESTION_ID_COLUMN =
      "security_question_id";

  // Blood bank security question blood bank intermediate table.
  public static final String BLOOD_BANK_SECURITY_QUESTION_BLOOD_BANK_TABLE =
      "blood_bank_security_question_blood_bank";

  // Blood bank security question ans column.
  public static final String BLOOD_BANK_SECURITY_QUESTION_ANS_COLUMN =
      "security_question_ans";

  // Blood bank registration stored procedure name.
  public static final String BLOOD_BANK_REGISTRATION_STORED_PROCEDURE =
      "blood_bank_registration";
}