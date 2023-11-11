package christmas.domain;

import static christmas.constants.EventDate.CHRISMAS;

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
        return this.dayOfWeek.equals(otherDayOfweek);
    }

    public boolean isHoliday() {
        return hasDayOfWeekContains(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    }

    public boolean isSpecialDiscountDate() {
        return hasDayOfWeekContains(DayOfWeek.SUNDAY) || reservationDate.equals(CHRISMAS.getDate());
    }

    private boolean hasDayOfWeekContains(DayOfWeek... otherDayOfWeek) {
        return Arrays.stream(otherDayOfWeek)
                .anyMatch(this::compareDayOfWeek);
    }


}
