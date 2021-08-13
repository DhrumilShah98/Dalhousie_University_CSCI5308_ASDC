package blood_bank_profile_management.database;

import backend.authentication.blood_bank.model.BloodBank;
import backend.blood_bank_profile_management.database.update_blood_bank_profile_details.UpdateBloodBankProfileDetailsQueryBuilder;
import backend.blood_bank_profile_management.database.update_blood_bank_profile_details.UpdateBloodBankProfileDetailsQueryBuilderDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UpdateBloodBankDetailsQueryBuilder class test suite")
public class UpdateBloodBankDetailsQueryBuilderTest {

  @Test
  @DisplayName("Blood Bank Profile Update Query")
  public void updateBloodBankProfileDetailsQuery() {

    final BloodBank bloodBank = new BloodBank(
        4,
        "Oxford Blood Bank",
        "cooper@gmail.com",
        "cooperbloodbank@12345",
        "9028832245",
        "Oasis Park",
        "Garodia Nagar",
        "Mumbai",
        "Maharashtra",
        "400077",
        "India",
        true
    );

    final UpdateBloodBankProfileDetailsQueryBuilder updateBloodBankProfileDetailsQueryBuilder = UpdateBloodBankProfileDetailsQueryBuilder.getInstance();
    final String actualUpdateBloodBankQuery = updateBloodBankProfileDetailsQueryBuilder.updateBloodBankProfileDetailsQuery(bloodBank);
    final String expectedUpdateBloodBankQuery = "UPDATE blood_bank SET name=\"Oxford Blood Bank\", address_first_line=\"Oasis Park\", address_street=\"Garodia Nagar\", address_city=\"Mumbai\", address_province=\"Maharashtra\", address_zip_code=\"400077\", address_country=\"India\" WHERE blood_bank_id=4;";
    Assertions.assertEquals(expectedUpdateBloodBankQuery, actualUpdateBloodBankQuery, "Incorrect Update Blood Bank Query.");
  }
}
