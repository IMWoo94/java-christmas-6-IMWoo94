package christmas.constants;

import static christmas.constants.EventDate.END_DATE;
import static christmas.constants.EventDate.START_DATE;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDateTest {

    @Test
    @DisplayName("[정상] 이벤트 기간 시작일 종료일 정보 테스트")
    void eventDatePeriod() {
        LocalDate startDate = START_DATE.getDate();
        LocalDate endDate = END_DATE.getDate();

        Assertions.assertThat(startDate).isEqualTo(LocalDate.of(2023, 12, 1));
        Assertions.assertThat(endDate).isEqualTo(LocalDate.of(2023, 12, 31));
    }

    @Test
    @DisplayName("[정상] 이벤트 기간 빼기")
    void eventDateMinusDays() {
        LocalDate date = START_DATE.getMinusDays(10);
        Assertions.assertThat(date).isEqualTo(LocalDate.of(2023, 12, 1).minusDays(10));
        Assertions.assertThat(date).isEqualTo(LocalDate.of(2023, 11, 21));
    }

    @Test
    @DisplayName("[정상] 이벤트 기간 더하기")
    void eventDatePlusDays() {
        LocalDate date = START_DATE.getPlusDays(10);
        Assertions.assertThat(date).isEqualTo(LocalDate.of(2023, 12, 1).plusDays(10));
        Assertions.assertThat(date).isEqualTo(LocalDate.of(2023, 12, 11));
    }

}