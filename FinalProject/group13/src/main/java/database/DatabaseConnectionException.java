package database;

public class DatabaseConnectionException extends Exception {
    private final String errorMessage;
    private final Throwable error;

    public DatabaseConnectionException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        this.errorMessage = errorMessage;
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "DatabaseConnectionException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", error=" + error +
                '}';
    }
}