package christmas.domain;

import static christmas.constants.EventDate.CHRISTMAST;
import static christmas.constants.EventDate.END_DATE;
import static christmas.constants.EventDate.START_DATE;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class ReservationDate {
    private final LocalDate reservationDate;
    private final DayOfWeek dayOfWeek;
    private final int ONE_DAY = 1;

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
        return hasBetweenPeriod(START_DATE.getMinusDays(ONE_DAY), END_DATE.getPlusDays(ONE_DAY));
    }

    public int getEventAccumulateDays() {
        int days = 0;
        if (hasBetweenPeriod(START_DATE.getMinusDays(ONE_DAY), CHRISTMAST.getPlusDays(ONE_DAY))) {
            Period period = Period.between(START_DATE.getDate(), reservationDate);
            return period.getDays();
        }
        return days;
    }

    private boolean hasBetweenPeriod(LocalDate beginDate, LocalDate endDate) {
        return reservationDate.isAfter(beginDate)
                && reservationDate.isBefore(endDate);
    }

    public boolean isChristmastDate() {
        return reservationDate.isEqual(CHRISTMAST.getDate());
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }

}
