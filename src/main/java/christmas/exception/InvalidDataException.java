package christmas.exception;

public class InvalidDataException extends CustomException {
    public InvalidDataException(String s) {
        super(s);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
