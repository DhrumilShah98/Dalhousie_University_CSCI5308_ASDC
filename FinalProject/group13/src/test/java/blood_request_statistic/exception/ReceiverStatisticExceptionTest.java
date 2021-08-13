package blood_request_statistic.exception;

import backend.blood_request_statistic.exception.ReceiverStatisticsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Receiver request Statistic Tests")
public class ReceiverStatisticExceptionTest {

  @Test
  @DisplayName("Blood receiver statistics exception getErrorMessage() test")
  public void getErrorMessageTest() {
    final ReceiverStatisticsException receiverStatisticsException = new ReceiverStatisticsException("Failed to load the blood receiver statistics. ");
    Assertions.assertEquals("Failed to load the blood receiver statistics. ", receiverStatisticsException.getErrorMessage());
  }

  @Test
  @DisplayName("Blood receiver statistics exception toString() test")
  public void toStringTest() {
    final ReceiverStatisticsException receiverStatisticsException = new ReceiverStatisticsException("Failed to load the blood receiver statistics.");
    Assertions.assertEquals("ReceiverStatisticsException{errorMessage='Failed to load the blood receiver statistics.'}", receiverStatisticsException.toString());
  }
}
