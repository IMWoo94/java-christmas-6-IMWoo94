package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

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
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }


}
