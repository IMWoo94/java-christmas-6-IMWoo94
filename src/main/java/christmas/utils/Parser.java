package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATA;

import christmas.exception.InvalidDataException;
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
            return LocalDate.of(2023, 12, parse.intValue());
        } catch (ParseException | DateTimeException e) {
            throw new InvalidDataException(INVALID_DATA.getFormatMessage("날짜"), e);
        }
    }
}
