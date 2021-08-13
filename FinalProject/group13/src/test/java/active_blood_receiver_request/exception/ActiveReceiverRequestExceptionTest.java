package active_blood_receiver_request.exception;

import backend.active_blood_receiver_request.exception.ActiveReceiverRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ActiveReceiverRequestExceptionTest {

        @Test
        @DisplayName("Active blood donor request exception getErrorMessage() test")
        public void getErrorMessageTest() {
            final ActiveReceiverRequestException activeReceiverRequestException = new ActiveReceiverRequestException("No active blood receiver requests found. ");
            Assertions.assertEquals("No active blood receiver requests found. ", activeReceiverRequestException.getErrorMessage());
        }

        @Test
        @DisplayName("Active blood donor request exception toString() test")
        public void toStringTest() {
            final ActiveReceiverRequestException activeReceiverRequestException = new ActiveReceiverRequestException("No active blood receiver requests found.");
            Assertions.assertEquals("ActiveReceiverRequestException{errorMessage='No active blood receiver requests found.'}", activeReceiverRequestException.toString());
        }
}
