package active_blood_donor_request.database;

import backend.active_blood_donor_request.database.view_active_blood_donor_request.ViewActiveBloodDonorRequestQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ViewActiveBloodDonorRequestQueryBuilder class test suite")
public class ViewActiveBloodDonorRequestQueryBuilderTest {

  @Test
  @DisplayName("Select active blood donor request query ")
  public void selectActiveBloodDonorRequestQuery() {
    final String STATUS_ACTIVE = "active";
    final ViewActiveBloodDonorRequestQueryBuilder viewActiveBloodDonorRequestQueryBuilder = ViewActiveBloodDonorRequestQueryBuilder.getInstance();
    final String actualSelectQuery = viewActiveBloodDonorRequestQueryBuilder.selectActiveBloodDonorRequestQuery();
    final String expectedSelectQuery = "SELECT u.user_id, bdr.request_id, u.first_name, u.last_name, u.date_of_birth, u.blood_group, u.email, u.contact_number, u.address_first_line, u.address_street, u.address_city, u.address_province, u.address_zip_code, u.address_country FROM user AS u, blood_donation_request AS bdr WHERE u.user_id = bdr.user_id AND bdr.status = \"" + STATUS_ACTIVE + "\";";
    Assertions.assertEquals(expectedSelectQuery, actualSelectQuery, "Incorrect Select Active Blood Donor Request Query.");
  }
}
