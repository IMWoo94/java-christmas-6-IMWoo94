package christmas.domain;

import static christmas.constants.GlobalConstant.MONTH;
import static christmas.constants.GlobalConstant.YEAR;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReservationDateTest {

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 저장 요일 정보 등록 기능 테스트")
    @MethodSource("dayOfWeekCompareData")
    void checkDayOfWeekCompareTest(LocalDate date, DayOfWeek otherDayOfWeek) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate).isNotNull();
        assertThat(reservationDate.compareDayOfWeek(otherDayOfWeek)).isTrue();
    }

    static Stream<Arguments> dayOfWeekCompareData() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 12), DayOfWeek.TUESDAY),
                Arguments.of(LocalDate.of(YEAR, MONTH, 23), DayOfWeek.SATURDAY),
                Arguments.of(LocalDate.of(YEAR, MONTH, 31), DayOfWeek.SUNDAY)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 주말 - 평일 확인 기능 테스트")
    @MethodSource("holyDayCheckData")
    void checkHolyDayTest(LocalDate date, boolean result) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.isHoliday()).isEqualTo(result);
        assertThat(reservationDate.isWeekday()).isEqualTo(!result);
    }

    static Stream<Arguments> holyDayCheckData() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 12), false),
                Arguments.of(LocalDate.of(YEAR, MONTH, 23), true),
                Arguments.of(LocalDate.of(YEAR, MONTH, 31), true)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 특별 할인 대상일 확인 기능 테스트")
    @MethodSource("specialDiscountDate")
    void checkSpecialDiscountDateTest(LocalDate date, boolean result) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.isSpecialDiscountDate()).isEqualTo(result);
    }

    static Stream<Arguments> specialDiscountDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 12), false),
                Arguments.of(LocalDate.of(YEAR, MONTH, 24), true),
                Arguments.of(EventDate.CHRISTMAST.getDate(), true)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 이벤트 기간 확인 기능 테스트")
    @MethodSource("eventDiscountDate")
    void checkEventPeriodTest(LocalDate date, boolean result) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.canEventPeriod()).isEqualTo(result);
    }

    static Stream<Arguments> eventDiscountDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 1), true),
                Arguments.of(LocalDate.of(YEAR, MONTH, 31), true),
                Arguments.of(EventDate.CHRISTMAST.getDate(), true),
                Arguments.of(LocalDate.of(YEAR, MONTH - 1, 11), false)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 크리스마스 이벤트 누적 일수 확인 기능 테스트")
    @MethodSource("eventAccumulateData")
    void getEventAccumulateDaysTest(LocalDate date, int accumulateDays) {
        ReservationDate reservationDate = new ReservationDate(date);
        int christmasEventAccumulateDays = reservationDate.getEventAccumulateDays();
        assertThat(christmasEventAccumulateDays).isEqualTo(accumulateDays);
    }

    static Stream<Arguments> eventAccumulateData() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 31), 0),
                Arguments.of(LocalDate.of(YEAR, MONTH, 24), 23),
                Arguments.of(EventDate.CHRISTMAST.getDate(), 24)
        );
    }

    @ParameterizedTest
    @DisplayName("[정상] 예약 일자 크리스마스 당일 확인 기능 테스트")
    @MethodSource("christmastDate")
    void checkChristmasDateTest(LocalDate date, boolean result) {
        ReservationDate reservationDate = new ReservationDate(date);
        assertThat(reservationDate.isChristmastDate()).isEqualTo(result);
    }

    static Stream<Arguments> christmastDate() {
        return Stream.of(
                Arguments.of(LocalDate.of(YEAR, MONTH, 31), false, 1),
                Arguments.of(LocalDate.of(YEAR, MONTH - 2, 24), false, 2),
                Arguments.of(EventDate.CHRISTMAST.getDate(), true, 3)
        );
    }
}