package backend.authentication.blood_bank.database.login_with_email;

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
 * {@code BloodBankLoginWithEmailQueryBuilder} implements the
 * {@code BloodBankLoginWithEmailQueryBuilderDAO} to provide a concrete
 * implementation for the blood bank login with email.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankLoginWithEmailQueryBuilder
    implements BloodBankLoginWithEmailQueryBuilderDAO {

  // Static instance of this class
  private static BloodBankLoginWithEmailQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankLoginWithEmailQueryBuilder} instance.
   */
  private BloodBankLoginWithEmailQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankLoginWithEmailQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankLoginWithEmailQueryBuilder} class.
   */
  public static BloodBankLoginWithEmailQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankLoginWithEmailQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to select blood bank by email.
   *
   * @param email email of this blood bank.
   *
   * @return string query to select blood bank by email.
   */
  @Override
  public String selectBloodBankByEmailQuery(final String email) {
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
        BLOOD_BANK_EMAIL_COLUMN + "=\"" + email + "\";";
  }
}