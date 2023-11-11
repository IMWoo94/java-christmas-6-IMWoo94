package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATE;

import christmas.exception.InputException;
import java.text.NumberFormat;
import java.text.ParseException;

public class InputValidator {
    private InputValidator() {
    }

    public static void validateNumberType(String inputData) {
        try {
            NumberFormat.getIntegerInstance().parse(inputData);
        } catch (ParseException e) {
            throw new InputException(INVALID_DATE.getMessage(), e);
        }
    }
}
