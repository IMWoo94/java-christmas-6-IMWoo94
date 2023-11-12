package christmas.domain;

import static christmas.constants.EventPolicy.WEEKDAY_DISCOUNT;
import static christmas.constants.EventPolicy.WEEKEND_DISCOUNT;

import christmas.constants.EventPolicy;
import christmas.constants.MenuType;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final EnumMap<EventPolicy, Integer> eventDiscount = new EnumMap<>(EventPolicy.class);
    private final Orders orders;

    public Discount(Orders orders) {
        this.orders = orders;
    }

    // 총 주문 금액 10,000원 이상 부터 이벤트 적용

    //    - [ ] 평일 이벤트 할인 금액 계산 기능 : 디저트 메뉴 당 2,023 할인
    public void weekdayDiscountApply() {
        int dessertQuantity = orders.getQuantityByMenuType(MenuType.DESSERT);
        int discountAmount = WEEKDAY_DISCOUNT.getDiscountAmount(dessertQuantity);

        eventDiscount.put(WEEKDAY_DISCOUNT, discountAmount);
    }

    //    - [ ] 주말 이벤트 할인 금액 계산 기능 : 메인 메뉴 당 2,023원 할인
    public void weekendDiscountApply() {
        int mainQuantity = orders.getQuantityByMenuType(MenuType.MAIN);
        int discountAmount = WEEKEND_DISCOUNT.getDiscountAmount(mainQuantity);

        eventDiscount.put(WEEKEND_DISCOUNT, discountAmount);
    }
    //    - [ ] 특별 이벤트 할인 금액 계산 기능 : 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
    //    - [ ] 크리스마스 디데이 할인 금액 계산 기능 : 크리스마스 디데이 다가올수록 할인 금액 100원 씩 증가
    //    - [ ] 증정 기능 : 할인 전 총 주문 금액이 12만원 이상일때, 샴페인 1개 증정

    public Map<String, Integer> getBenefitDiscounts() {
        Map<String, Integer> benefitDiscounts = new HashMap<>();
        eventDiscount.forEach(
                (key, value) -> benefitDiscounts.put(key.getEventName(), value)
        );
        return benefitDiscounts;
    }

}
