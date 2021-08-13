package blood_donation_statistics.database;

import backend.blood_donation_statistic.database.blood_donation_statistics.BloodDonationStatisticsQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodDonationStatisticsQueryBuilder class test suite")
public class BloodDonationStatisticsQueryBuilderTest {

  @Test
  @DisplayName("Select active blood donor request query ")
  public void selectActiveBloodDonorRequestQuery() {
    final String STATUS_ACTIVE = "active";
    final BloodDonationStatisticsQueryBuilder bloodDonationStatisticsQueryBuilder = BloodDonationStatisticsQueryBuilder.getInstance();
    final String actualSelectQuery = bloodDonationStatisticsQueryBuilder.selectBloodDonationStatisticsQuery();
    final String expectedSelectQuery = "SELECT u.user_id, bdr.request_id, u.first_name, u.last_name, u.date_of_birth, u.blood_group, u.email, u.contact_number, u.address_first_line, u.address_street, u.address_city, u.address_province, u.address_zip_code, u.address_country, bdr.status_change_date, bdr.status FROM user AS u, blood_donation_request AS bdr WHERE u.user_id = bdr.user_id;";
    Assertions.assertEquals(expectedSelectQuery, actualSelectQuery, "Incorrect Select Active Blood Donor Query.");
  }
}
