package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidDateException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputOrderValidatorTest {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_ORDER_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 입력 값에 대한 유효성 검사")
    @ValueSource(strings = {"12", "29", "31"})
    void inputOrderSuccessTest(String inputOrder) {
        assertThatNoException().isThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder));
    }

    @ParameterizedTest
    @DisplayName("[예외] 주문 내역 입력 값에 대한 유효성 검사 : 공백, 빈 값, \" \"")
    @MethodSource("errorInputOrder")
    void inputOrderExceptionTest(String inputOrder) {
        assertThatThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder))
                .isInstanceOf(InvalidDateException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

    static Stream<Arguments> errorInputOrder() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" ")
        );
    }

    @Test
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : null 입력")
    void validateNullTest() {
        String inputOrder = null;
        assertThatThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder))
                .isInstanceOf(InvalidDateException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

}