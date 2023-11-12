package christmas.constants.biz;

public enum EventPolicy {

    CHRISTMAS_D_DAY_DISCOUNT(1_000, "크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT(2_023, "평일 할인"),
    WEEKEND_DISCOUNT(2_023, "주말 할인"),
    SPECIAL_DISCOUNT(1_000, "특별 할인"),
    GIFT_EVENT(0, "증정 이벤트");

    private final int discount;
    private final String eventName;

    EventPolicy(int discount, String eventName) {
        this.discount = discount;
        this.eventName = eventName;
    }

    public int getDiscount() {
        return discount;
    }

    public String getEventName() {
        return eventName;
    }

    public int getDiscountAmount(int count) {
        if (this.equals(SPECIAL_DISCOUNT)) {
            return discount;
        }
        if (this.equals(CHRISTMAS_D_DAY_DISCOUNT)) {
            return discount + 100 * count;
        }
        return discount * count;
    }

    public int getGiftBenefitAmount() {
        return VariousMenu.getGifts()
                .stream()
                .mapToInt(VariousMenu::giftAmount)
                .sum();
    }

    public static boolean checkParticipationConditions(int amount) {
        return amount >= 10_000;
    }

    public static boolean checkGiveawayEventConditions(int amount) {
        return amount >= 120_000;
    }
}
