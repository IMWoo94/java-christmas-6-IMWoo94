package christmas.constants;

import static christmas.constants.GlobalConstant.MONTH;
import static christmas.constants.GlobalConstant.YEAR;
import static christmas.constants.biz.EventDate.CHRISTMAST;
import static christmas.constants.biz.EventDate.END_DATE;
import static christmas.constants.biz.EventDate.START_DATE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.biz.EventDate;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDateTest {

    @Test
    @DisplayName("[정상] 이벤트 기간 시작일 종료일 정보 테스트")
    void eventDatePeriod() {
        LocalDate startDate = START_DATE.getDate();
        LocalDate endDate = END_DATE.getDate();

        assertThat(startDate).isEqualTo(LocalDate.of(YEAR, MONTH, 1));
        assertThat(endDate).isEqualTo(LocalDate.of(YEAR, MONTH, 31));
    }

    @Test
    @DisplayName("[정상] 이벤트 기간 빼기")
    void eventDateMinusDays() {
        LocalDate date = START_DATE.getMinusDays(10);

        assertThat(date).isEqualTo(LocalDate.of(YEAR, MONTH, 1).minusDays(10));
        assertThat(date).isEqualTo(LocalDate.of(YEAR, MONTH, 21));
    }

    @Test
    @DisplayName("[정상] 이벤트 기간 더하기")
    void eventDatePlusDays() {
        LocalDate date = START_DATE.getPlusDays(10);

        assertThat(date).isEqualTo(LocalDate.of(YEAR, MONTH, 1).plusDays(10));
        assertThat(date).isEqualTo(LocalDate.of(YEAR, MONTH, 11));
    }

    @Test
    @DisplayName("[정상] 현재 데이트 정보와 입력된 데이터의 정보가 동일한지 테스트")
    void dateEqualsTest() {
        assertThat(END_DATE.dateEquals(LocalDate.of(YEAR, MONTH, 31))).isTrue();
        assertThat(END_DATE.dateEquals(LocalDate.of(YEAR, MONTH, 30))).isFalse();
        assertThat(START_DATE.dateEquals(LocalDate.of(YEAR, MONTH, 1))).isTrue();
        assertThat(START_DATE.dateEquals(LocalDate.of(YEAR, MONTH, 2))).isFalse();
    }

    @Test
    @DisplayName("[정상] 현재 기간과 입력된 기간 사이의 누적 일수")
    void getAccumulateDaysTest() {
        assertThat(START_DATE.getAccumulateDays(LocalDate.of(YEAR, MONTH, 25))).isEqualTo(24);
        assertThat(END_DATE.getAccumulateDays(LocalDate.of(YEAR, MONTH, 25))).isEqualTo(-6);
        assertThat(CHRISTMAST.getAccumulateDays(LocalDate.of(YEAR, MONTH, 23))).isEqualTo(-2);
    }

    @Test
    @DisplayName("[정상] 입력 된 일자가 이벤트 기간에 포함 되는지 테스트")
    void canEventPeriodTest() {
        LocalDate otherDate = LocalDate.of(YEAR, MONTH, 23);

        assertThat(EventDate.canEventPeriod(otherDate)).isTrue();

        LocalDate otherDate2 = LocalDate.of(YEAR, MONTH - 1, 23);

        assertThat(EventDate.canEventPeriod(otherDate2)).isFalse();
    }

    @Test
    @DisplayName("[정상] 입력 된 일자가 크리스마스 기간에 포함 되는지 테스트")
    void canChristmastPeriodTest() {
        LocalDate otherDate = LocalDate.of(YEAR, MONTH, 25);

        assertThat(EventDate.canChristmastPeriod(otherDate)).isTrue();

        LocalDate otherDate2 = LocalDate.of(YEAR, MONTH, 23);

        assertThat(EventDate.canChristmastPeriod(otherDate2)).isFalse();
    }
}