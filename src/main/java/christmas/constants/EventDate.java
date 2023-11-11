package christmas.constants;

import java.time.LocalDate;

public enum EventDate {
    START_DATE(2023, 12, 1),
    END_DATE(2023, 12, 31),
    CHRISTMAST(2023, 12, 25);
 
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
