package christmas.domain;

import static christmas.constants.EventDate.CHRISMAS;
import static christmas.constants.EventDate.END_DATE;
import static christmas.constants.EventDate.START_DATE;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Calendar {
    private final LocalDate reservationDate;
    private final DayOfWeek dayOfWeek;

    public Calendar(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        this.dayOfWeek = reservationDate.getDayOfWeek();
    }

    public boolean compareDayOfWeek(DayOfWeek otherDayOfweek) {
        return dayOfWeek.equals(otherDayOfweek);
    }

    public boolean isHoliday() {
        return hasDayOfWeekContains(SATURDAY, SUNDAY);
    }

    public boolean isSpecialDiscountDate() {
        return (hasDayOfWeekContains(SUNDAY)
                || reservationDate.equals(CHRISMAS.getDate()));
    }

    public boolean canEventPeriod() {
        return (reservationDate.isAfter(START_DATE.getMinusDays(1))
                && reservationDate.isBefore(END_DATE.getPlusDays(1)));
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }

}
