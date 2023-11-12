package christmas.domain;

import christmas.constants.ErrorMessage;
import christmas.constants.VariousMenu;
import christmas.exception.InvalidDataException;
import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Orders {
    private final EnumMap<VariousMenu, Integer> orderMenu = new EnumMap<>(VariousMenu.class);
    private final String orders;
    private int totalMenuQuantity;

    public Orders(String readOrder) {
        // 해산물파스타-2,레드와인-1,초코케이크-10 의 형식의 데이터
        orders = readOrder;
        saveOrderMenu();
        checkOrderMenu();
    }

    private void saveOrderMenu() {
        String orderRegex = "([^,]+)-(\\d+)";
        Pattern pattern = Pattern.compile(orderRegex);
        Matcher matcher = pattern.matcher(orders);

        while (matcher.find()) {
            String foodName = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));

            VariousMenu menuName = getVariousMenu(foodName);
            validateDuplicateMenu(menuName);
            totalMenuQuantity += quantity;
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
            throw new InvalidDataException(ErrorMessage.INVALID_DATA.getFormatMessage("주문"));
        }
    }

    private void checkOrderMenu() {
        validateMenuQuantityOver();
    }

    private void validateMenuQuantityOver() {
        if (totalMenuQuantity > 20) {
            // 주문 메뉴 수량 20개 초과 시 예외 발생
            throw new InvalidDataException(ErrorMessage.INVALID_DATA.getFormatMessage("주문"));
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
