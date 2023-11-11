package christmas.constants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VariousMenuTest {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @DisplayName("[예외] 주문 내역 메뉴에 없는 메뉴 주문 시 예외 테스트")
    @ValueSource(strings = {"noMenu", "없는 주문"})
    void checkOrderMenuNoMenuName(String orderMenuName) {
        assertThatThrownBy(() -> VariousMenu.getResultByMatchedMenuName(orderMenuName))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

}