package christmas.constants.biz;

public enum PreviewType {

    ORDER_MENU("<주문 메뉴>", "%s %s개"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>", "%,d원"),
    GIFT_MENU("<증정 메뉴>", "%s %s개"),
    BENEFIT_DETAILS("<혜택 내역>", "%s: %,d원"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>", "%,d원"),
    TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>", "%,d원"),
    EVENT_BADGE("<12월 이벤트 배지>", "%s");

    private final String type;
    private final String format;

    PreviewType(String type, String format) {
        this.type = type;
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String getFormatMessage(Object... args) {
        return String.format(format, args);
    }
}
