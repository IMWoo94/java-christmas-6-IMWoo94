package christmas.constants;

import static christmas.constants.VariousMenu.CHAMPAGNE;

public enum EventPolicy {

    CHRISTMAS_D_DAY_DISCOUNT(1_000),
    WEEKDAY_DISCOUNT(2_023),
    WEEKEND_DISCOUNT(2_023),
    SPECIAL_DISCOUNT(1_000),
    GIVEAWAY_EVENT(CHAMPAGNE.getPrice());

    private final int discount;

    EventPolicy(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
