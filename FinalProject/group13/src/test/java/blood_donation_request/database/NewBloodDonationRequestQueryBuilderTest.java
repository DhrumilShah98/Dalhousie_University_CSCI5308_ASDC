package blood_donation_request.database;

import backend.blood_donation_request.database.new_blood_donation_request.NewBloodDonationRequestQueryBuilder;
import backend.blood_donation_request.model.BloodDonationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("NewBloodDonationRequestQueryBuilder class test suite")
public class NewBloodDonationRequestQueryBuilderTest {

    @Test
    @DisplayName("Insert a New Blood Donation Request")
    public void insertBloodDonationRequestQuery() {
        final BloodDonationRequest bloodDonationRequest = new BloodDonationRequest(
                9,
                "String.valueOf(LocalDate.now())",
                "String.valueOf(LocalDate.now())",
                "active",
                ""
        );
        final NewBloodDonationRequestQueryBuilder newBloodDonationRequestQueryBuilder = NewBloodDonationRequestQueryBuilder.getInstance();
        final String actualSelectActiveQuery = newBloodDonationRequestQueryBuilder.insertNewBloodDonationRequestQuery(bloodDonationRequest);
        final String expectedSelectActiveQuery = "INSERT INTO blood_donation_request(user_id, request_date, status_change_date, status, certificate_id)" +
                " VALUES (\"9\", \"" + String.valueOf(LocalDate.now()) + "\", \"" + String.valueOf(LocalDate.now()) + "\", \"active\", \"\");";
        Assertions.assertEquals(expectedSelectActiveQuery, actualSelectActiveQuery, "Incorrect Insert Blood Donation Query");
    }
}
