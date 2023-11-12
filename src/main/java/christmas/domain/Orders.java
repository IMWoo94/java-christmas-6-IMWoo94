package christmas.domain;

import static christmas.constants.GlobalConstant.ORDER;
import static christmas.constants.MenuType.BEVERAGE;

import christmas.constants.ErrorMessage;
import christmas.constants.MenuType;
import christmas.constants.VariousMenu;
import christmas.exception.InvalidDataException;
import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Orders {
    private final String ORDER_REGEX = "([^,]+)-(\\d+)";
    private final EnumMap<VariousMenu, Integer> orderMenu = new EnumMap<>(VariousMenu.class);
    private final EnumMap<MenuType, Integer> orderByMenu = new EnumMap<>(MenuType.class);
    private final String orders;

    public Orders(String readOrder) {
        // 해산물파스타-2,레드와인-1,초코케이크-10 의 형식의 데이터
        orders = readOrder;
        saveOrderMenu();
        checkOrderMenu();
    }

    public int calculateTotalOrderAmount() {
        // 주문 총 금액 계산
        return orderMenu.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private void saveOrderMenu() {
        Pattern pattern = Pattern.compile(ORDER_REGEX);
        Matcher matcher = pattern.matcher(orders);

        while (matcher.find()) {
            String foodName = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));

            VariousMenu menuName = getVariousMenu(foodName);
            validateDuplicateMenu(menuName);
            orderMenu.put(menuName, quantity);
        }
    }

    private VariousMenu getVariousMenu(String foodName) {
        // 없는 메뉴 제공 시 예외 발생
        return VariousMenu.getResultByMatchedMenuName(foodName);
    }

    private void validateDuplicateMenu(VariousMenu menuName) {
        if (orderMenu.containsKey(menuName)) {
            // 중복 입력에 대한 예외 발생 처리
            throw new InvalidDataException(ErrorMessage.INVALID_DATA.getFormatMessage(ORDER.getValue()));
        }
    }

    private void checkOrderMenu() {
        validateMenuQuantityOver();
        validateOrderOnlyBeverage();
    }

    private void validateMenuQuantityOver() {
        int totalMenuQuantity = orderMenu.values()
                .stream()
                .mapToInt(Integer::intValue).sum();

        if (totalMenuQuantity > 20) {
            // 주문 메뉴 수량 20개 초과 시 예외 발생
            throw new InvalidDataException(ErrorMessage.INVALID_DATA.getFormatMessage(ORDER.getValue()));
        }
    }

    private void validateOrderOnlyBeverage() {
        boolean result = orderMenu.keySet()
                .stream()
                .allMatch(menu -> menu.getType().equals(BEVERAGE));

        if (result) {
            // 음료만 주문 시 예외 발생
            throw new InvalidDataException(ErrorMessage.INVALID_DATA.getFormatMessage(ORDER.getValue()));
        }
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderMenu=" + orderMenu +
                ", orders='" + orders + '\'' +
                '}';
    }
}
