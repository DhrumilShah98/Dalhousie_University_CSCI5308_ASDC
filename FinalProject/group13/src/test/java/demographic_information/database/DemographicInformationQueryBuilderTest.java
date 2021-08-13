package demographic_information.database;

import backend.demographic_information.database.DemographicInformationQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Demographic information query test suite")
public class DemographicInformationQueryBuilderTest {

  @Test
  @DisplayName("User Profile Update Query")
  public void fetchActiveUserInProvince() {

    final DemographicInformationQueryBuilder demographicInformationQueryBuilder =
        DemographicInformationQueryBuilder.getInstance();
    final String provinceName = "Ontario";
    final String actualActiveUserInProvinceQuery =
        demographicInformationQueryBuilder.fetchActiveUserInProvince(provinceName);
    final String expectedActiveUserInProvinceQuery = "SELECT COUNT(user_id) FROM user WHERE address_province = \"Ontario\";";
    Assertions.assertEquals(expectedActiveUserInProvinceQuery, actualActiveUserInProvinceQuery,
        "Incorrect fetch active user Query.");
  }

  @Test
  @DisplayName("User Profile Update Query")
  public void fetchActiveBloodBankInProvince() {

    final DemographicInformationQueryBuilder demographicInformationQueryBuilder =
        DemographicInformationQueryBuilder.getInstance();
    final String provinceName = "Ontario";
    final String actualActiveBloodBankInProvinceQuery =
        demographicInformationQueryBuilder.fetchActiveBloodBankInProvince(provinceName);
    final String expectedActiveBloodBankInProvinceQuery = "SELECT COUNT(blood_bank_id) FROM blood_bank WHERE address_province = \"Ontario\";";
    Assertions.assertEquals(expectedActiveBloodBankInProvinceQuery, actualActiveBloodBankInProvinceQuery,
        "Incorrect fetch active blood bank Query.");
  }

}
