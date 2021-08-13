package backend.authentication.blood_bank.database.login_with_contact_number;

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

/**
 * {@code BloodBankLoginWithContactNumberQueryBuilder} implements the
 * {@code BloodBankLoginWithContactNumberQueryBuilderDAO} to provide a concrete
 * implementation for the blood bank login with email.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankLoginWithContactNumberQueryBuilder
    implements BloodBankLoginWithContactNumberQueryBuilderDAO {

  // Static instance of this class
  private static BloodBankLoginWithContactNumberQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankLoginWithContactNumberQueryBuilder} instance.
   */
  private BloodBankLoginWithContactNumberQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankLoginWithContactNumberQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankLoginWithContactNumberQueryBuilder} class.
   */
  public static BloodBankLoginWithContactNumberQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankLoginWithContactNumberQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select blood bank by contact number.
   *
   * @param contactNumber contact number of this blood bank.
   *
   * @return string query to select blood bank by email.
   */
  @Override
  public String selectBloodBankByContactNumberQuery(final String contactNumber) {
    return "SELECT " +
        BLOOD_BANK_ID_COLUMN + ", " +
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
        BlOOD_BANK_ACCOUNT_ACTIVE_COLUMN +
        " FROM " +
        BLOOD_BANK_TABLE +
        " WHERE " +
        BLOOD_BANK_CONTACT_NUMBER_COLUMN + "=\"" + contactNumber + "\";";
  }
}