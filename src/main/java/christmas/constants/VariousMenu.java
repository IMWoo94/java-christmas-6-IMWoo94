package christmas.constants;

import static christmas.constants.GlobalConstant.ORDER;
import static christmas.constants.MenuType.APPETIZER;
import static christmas.constants.MenuType.BEVERAGE;
import static christmas.constants.MenuType.DESSERT;
import static christmas.constants.MenuType.MAIN;

import christmas.exception.InvalidDataException;
import java.util.Arrays;
import java.util.List;

public enum VariousMenu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER, false, 0),
    TAPAS("타파스", 5_500, APPETIZER, false, 0),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER, false, 0),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000, MAIN, false, 0),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN, false, 0),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN, false, 0),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN, false, 0),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT, false, 0),
    ICE_CREAM("아이스크림", 5_000, DESSERT, false, 0),

    // 음료
    ZERO_COLA("제로콜라", 3_000, BEVERAGE, false, 0),
    RED_WINE("레드와인", 60_000, BEVERAGE, false, 0),
    CHAMPAGNE("샴페인", 25_000, BEVERAGE, true, 1);

    private final String menuName;
    private final int price;
    private final MenuType type;
    private final boolean gift;
    private final int quantity;

    VariousMenu(String menuName, int price, MenuType type, boolean gift, int quantity) {
        this.menuName = menuName;
        this.price = price;
        this.type = type;
        this.gift = gift;
        this.quantity = quantity;
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

    public boolean isGift() {
        return gift;
    }

    public int getQuantity() {
        return quantity;
    }

    public int giftAmount() {
        return price * quantity;
    }

    public static VariousMenu getResultByMatchedMenuName(String otherMenuName) {
        return Arrays.stream(values())
                .filter(menu -> menu.menuName.equals(otherMenuName))
                .findFirst()
                .orElseThrow(() -> InvalidDataException.from(ORDER));
    }

    public static List<VariousMenu> getGifts() {
        return Arrays.stream(values())
                .filter(menu -> menu.gift)
                .toList();
    }
}
