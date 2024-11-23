package exception;

/**
 * Exception for when an API call fails.
 */
public class APICallException extends Exception {

    public APICallException() {
        super();
    }

    public APICallException(String message) {
        super(message);
    }

    public APICallException(String message, Throwable cause) {
        super(message, cause);
    }

    public APICallException(Throwable cause) {
        super(cause);
    }

}
