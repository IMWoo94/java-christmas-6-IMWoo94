package christmas.domain;

import static christmas.constants.biz.EventPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.constants.biz.EventPolicy.GIFT_EVENT;
import static christmas.constants.biz.EventPolicy.SPECIAL_DISCOUNT;
import static christmas.constants.biz.EventPolicy.WEEKDAY_DISCOUNT;
import static christmas.constants.biz.EventPolicy.WEEKEND_DISCOUNT;

import christmas.constants.biz.EventPolicy;
import christmas.constants.biz.MenuType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Discount {

    private final EnumMap<EventPolicy, Integer> eventDiscount = new EnumMap<>(EventPolicy.class);
    private final Orders orders;
    private final ReservationDate reservationDate;

    public Discount(Orders orders, ReservationDate reservationDate) {
        this.orders = orders;
        this.reservationDate = reservationDate;
        if (availabilityOfBenefits()) {
            discountCalculate();
        }
    }

    private void discountCalculate() {
        if (reservationDate.canEventPeriod()) {
            weekdayDiscountApply();
            weekendDiscountApply();
            specialDiscountApply();
            christmasDDayDiscountApply();
            giveawayEventApply();
        }
    }

    // 이벤트 참여 확인 기능
    private boolean availabilityOfBenefits() {
        return EventPolicy.checkParticipationConditions(orders.getCalculateTotalOrderAmount());
    }

    //    - [ ] 평일 이벤트 할인 금액 계산 기능 : 디저트 메뉴 당 2,023 할인
    private void weekdayDiscountApply() {
        if (reservationDate.isWeekday()) {
            int dessertQuantity = orders.getQuantityByMenuType(MenuType.DESSERT);
            int discountAmount = WEEKDAY_DISCOUNT.getDiscountAmount(dessertQuantity);

            eventDiscount.put(WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    //    - [ ] 주말 이벤트 할인 금액 계산 기능 : 메인 메뉴 당 2,023원 할인
    private void weekendDiscountApply() {
        if (reservationDate.isHoliday()) {
            int mainQuantity = orders.getQuantityByMenuType(MenuType.MAIN);
            int discountAmount = WEEKEND_DISCOUNT.getDiscountAmount(mainQuantity);

            eventDiscount.put(WEEKEND_DISCOUNT, discountAmount);
        }
    }

    //    - [ ] 특별 이벤트 할인 금액 계산 기능 : 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
    private void specialDiscountApply() {
        if (reservationDate.isSpecialDiscountDate()) {
            int discountAmount = SPECIAL_DISCOUNT.getDiscount();

            eventDiscount.put(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    //    - [ ] 크리스마스 디데이 할인 금액 계산 기능 : 크리스마스 디데이 다가올수록 할인 금액 100원 씩 증가
    private void christmasDDayDiscountApply() {
        Optional<Integer> days = reservationDate.getEventAccumulateDays();
        if (days.isPresent()) {
            int discountAmount = CHRISTMAS_D_DAY_DISCOUNT.getDiscountAmount(days.get());

            eventDiscount.put(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
        }
    }

    //    - [ ] 증정 기능 : 할인 전 총 주문 금액이 12만원 이상일때, 샴페인 1개 증정
    private void giveawayEventApply() {
        if (EventPolicy.checkGiftEventConditions(orders.getCalculateTotalOrderAmount())) {
            int giftAmount = 0;
            giftAmount = GIFT_EVENT.getGiftBenefitAmount();
            eventDiscount.put(GIFT_EVENT, giftAmount);
        }
    }

    public Map<String, Integer> getBenefitDiscounts() {
        Map<String, Integer> benefitDiscounts = new HashMap<>();
        eventDiscount.forEach(
                (eventPolicy, discount) -> benefitDiscounts.put(eventPolicy.getEventName(), discount)
        );
        return benefitDiscounts;
    }

    public int getTotalBenefitAmount() {
        return eventDiscount.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
