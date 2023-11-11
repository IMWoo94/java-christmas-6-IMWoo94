package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATE;
import static christmas.constants.EventDate.MONTH;
import static christmas.constants.EventDate.YEAR;

import christmas.exception.InputDateException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {
    private Parser() {
    }

    public static LocalDate StringToLocalDate(String data) {
        try {
            Number parse = NumberFormat.getIntegerInstance().parse(data);
            return LocalDate.of(YEAR.getValue(), MONTH.getValue(), parse.intValue());
        } catch (ParseException | DateTimeException e) {
            throw new InputDateException(INVALID_DATE.getMessage(), e);
        }
    }
}
