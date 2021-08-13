package backend.admin_blood_receiver_request.exception;

/**
 * {@code BloodReceiverRequestStatusException} is thrown when error occurs
 * during while changing status of blood receiver.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodReceiverRequestStatusException} is a checked exception.
 */
public class BloodReceiverRequestStatusException extends Exception {
    private final String errorMessage;

    /**
     * Construct this {@code BloodReceiverRequestStatusException} instance.
     *
     * @param errorMessage generates error message.
     */
    public BloodReceiverRequestStatusException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message of this exception.
     *
     * @return the error message of this exception.
     * */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets the string representation of this exception.
     *
     * @return string representation of this exception.
     */
    @Override
    public String toString() {
        return "BloodDonationRequestException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
