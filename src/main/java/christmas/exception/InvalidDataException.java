package christmas.exception;

import static christmas.constants.ErrorMessage.INVALID_DATA;

public class InvalidDataException extends CustomException {
    private InvalidDataException(String s) {
        super(s);
    }

    private InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InvalidDataException from(String message) {
        return new InvalidDataException(INVALID_DATA.getFormatMessage(message));
    }

    public static InvalidDataException of(String message, Throwable cause) {
        return new InvalidDataException(INVALID_DATA.getFormatMessage(message), cause);
    }

}
