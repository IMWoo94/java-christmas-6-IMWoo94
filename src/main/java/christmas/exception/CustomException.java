package christmas.exception;

import static christmas.constants.ErrorMessage.ERROR_PRIFIX;

public class CustomException extends IllegalArgumentException {

    public CustomException(String s) {
        super(ERROR_PRIFIX.getMessage() + s);
    }

    public CustomException(String message, Throwable cause) {
        super(ERROR_PRIFIX.getMessage() + message, cause);
    }
}
