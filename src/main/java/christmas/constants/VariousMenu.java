package christmas.constants;

import static christmas.constants.GlobalConstant.ORDER;
import static christmas.constants.MenuType.APPETIZER;
import static christmas.constants.MenuType.BEVERAGE;
import static christmas.constants.MenuType.DESSERT;
import static christmas.constants.MenuType.MAIN;

import christmas.exception.InvalidDataException;

public enum VariousMenu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),

    // 음료
    ZERO_COLA("제로콜라", 3_000, BEVERAGE),
    RED_WINE("레드와인", 60_000, BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, BEVERAGE);

    private final String menuName;
    private final int price;
    private final MenuType type;

    VariousMenu(String menuName, int price, MenuType type) {
        this.menuName = menuName;
        this.price = price;
        this.type = type;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    public static VariousMenu getResultByMatchedMenuName(String menuName) {
        for (VariousMenu menu : values()) {
            if (menu.menuName.equals(menuName)) {
                return menu;
            }
        }
        throw InvalidDataException.from(ORDER);
    }
}
