package christmas.constants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.biz.VariousMenu;
import christmas.exception.InvalidDataException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VariousMenuTest {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_ORDER_MESSAGE = "유효 하지 않은 주문입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @DisplayName("[예외] 주문 내역 메뉴에 없는 메뉴 주문 시 예외 테스트")
    @ValueSource(strings = {"noMenu", "없는 주문"})
    void checkOrderMenuNoMenuName(String orderMenuName) {
        assertThatThrownBy(() -> VariousMenu.getResultByMatchedMenuName(orderMenuName))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

    @Test
    @DisplayName("[정상] 증정 메뉴 조회 테스트")
    void checkGiftMenuTest() {
        List<VariousMenu> gifts = VariousMenu.getGifts();
        assertThat(gifts).isNotNull();
        assertThat(gifts).contains(VariousMenu.CHAMPAGNE);
    }

}