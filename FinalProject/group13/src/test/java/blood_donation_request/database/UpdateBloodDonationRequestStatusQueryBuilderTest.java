package blood_donation_request.database;

import backend.blood_donation_request.database.update_blood_donation_request_status.UpdateBloodDonationRequestStatusQueryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("UpdateBloodDonationRequestStatusQueryBuilder class test suite")
public class UpdateBloodDonationRequestStatusQueryBuilderTest {

    @Test
    @DisplayName("Update blood donation status to Request from Active")
    public void updateRequestStatusQuery() {
        final int userId = 8;
        final String STATUS_ACTIVE = "active";
        final String STATUS_REQUEST = "request";
        final UpdateBloodDonationRequestStatusQueryBuilder updateBloodDonationRequestStatusQueryBuilder = UpdateBloodDonationRequestStatusQueryBuilder.getInstance();
        final String actualUpdateQuery = updateBloodDonationRequestStatusQueryBuilder.updateBloodDonationRequestStatusQuery(userId);
        final String expectedUpdateQuery = "UPDATE blood_donation_request SET status = \"" + STATUS_REQUEST + "\", status_change_date = \"" + String.valueOf(LocalDate.now()) + "\" WHERE user_id = " + userId + " AND status = \"" + STATUS_ACTIVE + "\";";
        Assertions.assertEquals(expectedUpdateQuery, actualUpdateQuery, "Incorrect Update Blood Donation Query");
    }
}
