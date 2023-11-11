package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATE;

import christmas.exception.InputDateException;
import java.text.NumberFormat;
import java.text.ParseException;

public class Parser {
    private Parser() {
    }

    public static int StringToInt(String data) {
        try {
            Number parse = NumberFormat.getIntegerInstance().parse(data);
            return parse.intValue();
        } catch (ParseException e) {
            throw new InputDateException(INVALID_DATE.getMessage(), e);
        }
    }
}
