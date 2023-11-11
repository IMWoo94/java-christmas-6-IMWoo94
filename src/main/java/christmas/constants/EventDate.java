package christmas.constants;

public enum EventDate {
    YEAR(2023),
    MONTH(12),
    DAY(1),
    CHRISMAS(25);

    private final int value;

    EventDate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
