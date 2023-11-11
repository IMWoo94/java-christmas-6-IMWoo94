package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidDataException;
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
    @ValueSource(strings = {"양송이수프-1,타파스-2,티본스테이크-1", "초코케이크-1,제로콜라-3,레드와인-1", "바비큐립-1,해산물파스타-3"})
    void inputOrderSuccessTest(String inputOrder) {
        assertThatNoException().isThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder));
    }

    @ParameterizedTest
    @DisplayName("[예외] 주문 내역 입력 값에 대한 유효성 검사 : 공백, 빈 값, \" \", 입력 포맷 불일치")
    @MethodSource("errorInputOrder")
    void inputOrderExceptionTest(String inputOrder) {
        System.out.println("inputOrder = " + inputOrder);
        assertThatThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

    static Stream<Arguments> errorInputOrder() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("양송이수프-3,타파스-04"),
                Arguments.of("양송이수프-3,아이스크림-1,"),
                Arguments.of("양송이수프-3,,"),
                Arguments.of("양송이수프-3 ,스테이크-4")
        );
    }

    @Test
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : null 입력")
    void validateNullTest() {
        String inputOrder = null;
        assertThatThrownBy(() -> InputOrderValidator.validateInputOrder(inputOrder))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_ORDER_MESSAGE);
    }

}