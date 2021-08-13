package blood_bank_rating_by_user.database;

import authentication.blood_bank.database.BloodBankQueryBuilderTest;
import backend.blood_bank_rating_by_user.database.BloodBankRatingQueryBuilder;
import backend.blood_bank_rating_by_user.model.BloodBankRating;
import backend.blood_donation_request.database.new_blood_donation_request.NewBloodDonationRequestQueryBuilder;
import backend.blood_donation_request.model.BloodDonationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BloodBankRatingQueryBuilderTest {

    @Test
    @DisplayName("Insert a blood bank rating")
    public void insertBloodBankRatingQuery() {
        final BloodBankRating bloodBankRating = new BloodBankRating(
                1,
                1,
                "Great staff",
                4.3f,
                23,
                "2021-07-23"
        );
        final BloodBankRatingQueryBuilder bloodBankRatingQueryBuilder = BloodBankRatingQueryBuilder.getInstance();
        final String actualBloodBankRatingQuery = bloodBankRatingQueryBuilder.insertBloodBankRatingQuery(bloodBankRating);
        final String expectedBloodBankRatingQuery = "INSERT INTO blood_bank_rating (blood_bank_id, user_id, comment, star, age_during_rating, created_at) VALUES (1, 1, \"Great staff\", 4.3, 23, \"2021-07-23\");";
        Assertions.assertEquals(expectedBloodBankRatingQuery, actualBloodBankRatingQuery, "Incorrect method implementation: insertBloodBankRatingQuery");
    }
}
