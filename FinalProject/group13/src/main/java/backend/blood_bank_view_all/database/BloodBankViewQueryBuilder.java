package backend.blood_bank_view_all.database;

import backend.authentication.blood_bank.database.login_with_email.BloodBankLoginWithEmailQueryBuilder;

import static backend.blood_bank_view_all.database.BloodBankViewDatabaseConstant.*;

/**
 * {@code BloodBankViewQueryBuilder} implements the
 * {@code BloodBankViewQueryBuilderDAO} to provide a concrete
 * implementation for viewing all the blood banks.
 * This class is implemented using the Singleton Design Pattern.
 *
 * @author Dhrumil Amish Shah (B00857606)
 */
public final class BloodBankViewQueryBuilder
    implements BloodBankViewQueryBuilderDAO {

  // Static instance of this class.
  private static BloodBankViewQueryBuilder instance;

  /**
   * Constructs this {@code BloodBankViewQueryBuilder} instance.
   */
  private BloodBankViewQueryBuilder() {
    //Required empty private constructor
  }

  /**
   * Instantiates this {@code BloodBankViewQueryBuilder} class.
   * Lazy implementation instantiation.
   *
   * @return instance of this {@code BloodBankViewQueryBuilder} class.
   */
  public static BloodBankViewQueryBuilder getInstance() {
    if (instance == null) {
      instance = new BloodBankViewQueryBuilder();
    }
    return instance;
  }

  /**
   * Gets the query to view all the blood banks.
   *
   * @return {@code String} query to view all the blood banks.
   */
  @Override
  public String selectAllBloodBanksQuery() {
    return "SELECT " +
        BLOOD_BANK_ID_COLUMN + ", " +
        BLOOD_BANK_NAME_COLUMN + ", " +
        BLOOD_BANK_EMAIL_COLUMN + ", " +
        BLOOD_BANK_CONTACT_NUMBER_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_FIRST_LINE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_STREET_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_CITY_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_PROVINCE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_ZIP_CODE_COLUMN + ", " +
        BLOOD_BANK_ADDRESS_COUNTRY_COLUMN +
        " FROM " +
        BLOOD_BANK_TABLE + ";";
  }
}