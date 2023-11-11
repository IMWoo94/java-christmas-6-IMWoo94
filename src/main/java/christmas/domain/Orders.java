package christmas.domain;

import christmas.constants.VariousMenu;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class Orders {
    private final EnumMap<VariousMenu, Integer> orderMenu;

    public Orders(String readOrder) {
        orderMenuInit(readOrder);
        orderMenu = Arrays.stream(readOrder.split(", "))
                .map(order -> order.split("-"))
                .collect(Collectors.toMap(
                        arr -> VariousMenu.getResultByMatchedMenuName(arr[0]),
                        arr -> Integer.parseInt(arr[1]),
                        (existing, replacement) -> existing,
                        () -> new EnumMap<>(VariousMenu.class)
                ));
    }

    private void orderMenuInit(String readOrder) {
        List<String> orders = Arrays.stream(readOrder.split(","))
                .toList();


    }


}
