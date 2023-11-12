package christmas.constants;

import static christmas.constants.GlobalConstant.MONTH;
import static christmas.constants.GlobalConstant.YEAR;

import java.time.LocalDate;

public enum EventDate {
    START_DATE(YEAR, MONTH, 1),
    END_DATE(YEAR, MONTH, 31),
    CHRISTMAST(YEAR, MONTH, 25);

    private final LocalDate date;

    EventDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getMinusDays(long days) {
        return date.minusDays(days);
    }

    public LocalDate getPlusDays(long days) {
        return date.plusDays(days);
    }

}
