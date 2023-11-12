package christmas.domain;

import static christmas.constants.EventDate.CHRISTMAST;
import static christmas.constants.EventDate.START_DATE;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.constants.EventDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

public class ReservationDate {
    private final LocalDate reservationDate;
    private final DayOfWeek dayOfWeek;

    public ReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        this.dayOfWeek = reservationDate.getDayOfWeek();
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public boolean compareDayOfWeek(DayOfWeek otherDayOfweek) {
        return dayOfWeek.equals(otherDayOfweek);
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
        return EventDate.isSpecialPeriod(reservationDate);
    }

    public boolean canEventPeriod() {
        return EventDate.canEventPeriod(reservationDate);
    }

    public Optional<Integer> getEventAccumulateDays() {
        if (EventDate.canChristmastPeriod(reservationDate)) {
            return Optional.of(START_DATE.getAccumulateDays(reservationDate));
        }
        return Optional.empty();
    }

    public boolean isChristmastDate() {
        return CHRISTMAST.dateEquals(reservationDate);
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }

}
