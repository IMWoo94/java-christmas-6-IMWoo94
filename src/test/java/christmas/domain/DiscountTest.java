package christmas.domain;

import static christmas.constants.EventPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.constants.EventPolicy.SPECIAL_DISCOUNT;

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
    @ParameterizedTest
    @DisplayName("[정상] 주말 이벤트 할인 금액 테스트")
    @MethodSource("weekendDiscountData")
    void weekendDiscountApplyTest(String readOrder, int benefitAmount) {
        Orders orders = new Orders(readOrder);
        Discount discount = new Discount(orders);

        discount.weekendDiscountApply();

        Map<String, Integer> eventDiscount = discount.getBenefitDiscounts();
        int applyAmount = eventDiscount.get(EventPolicy.WEEKEND_DISCOUNT.getEventName());
        Assertions.assertThat(applyAmount).isEqualTo(benefitAmount);
    }

    static Stream<Arguments> weekendDiscountData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", 2023),
                Arguments.of("바비큐립-2,티본스테이크-4", 12138)
        );
    }

    //    - [ ] 특별 이벤트 할인 금액 계산 기능 : 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
    @ParameterizedTest
    @DisplayName("[정상] 특별 이벤트 할인 금액 테스트")
    @MethodSource("specialDiscountData")
    void specialDiscountApplyTest(String readOrder, int benefitAmount) {
        Orders orders = new Orders(readOrder);
        Discount discount = new Discount(orders);

        discount.specialDiscountApply();

        Map<String, Integer> eventDiscount = discount.getBenefitDiscounts();
        int applyAmount = eventDiscount.get(SPECIAL_DISCOUNT.getEventName());
        Assertions.assertThat(applyAmount).isEqualTo(benefitAmount);
    }

    static Stream<Arguments> specialDiscountData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", 1000),
                Arguments.of("바비큐립-2,티본스테이크-4", 1000)
        );
    }

    //    - [ ] 크리스마스 디데이 할인 금액 계산 기능 : 크리스마스 디데이 다가올수록 할인 금액 100원 씩 증가
    @ParameterizedTest
    @DisplayName("[정상] 크리스마스 디데이 이벤트 할인 금액 테스트")
    @MethodSource("christmasDDayDiscountData")
    void christmasDDayDiscountApplyTest(String readOrder, int benefitAmount, int days) {
        Orders orders = new Orders(readOrder);
        Discount discount = new Discount(orders);

        discount.christmasDDayDiscountApply(days);

        Map<String, Integer> eventDiscount = discount.getBenefitDiscounts();
        int applyAmount = eventDiscount.get(CHRISTMAS_D_DAY_DISCOUNT.getEventName());
        Assertions.assertThat(applyAmount).isEqualTo(benefitAmount);
    }

    static Stream<Arguments> christmasDDayDiscountData() {
        return Stream.of(
                Arguments.of("해산물파스타-1,레드와인-2,초코케이크-10", 3300, 23),
                Arguments.of("바비큐립-2,티본스테이크-4", 1500, 5)
        );
    }
    //    - [ ] 증정 기능 : 할인 전 총 주문 금액이 12만원 이상일때, 샴페인 1개 증정

}