package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.MenuType;
import christmas.exception.InvalidDataException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrdersTest {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    // 정상케이스
    @ParameterizedTest
    @DisplayName("[정상] 주문 입력 기능 정상 테스트")
    @MethodSource("successOrderData")
    void orderSuccess(String readOrder, String otherOrder) {
        Orders orders = new Orders(readOrder);
        assertThat(orders.toString()).contains(otherOrder);
    }

    static Stream<Arguments> successOrderData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", "해산물파스타-1,레드와인-2,초코케이크-10", 305000),
                Arguments.of("바비큐립-2,티본스테이크-4", "바비큐립-2,티본스테이크-4", 328000)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 주문 입력 후 주문 전체 금액 테스트")
    @MethodSource("calculateTotalOrderAmountData")
    void calculateTotalOrderAmountSuccess(String readOrder, int totalAmount) {
        Orders orders = new Orders(readOrder);
        assertThat(orders.getCalculateTotalOrderAmount()).isEqualTo(totalAmount);
    }

    static Stream<Arguments> calculateTotalOrderAmountData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", 305_000),
                Arguments.of("바비큐립-2,티본스테이크-4", 328_000)
        );
    }

    // 메뉴 타입별 주문 수량 계산 기능
    @ParameterizedTest
    @DisplayName("[정상] 메뉴 타입별 주문 수량 계산 테스트")
    @MethodSource("quantityByMenuTypeData")
    void getQuantityByMenuTypeTest(String readOrder, MenuType type, int quantity) {
        Orders orders = new Orders(readOrder);
        assertThat(orders.getQuantityByMenuType(type)).isEqualTo(quantity);
    }

    static Stream<Arguments> quantityByMenuTypeData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", MenuType.DESSERT, 10),
                Arguments.of("바비큐립-2,티본스테이크-4", MenuType.MAIN, 6)
        );
    }

    // 중복 입력 예외 발생
    // 없는 메뉴 입력 시 예외 발생
    // 메뉴 주문 수량 20 초과 시 예외 발생
    @ParameterizedTest
    @DisplayName("[예외] 주문 입력 기능 예외 테스트 : 중복 메뉴 입력, 없는 메뉴 입력, 주문 수량 초과, 음료만 주문 ")
    @MethodSource("errorOrderData")
    void orderDuplicateAndNoMenuExceptionTest(String readOrder) {
        assertThatThrownBy(() -> new Orders(readOrder))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

    static Stream<Arguments> errorOrderData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10,없는메뉴-3"),
                Arguments.of("바비큐립-2,티본스테이크-4,바비큐립-4"),
                Arguments.of("바비큐립-10,티본스테이크-10,바비큐립-1"),
                Arguments.of("제로콜라-10,레드와인-9,샴페인-1")
        );
    }


}