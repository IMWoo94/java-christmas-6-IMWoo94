package christmas.exception;

public class InputDateException extends CustomException {
    public InputDateException(String s) {
        super(s);
    }

    public InputDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
