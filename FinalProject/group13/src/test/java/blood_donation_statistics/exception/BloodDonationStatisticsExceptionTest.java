package blood_donation_statistics.exception;

import backend.blood_donation_statistic.exception.BloodDonationStatisticsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BloodDonationStatisticsException class test suite")
public class BloodDonationStatisticsExceptionTest {

  @Test
  @DisplayName("Blood donation statistics exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final BloodDonationStatisticsException bloodDonationStatisticsException = new BloodDonationStatisticsException("Failed to load the blood donation statistics. ");
    Assertions.assertEquals("Failed to load the blood donation statistics. ", bloodDonationStatisticsException.getErrorMessage());
  }

  @Test
  @DisplayName("Blood donation statistics exception toString() test")
  public void toStringTest() {
    final BloodDonationStatisticsException bloodDonationStatisticsException = new BloodDonationStatisticsException("Failed to load the blood donation statistics.");
    Assertions.assertEquals("BloodDonationStatisticsException{errorMessage='Failed to load the blood donation statistics.'}", bloodDonationStatisticsException.toString());
  }
}
