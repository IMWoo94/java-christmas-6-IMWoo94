package christmas.constants;

import static christmas.constants.GlobalConstant.MONTH;
import static christmas.constants.GlobalConstant.YEAR;

import java.time.LocalDate;
import java.time.Period;

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

    public boolean dateEquals(LocalDate otherDate) {
        return date.equals(otherDate);
    }

    public int getAccumulateDays(LocalDate otherDate) {
        Period period = Period.between(date, otherDate);
        return period.getDays();
    }

    public static boolean canEventPeriod(LocalDate date) {
        return hasBetweenPeriod(date,
                START_DATE.getMinusDays(1),
                END_DATE.getPlusDays(1));
    }

    public static boolean canChristmastPeriod(LocalDate date) {
        return hasBetweenPeriod(date,
                START_DATE.getMinusDays(1),
                CHRISTMAST.getPlusDays(1));
    }


    private static boolean hasBetweenPeriod(LocalDate date, LocalDate beginDate, LocalDate endDate) {
        return date.isAfter(beginDate)
                && date.isBefore(endDate);
    }


}
