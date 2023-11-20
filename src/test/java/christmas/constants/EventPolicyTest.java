package christmas.constants;

import static christmas.constants.biz.EventPolicy.GIFT_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.biz.EventPolicy;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class EventPolicyTest {

    @ParameterizedTest
    @DisplayName("[정상] 이벤트 참가 대상 확인 테스트")
    @CsvSource(value = {"10_000:true", "2_000:false"}, delimiter = ':')
    void checkParticipationConditions(int amount, boolean result) {
        assertThat(EventPolicy.checkParticipationConditions(amount)).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("[정상] 증정 대상 확인 테스트")
    @CsvSource(value = {"120_000:true", "2_000:false"}, delimiter = ':')
    void checkGiveawayEventConditions(int amount, boolean result) {
        assertThat(EventPolicy.checkGiftEventConditions(amount)).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("[정상] 이벤트 정책 별 이벤트 적용 할인 금액 테스트")
    @MethodSource("eventDiscountData")
    void checkEventDiscountTest(EventPolicy eventPolicy, int count, int discount) {
        assertThat(eventPolicy.getDiscountAmount(count)).isEqualTo(discount);
    }

    static Stream<Arguments> eventDiscountData() {
        return Stream.of(
                Arguments.of(EventPolicy.WEEKEND_DISCOUNT, 3, 6_069),
                Arguments.of(EventPolicy.SPECIAL_DISCOUNT, 3, 1_000),
                Arguments.of(EventPolicy.CHRISTMAS_D_DAY_DISCOUNT, 23, 3_300)
        );
    }

    @Test
    @DisplayName("[정상] 이벤트 증정 이벤트 적용 할인 금액 테스트")
    void checkEventGiftDiscountTest() {
        assertThat(GIFT_EVENT.getGiftBenefitAmount()).isEqualTo(25_000);
    }

}