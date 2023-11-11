package christmas.exception;

public class InputException extends CustomException {
    public InputException(String s) {
        super(s);
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
    }
}
