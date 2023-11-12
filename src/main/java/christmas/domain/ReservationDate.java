package christmas.domain;

import static christmas.constants.EventDate.CHRISTMAST;
import static christmas.constants.EventDate.START_DATE;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import christmas.constants.EventDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class ReservationDate {
    private final LocalDate reservationDate;
    private final DayOfWeek dayOfWeek;

    public ReservationDate(LocalDate reservationDate) {
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
                || reservationDate.equals(CHRISTMAST.getDate()));
    }

    public boolean canEventPeriod() {
        return EventDate.canEventPeriod(reservationDate);
    }

    public int getEventAccumulateDays() {
        int days = 0;
        if (EventDate.canChristmastPeriod(reservationDate)) {
            return START_DATE.getAccumulateDays(reservationDate);
        }
        return days;
    }

    public boolean isChristmastDate() {
        return CHRISTMAST.dateEquals(reservationDate);
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }

}
