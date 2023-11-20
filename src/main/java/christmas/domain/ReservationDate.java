package christmas.domain;

import static christmas.constants.biz.EventDate.CHRISTMAST;
import static christmas.constants.biz.EventDate.START_DATE;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.constants.biz.EventDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

public class ReservationDate {
    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    public ReservationDate(LocalDate date) {
        this.date = date;
        this.dayOfWeek = date.getDayOfWeek();
    }

    public LocalDate getReservationDate() {
        return date;
    }

    public boolean isHoliday() {
        return hasDayOfWeekContains(SATURDAY, SUNDAY);
    }

    public boolean isWeekday() {
        return hasDayOfWeekContains(MONDAY,
                TUESDAY,
                WEDNESDAY,
                THURSDAY,
                FRIDAY);
    }

    public boolean isSpecialDiscountDate() {
        return EventDate.isSpecialPeriod(date);
    }

    public boolean canEventPeriod() {
        return EventDate.canEventPeriod(date);
    }

    public Optional<Integer> getEventAccumulateDays() {
        if (EventDate.canChristmastPeriod(date)) {
            return Optional.of(START_DATE.getAccumulateDays(date));
        }
        return Optional.empty();
    }

    public boolean isChristmastDate() {
        return CHRISTMAST.dateEquals(date);
    }

    private boolean compareDayOfWeek(DayOfWeek otherDayOfweek) {
        return dayOfWeek.equals(otherDayOfweek);
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public int getDays() {
        return date.getDayOfMonth();
    }

}
