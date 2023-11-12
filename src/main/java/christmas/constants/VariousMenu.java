package christmas.constants;

import static christmas.constants.ErrorMessage.INVALID_DATA;
import static christmas.constants.GlobalConstant.APPETIZER;
import static christmas.constants.GlobalConstant.BEVERAGE;
import static christmas.constants.GlobalConstant.DESSERT;
import static christmas.constants.GlobalConstant.MAIN;
import static christmas.constants.GlobalConstant.ORDER;

import christmas.exception.InvalidDataException;

public enum VariousMenu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER.getValue()),
    TAPAS("타파스", 5_500, APPETIZER.getValue()),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER.getValue()),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MAIN.getValue()),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN.getValue()),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN.getValue()),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN.getValue()),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT.getValue()),
    ICE_CREAM("아이스크림", 5_000, DESSERT.getValue()),

    // 음료
    ZERO_COLA("제로콜라", 3_000, BEVERAGE.getValue()),
    RED_WINE("레드와인", 60_000, BEVERAGE.getValue()),
    CHAMPAGNE("샴페인", 25_000, BEVERAGE.getValue());

    private final String menuName;
    private final int price;
    private final String type;

    VariousMenu(String menuName, int price, String type) {
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

    public String getType() {
        return type;
    }

    public static VariousMenu getResultByMatchedMenuName(String menuName) {
        for (VariousMenu menu : values()) {
            if (menu.menuName.equals(menuName)) {
                return menu;
            }
        }
        throw new InvalidDataException(INVALID_DATA.getFormatMessage(ORDER.getValue()));
    }
}
