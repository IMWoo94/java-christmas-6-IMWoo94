package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidDataException;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String ERROR_DATE_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 입력 값 LocalDate 타입 으로 변환")
    @MethodSource("initData")
    void parserSuccessTest(String data, LocalDate conversionData) {
        assertThat(Parser.StringToLocalDate(data)).isEqualTo(conversionData);
    }

    static Stream<Arguments> initData() {
        return Stream.of(
                Arguments.of("12", LocalDate.of(YEAR, MONTH, 12)),
                Arguments.of("23", LocalDate.of(YEAR, MONTH, 23)),
                Arguments.of("31", LocalDate.of(YEAR, MONTH, 31))
        );
    }

    @ParameterizedTest
    @DisplayName("[예외] 예약 일자 입력 값 타입 변환 오류 : 숫자 타입 X 날짜 지원 타입 X")
    @ValueSource(strings = {"asd", " ", "", "z1", "33"})
    void parserFailTest(String data) {
        assertThatThrownBy(() -> Parser.StringToLocalDate(data))
                .isInstanceOf(InvalidDataException.class)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX, ERROR_DATE_MESSAGE);
    }
}