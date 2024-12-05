package exception;

/**
 * Exception for when an API call fails.
 */
public class ApiCallException extends Exception {

    public ApiCallException(String message) {
        super(message);
    }

    public ApiCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiCallException(Throwable cause) {
        super(cause);
    }

}
