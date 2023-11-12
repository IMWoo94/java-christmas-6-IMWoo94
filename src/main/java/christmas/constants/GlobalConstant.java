package christmas.constants;

public enum GlobalConstant {
    DATE("날짜"),
    ORDER("주문");
    private final String value;

    GlobalConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
