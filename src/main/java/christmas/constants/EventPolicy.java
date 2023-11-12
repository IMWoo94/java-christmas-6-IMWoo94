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

    public int getDiscountAmount(int count) {
        if (this.equals(SPECIAL_DISCOUNT)
                || this.equals(GIVEAWAY_EVENT)) {
            return discount;
        }
        if (this.equals(CHRISTMAS_D_DAY_DISCOUNT)) {
            return discount + 100 * count;
        }
        return discount * count;
    }
}
