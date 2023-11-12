package christmas.domain;

import christmas.constants.EventPolicy;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DiscountTest {

    //    - [ ] 평일 이벤트 할인 금액 계산 기능 : 디저트 메뉴 당 2,023 할인
    @ParameterizedTest
    @DisplayName("[정상] 평일 이벤트 할인 금액 테스트")
    @MethodSource("weekdayDiscountData")
    void weekdayDiscountApplyTest(String readOrder, int benefitAmount) {
        Orders orders = new Orders(readOrder);
        Discount discount = new Discount(orders);

        discount.weekdayDiscountApply();

        Map<String, Integer> eventDiscount = discount.getBenefitDiscounts();
        int applyAmount = eventDiscount.get(EventPolicy.WEEKDAY_DISCOUNT.getEventName());
        Assertions.assertThat(applyAmount).isEqualTo(benefitAmount);
    }

    static Stream<Arguments> weekdayDiscountData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", 20230),
                Arguments.of("바비큐립-2,티본스테이크-4", 0)
        );
    }
    //    - [ ] 주말 이벤트 할인 금액 계산 기능 : 메인 메뉴 당 2,023원 할인
    //    - [ ] 특별 이벤트 할인 금액 계산 기능 : 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
    //    - [ ] 크리스마스 디데이 할인 금액 계산 기능 : 크리스마스 디데이 다가올수록 할인 금액 100원 씩 증가
    //    - [ ] 증정 기능 : 할인 전 총 주문 금액이 12만원 이상일때, 샴페인 1개 증정

}