package christmas.constants;

public enum MenuType {
    APPETIZER("애피타이저"),
    BEVERAGE("음료"),
    MAIN("메인"),
    DESSERT("디저트");
    private final String type;

    MenuType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
