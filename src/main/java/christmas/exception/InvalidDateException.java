package christmas.exception;

public class InvalidDateException extends CustomException {
    public InvalidDateException(String s) {
        super(s);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
