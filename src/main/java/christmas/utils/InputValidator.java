package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATE;

import christmas.exception.InputException;
import java.text.NumberFormat;
import java.text.ParseException;

public class InputValidator {
    private InputValidator() {
    }

    public static void validateNull(String inputDate) {
        if (inputDate == null) {
            throw new InputException(INVALID_DATE.getMessage());
        }
    }

    public static void validateEmpty(String inputDate) {
        if (inputDate.isBlank()) {
            throw new InputException(INVALID_DATE.getMessage());
        }
    }

    public static void validateNumberType(String inputDate) {
        try {
            NumberFormat.getIntegerInstance().parse(inputDate);
        } catch (ParseException e) {
            throw new InputException(INVALID_DATE.getMessage(), e);
        }
    }
}
