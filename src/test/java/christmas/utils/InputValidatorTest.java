package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

import christmas.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_DATE_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @Test
    @DisplayName("[정상] 예약 일자 입력 값에 대한 유효성 검사")
    void inputDateSuccessTest(String inputDate) {
        fail("입력 실패");
    }

    @Test
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : 숫자 타입이 아닌 경우")
    void validateNumberTypeTest() {
        String inputDate = "noNumberType";
        assertThatThrownBy(() -> InputValidator.validateNumberType(inputDate))
                .isInstanceOf(InputException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("%s %s", ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : 1 ~ 31 사이의 숫자가 아닌 경우")
    @ValueSource(ints = {0, 32})
    void validateBetweenRangeTest(int day) {
        assertThatThrownBy(() -> assertThat(day).isBetween(1, 31))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_DATE_MESSAGE);

    }

    @ParameterizedTest
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : 빈 값, \" \" 공백 입력")
    @ValueSource(strings = {"", " "})
    void validateEmptyTest(String inputDate) {
        assertThatThrownBy(() -> InputValidator.validateEmpty(inputDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }

    @Test
    @DisplayName("[예외] 예약 일자 입력 값에 대한 유효성 검사 : null 입력")
    void validateNullTest() {
        String inputDate = null;
        assertThatThrownBy(() -> InputValidator.validateNull(inputDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }


}
