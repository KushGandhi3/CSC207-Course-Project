package exception;

/**
 * Exception for when the data for recent cities cannot be loaded.
 */
public class RecentCitiesDataException extends Exception {

    public RecentCitiesDataException(String message) {
        super(message);
    }

    public RecentCitiesDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecentCitiesDataException(Throwable cause) {
        super(cause);
    }

}
