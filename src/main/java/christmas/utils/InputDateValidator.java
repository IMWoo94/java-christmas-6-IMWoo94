package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATE;

import christmas.exception.InputDateException;
import java.text.NumberFormat;
import java.text.ParseException;

public class InputDateValidator {
    private InputDateValidator() {
    }

    public static void validateInputDate(String inputDate) {
        validateNull(inputDate);
        validateEmpty(inputDate);
        validateNumberType(inputDate);
    }

    private static void validateNull(String inputDate) {
        if (inputDate == null) {
            throw new InputDateException(INVALID_DATE.getMessage());
        }
    }

    private static void validateEmpty(String inputDate) {
        if (inputDate.isBlank()) {
            throw new InputDateException(INVALID_DATE.getMessage());
        }
    }

    private static void validateNumberType(String inputDate) {
        try {
            NumberFormat.getIntegerInstance().parse(inputDate);
        } catch (ParseException e) {
            throw new InputDateException(INVALID_DATE.getMessage(), e);
        }
    }
}
