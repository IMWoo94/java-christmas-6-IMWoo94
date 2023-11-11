package christmas.domain;

import static christmas.constants.EventDate.MONTH;
import static christmas.constants.EventDate.YEAR;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalendarTest {

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 저장 요일 정보 등록 기능 테스트")
    @MethodSource("dayOfWeekCompareData")
    void dayOfWeekCompareTest(LocalDate date, DayOfWeek otherDayOfWeek) {
        Calendar reservationDate = new Calendar(date);
        assertThat(reservationDate).isNotNull();
        assertThat(reservationDate.compareDayOfWeek(otherDayOfWeek)).isTrue();
    }

    static Stream<Arguments> dayOfWeekCompareData() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 12), DayOfWeek.TUESDAY),
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 23), DayOfWeek.SATURDAY),
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 31), DayOfWeek.SUNDAY)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 주말 - 평일 확인 기능 테스트")
    @MethodSource("holyDayCheckData")
    void holyDayCheckTest(LocalDate date, boolean result) {
        Calendar reservationDate = new Calendar(date);
        assertThat(reservationDate.isHoliday()).isEqualTo(result);
    }

    static Stream<Arguments> holyDayCheckData() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 12), false),
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 23), true),
                Arguments.of(LocalDate.of(YEAR.getValue(), MONTH.getValue(), 31), true)
        );
    }

    /**
     * - [ ] 예약 일자 저장 기능
     *     - [ ] 예약 일자 요일 정보 등록 기능
     *     - [ ] 예약 일자 주말 - 평일 확인 기능
     *     - [ ] 특별 할인 대상일 확인 기능
     *     - [ ] 이벤트 기간 확인 기능
     *     - [ ] 크리스마스 이전 확인 기능
     *     - [ ] 크리스마스 당일 확인 기능
     */

}