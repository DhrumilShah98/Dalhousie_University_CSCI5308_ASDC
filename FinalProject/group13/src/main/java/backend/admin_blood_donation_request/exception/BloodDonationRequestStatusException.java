package backend.admin_blood_donation_request.exception;

/**
 * {@code BloodDonationRequestStatusException} is thrown when error occurs
 * during while changing status of blood donor.
 * This class extends the {@code Exception} class.
 * Hence {@code BloodDonationRequestStatusException} is a checked exception.
 */
public class BloodDonationRequestStatusException extends Exception{
    private final String errorMessage;


    /**
     * Construct this {@code BloodDonationRequestStatusException} instance.
     *
     * @param errorMessage generates error message.
     */
    public BloodDonationRequestStatusException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message of this exception.
     *
     * @return the error message of this exception.
     */
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
