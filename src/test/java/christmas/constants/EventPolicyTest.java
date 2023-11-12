package christmas.constants;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventPolicyTest {

    @ParameterizedTest
    @DisplayName("[정상] 이벤트 정책 별 이벤트 적용 할인 금액 테스트")
    @MethodSource("eventDiscountData")
    void checkEventDiscountTest(EventPolicy eventPolicy, int count, int discount) {
        Assertions.assertThat(eventPolicy.getDiscountAmount(count)).isEqualTo(discount);
    }

    static Stream<Arguments> eventDiscountData() {
        return Stream.of(
                Arguments.of(EventPolicy.GIVEAWAY_EVENT, 2, 25_000),
                Arguments.of(EventPolicy.WEEKEND_DISCOUNT, 3, 6_069),
                Arguments.of(EventPolicy.SPECIAL_DISCOUNT, 3, 1_000),
                Arguments.of(EventPolicy.CHRISTMAS_D_DAY_DISCOUNT, 23, 3_300)
        );
    }

}