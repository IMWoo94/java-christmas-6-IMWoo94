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

class InputDateValidatorTest {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_DATE_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 입력 값에 대한 유효성 검사")
    @ValueSource(strings = {"12", "29", "31"})
    void inputDateSuccessTest(String inputDate) {
        assertThatNoException().isThrownBy(() -> InputDateValidator.validateInputDate(inputDate));
    }

    @ParameterizedTest
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : 공백, 빈 값, \" \"")
    @MethodSource("errorInputDate")
    void inputDateExceptionTest(String inputDate) {
        assertThatThrownBy(() -> InputDateValidator.validateInputDate(inputDate))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }

    static Stream<Arguments> errorInputDate() {
        return Stream.of(
                Arguments.of("noNumberType"),
                Arguments.of(""),
                Arguments.of(" ")
        );
    }

    @Test
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : null 입력")
    void validateNullTest() {
        String inputDate = null;
        assertThatThrownBy(() -> InputDateValidator.validateInputDate(inputDate))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }


}
