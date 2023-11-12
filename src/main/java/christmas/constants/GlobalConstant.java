package christmas.constants;

public enum GlobalConstant {
    DATE("날짜"),
    ORDER("주문"),
    APPETIZER("애피타이저"),
    BEVERAGE("음료"),
    MAIN("메인"),
    DESSERT("디저트");
    private final String value;

    GlobalConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
