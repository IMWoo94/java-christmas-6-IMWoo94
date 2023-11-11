package christmas.exception;

import static christmas.constants.ErrorMessage.ERROR_PREFIX;

public class CustomException extends IllegalArgumentException {

    public CustomException(String s) {
        super(ERROR_PREFIX.getMessage() + s);
    }

    public CustomException(String message, Throwable cause) {
        super(ERROR_PREFIX.getMessage() + message, cause);
    }
}
