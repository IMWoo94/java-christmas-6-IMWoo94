package christmas.domain;

import static christmas.constants.EventPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.constants.EventPolicy.GIFT_EVENT;
import static christmas.constants.EventPolicy.SPECIAL_DISCOUNT;
import static christmas.constants.EventPolicy.WEEKDAY_DISCOUNT;
import static christmas.constants.EventPolicy.WEEKEND_DISCOUNT;

import java.time.LocalDate;
import java.util.HashMap;
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
    @DisplayName("[정상] 전체 이벤트 할인 금액 테스트")
    @MethodSource("discountData")
    void weekdayDiscountApplyTest(
            String description,
            Orders orders,
            ReservationDate reservationDate,
            Map<String, Integer> resultDiscount
    ) {
        Discount discount = new Discount(orders, reservationDate);
        Map<String, Integer> benefitDiscounts = discount.getBenefitDiscounts();
        Assertions.assertThat(benefitDiscounts).isEqualTo(resultDiscount);
    }

    static Stream<Arguments> discountData() {
        return Stream.of(
                Arguments.of("평일 이벤트 할인 금액 계산",
                        new Orders("해산물파스타-1,레드와인-2,초코케이크-10"),
                        new ReservationDate(LocalDate.of(2023, 12, 1)),
                        new HashMap<String, Integer>() {
                            {
                                put(GIFT_EVENT.getEventName(), GIFT_EVENT.getGiftBenefitAmount());
                                put(CHRISTMAS_D_DAY_DISCOUNT.getEventName(), 1_000);
                                put(WEEKDAY_DISCOUNT.getEventName(), 20_230);
                            }
                        }),
                Arguments.of("주말 이벤트 할인 금액 계산",
                        new Orders("해산물파스타-1,레드와인-1,초코케이크-1"),
                        new ReservationDate(LocalDate.of(2023, 12, 2)),
                        new HashMap<String, Integer>() {
                            {
                                put(CHRISTMAS_D_DAY_DISCOUNT.getEventName(), 1_100);
                                put(WEEKEND_DISCOUNT.getEventName(), 2_023);
                            }
                        }),
                Arguments.of("특별 이벤트 할인 금액 계산",
                        new Orders("해산물파스타-2,초코케이크-10"),
                        new ReservationDate(LocalDate.of(2023, 12, 10)),
                        new HashMap<String, Integer>() {
                            {
                                put(CHRISTMAS_D_DAY_DISCOUNT.getEventName(), 1_900);
                                put(GIFT_EVENT.getEventName(), GIFT_EVENT.getGiftBenefitAmount());
                                put(WEEKEND_DISCOUNT.getEventName(), 4_046);
                                put(SPECIAL_DISCOUNT.getEventName(), 1_000);

                            }
                        }),
                Arguments.of("크리스마스 디데이 이벤트 할인 금액 계산",
                        new Orders("해산물파스타-1,레드와인-2,초코케이크-10"),
                        new ReservationDate(LocalDate.of(2023, 12, 24)),
                        new HashMap<String, Integer>() {
                            {
                                put(CHRISTMAS_D_DAY_DISCOUNT.getEventName(), 3_300);
                                put(GIFT_EVENT.getEventName(), GIFT_EVENT.getGiftBenefitAmount());
                                put(WEEKEND_DISCOUNT.getEventName(), 2_023);
                                put(SPECIAL_DISCOUNT.getEventName(), 1_000);
                            }
                        }),
                Arguments.of("증정 이벤트 할인 금액 계산",
                        new Orders("해산물파스타-1,레드와인-2,초코케이크-10"),
                        new ReservationDate(LocalDate.of(2023, 12, 26)),
                        new HashMap<String, Integer>() {
                            {
                                put(GIFT_EVENT.getEventName(), GIFT_EVENT.getGiftBenefitAmount());
                                put(WEEKDAY_DISCOUNT.getEventName(), 20_230);
                            }
                        })
        );
    }
}