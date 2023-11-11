package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATA;

import christmas.exception.InvalidDateException;
import java.text.NumberFormat;
import java.text.ParseException;

public class InputDateValidator {

    private static final String DATE = "날짜";

    private InputDateValidator() {
    }

    public static void validateInputDate(String inputDate) {
        validateNull(inputDate);
        validateEmpty(inputDate);
        validateNumberType(inputDate);
    }

    private static void validateNull(String inputDate) {
        if (inputDate == null) {
            throw new InvalidDateException(INVALID_DATA.getFormatMessage(DATE));
        }
    }

    private static void validateEmpty(String inputDate) {
        if (inputDate.isBlank()) {
            throw new InvalidDateException(INVALID_DATA.getFormatMessage(DATE));
        }
    }

    private static void validateNumberType(String inputDate) {
        try {
            NumberFormat.getIntegerInstance().parse(inputDate);
        } catch (ParseException e) {
            throw new InvalidDateException(INVALID_DATA.getFormatMessage(DATE), e);
        }
    }
}
