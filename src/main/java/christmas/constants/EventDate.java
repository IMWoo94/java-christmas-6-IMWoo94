package christmas.constants;

import java.time.LocalDate;

public enum EventDate {
    START_DATE(1),
    END_DATE(31),
    CHRISMAS(25);

    private final LocalDate date;

    EventDate(int date) {
        this.date = LocalDate.of(2023, 12, date);
    }

    public LocalDate getDate() {
        return date;
    }
}
