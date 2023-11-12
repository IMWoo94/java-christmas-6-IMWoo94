package christmas.domain;

import static christmas.constants.GlobalConstant.ORDER;
import static christmas.constants.MenuType.BEVERAGE;

import christmas.constants.MenuType;
import christmas.constants.VariousMenu;
import christmas.exception.InvalidDataException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Orders {
    private static final String ORDER_REGEX = "([^,]+)-(\\d+)";
    private static final int LIMIT_QUANTITY = 20;
    private final EnumMap<VariousMenu, Integer> orderMenu = new EnumMap<>(VariousMenu.class);
    private final EnumMap<MenuType, Integer> orderQuantityByMenuType = new EnumMap<>(MenuType.class);
    private final String orders;

    public Orders(String readOrder) {
        // 해산물파스타-2,레드와인-1,초코케이크-10 의 형식의 데이터
        orders = readOrder;
        saveOrderMenu();
        quantitySurveyByMenuType();
    }

    public Map<String, String> getOrderMenu() {
        Map<String, String> menus = new HashMap<>();
        orderMenu.forEach(
                (key, value) -> menus.put(key.getMenuName(), String.valueOf(value))
        );

        return menus;
    }

    public int getCalculateTotalOrderAmount() {
        // 주문 총 금액 계산
        return orderMenu.entrySet()
                .stream()
                .mapToInt(menus -> menus.getKey().getPrice() * menus.getValue())
                .sum();
    }

    public int getQuantityByMenuType(MenuType type) {
        return orderQuantityByMenuType.get(type);
    }

    private void quantitySurveyByMenuType() {
        Arrays.stream(MenuType.values())
                .parallel()
                .forEach(type -> {
                    int quantity = orderMenu.entrySet()
                            .parallelStream()
                            .filter(entry -> entry.getKey().getType().equals(type))
                            .mapToInt(Entry::getValue)
                            .sum();
                    orderQuantityByMenuType.put(type, quantity);
                });
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
        checkOrderMenu();
    }

    private VariousMenu getVariousMenu(String foodName) {
        // 없는 메뉴 제공 시 예외 발생
        return VariousMenu.getResultByMatchedMenuName(foodName);
    }

    private void validateDuplicateMenu(VariousMenu menuName) {
        if (orderMenu.containsKey(menuName)) {
            // 중복 입력에 대한 예외 발생 처리
            throw InvalidDataException.from(ORDER);
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

        if (totalMenuQuantity > LIMIT_QUANTITY) {
            throw InvalidDataException.from(ORDER);
        }
    }

    private void validateOrderOnlyBeverage() {
        boolean result = orderMenu.keySet()
                .stream()
                .allMatch(menu -> menu.getType().equals(BEVERAGE));

        if (result) {
            throw InvalidDataException.from(ORDER);
        }
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderMenu=" + orderMenu +
                ", orderQuantityByMenuType=" + orderQuantityByMenuType +
                ", orders='" + orders + '\'' +
                '}';
    }
}
