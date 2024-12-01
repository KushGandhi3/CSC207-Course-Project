package exception;

/**
 * Exception for when an invalid location is inputted.
 */
public class InvalidLocationException extends Exception {
    public InvalidLocationException(String message) {
        super(message);
    }

    public InvalidLocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
