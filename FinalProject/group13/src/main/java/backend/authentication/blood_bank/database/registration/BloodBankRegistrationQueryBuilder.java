package backend.authentication.blood_bank.database.registration;

import backend.authentication.blood_bank.model.BloodBank;

import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ID_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_NAME_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_EMAIL_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_PASSWORD_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_CONTACT_NUMBER_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_STREET_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_CITY_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_PROVINCE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_ADDRESS_COUNTRY_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_TABLE;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_SECURITY_QUESTION_BLOOD_BANK_TABLE;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.SECURITY_QUESTION_ID_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_SECURITY_QUESTION_ANS_COLUMN;
import static backend.authentication.blood_bank.database.BloodBankDatabaseConstant.BLOOD_BANK_REGISTRATION_STORED_PROCEDURE;

/**
 * {@code BloodBankRegistrationQueryBuilder} implements the
 * {@code BloodBankRegistrationQueryBuilderDAO} to provide a concrete
 * implementation for the blood bank registration.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankRegistrationQueryBuilder
    implements BloodBankRegistrationQueryBuilderDAO {

  // Static instance of this class
  private static BloodBankRegistrationQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankRegistrationQueryBuilder} instance.
   */
  private BloodBankRegistrationQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankRegistrationQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankRegistrationQueryBuilder} class.
   */
  public static BloodBankRegistrationQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankRegistrationQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to insert this blood bank in the table.
   *
   * @param bloodBank blood bank to insert in the table.
   *
   * @return string query to insert this blood bank in the table.
   */
  @Override
  public String insertBloodBankQuery(final BloodBank bloodBank) {
    return "INSERT INTO " + BLOOD_BANK_TABLE + "(" +
        BLOOD_BANK_NAME_COLUMN + ", " +
        BLOOD_BANK_EMAIL_COLUMN + ", " +
        BLOOD_BANK_PASSWORD_COLUMN + ", " +
        BLOOD_BANK_CONTACT_NUMBER_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_STREET_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_CITY_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_PROVINCE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_COUNTRY_COLUMN + ", " +
        BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN + ") " +
        "VALUES (" +
        "\"" + bloodBank.getName() + "\", " +
        "\"" + bloodBank.getEmail() + "\", " +
        "\"" + bloodBank.getPassword() + "\", " +
        "\"" + bloodBank.getContactNumber() + "\", " +
        "\"" + bloodBank.getAddressFirstLine() + "\", " +
        "\"" + bloodBank.getAddressStreet() + "\", " +
        "\"" + bloodBank.getAddressCity() + "\", " +
        "\"" + bloodBank.getAddressProvince() + "\", " +
        "\"" + bloodBank.getAddressZipCode() + "\", " +
        "\"" + bloodBank.getAddressCountry() + "\", " +
        bloodBank.isAccountActive() +
        ");";
  }

  /**
   * Gets the query to insert security questions and answers for this blood
   * bank in the table.
   *
   * @param bloodBankId   unique id of this blood bank.
   * @param securityQ1Id  security question 1 id of this blood bank.
   * @param securityQ1Ans security question 1 answer of this blood bank.
   * @param securityQ2Id  security question 2 id of this blood bank.
   * @param securityQ2Ans security question 2 answer of this blood bank.
   *
   * @return string query to insert security questions and answers for this
   * blood bank in the table.
   */
  @Override
  public String insertBloodBankSecurityQAQuery(final int bloodBankId,
                                               final int securityQ1Id,
                                               final String securityQ1Ans,
                                               final int securityQ2Id,
                                               final String securityQ2Ans) {
    return "INSERT INTO " + BLOOD_BANK_SECURITY_QUESTION_BLOOD_BANK_TABLE +
        "(" + BLOOD_BANK_ID_COLUMN + ", " +
        SECURITY_QUESTION_ID_COLUMN + ", " +
        BLOOD_BANK_SECURITY_QUESTION_ANS_COLUMN + ") " +
        "VALUES " +
        "(" + bloodBankId + ", " +
        securityQ1Id + ", \"" +
        securityQ1Ans + "\"), " +
        "(" + bloodBankId + ", " +
        securityQ2Id + ", \"" +
        securityQ2Ans + "\");";
  }

  /**
   * Gets the stored procedure query to insert this blood bank in the table.
   *
   * @param bloodBank     blood bank to insert in the table.
   * @param securityQ1Id  security question 1 id of this blood bank.
   * @param securityQ1Ans security question 1 answer of this blood bank.
   * @param securityQ2Id  security question 2 id of this blood bank.
   * @param securityQ2Ans security question 2 answer of this blood bank.
   *
   * @return string stored procedure query to insert this blood bank in the
   * table.
   */
  @Override
  public String storedProcedureForBloodBankRegistrationQuery(final BloodBank bloodBank,
                                                             final int securityQ1Id,
                                                             final String securityQ1Ans,
                                                             final int securityQ2Id,
                                                             final String securityQ2Ans) {
    return "CALL " +
        BLOOD_BANK_REGISTRATION_STORED_PROCEDURE + "(" +
        "\"" + bloodBank.getName() + "\", " +
        "\"" + bloodBank.getEmail() + "\", " +
        "\"" + bloodBank.getPassword() + "\", " +
        "\"" + bloodBank.getContactNumber() + "\", " +
        "\"" + bloodBank.getAddressFirstLine() + "\", " +
        "\"" + bloodBank.getAddressStreet() + "\", " +
        "\"" + bloodBank.getAddressCity() + "\", " +
        "\"" + bloodBank.getAddressProvince() + "\", " +
        "\"" + bloodBank.getAddressZipCode() + "\", " +
        "\"" + bloodBank.getAddressCountry() + "\", " +
        securityQ1Id + ", " +
        "\"" + securityQ1Ans + "\", " +
        securityQ2Id + ", " +
        "\"" + securityQ2Ans + "\"" +
        ");";
  }
}