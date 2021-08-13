package blood_donation_request.database;

import backend.blood_donation_request.database.view_previous_blood_donation_request.ViewPreviousBloodDonationRequestQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ViewPreviousBloodDonationRequestQueryBuilder class test suite")
public class ViewPreviousBloodDonationRequestQueryBuilderTest {
    @Test
    @DisplayName("Select previous blood donation query")
    public void selectPreviousBloodDonationQuery() {
        final int userId = 8;
        final ViewPreviousBloodDonationRequestQueryBuilder viewPreviousBloodDonationRequestQueryBuilder = ViewPreviousBloodDonationRequestQueryBuilder.getInstance();
        final String actualSelectQuery = viewPreviousBloodDonationRequestQueryBuilder.selectPreviousBloodDonationQuery(userId);
        final String expectedSelectQuery = "SELECT request_id, user_id, request_date, status_change_date, status, certificate_id FROM blood_donation_request WHERE user_id = 8 ORDER BY request_id DESC LIMIT 1;";
        Assertions.assertEquals(expectedSelectQuery, actualSelectQuery, "Incorrect Select Blood Donation Query");
    }

}
