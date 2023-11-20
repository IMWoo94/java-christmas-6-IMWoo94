package christmas.constants.biz;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgesTest {

    @ParameterizedTest
    @DisplayName("[정상] 일정 이벤트 헤택 금액에 따른 배지 부여 테스트")
    @MethodSource("badgesData")
    void checkBadgesTest(int benefitAmount, Badges otherBadge) {
        assertThat(Badges.getContionsBadge(benefitAmount)).isEqualTo(otherBadge);
    }

    static Stream<Arguments> badgesData() {
        return Stream.of(
                Arguments.of(20_000, Badges.SANTA),
                Arguments.of(17_000, Badges.TREE),
                Arguments.of(12_000, Badges.STAR),
                Arguments.of(5_000, Badges.STAR),
                Arguments.of(3_000, Badges.NONE),
                Arguments.of(0, Badges.NONE)
        );
    }
}