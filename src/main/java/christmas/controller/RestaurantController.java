package christmas.controller;

import static christmas.constants.biz.PreviewType.BENEFIT_DETAILS;
import static christmas.constants.biz.PreviewType.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT;
import static christmas.constants.biz.PreviewType.EVENT_BADGE;
import static christmas.constants.biz.PreviewType.GIVEAWAY_MENU;
import static christmas.constants.biz.PreviewType.ORDER_MENU;
import static christmas.constants.biz.PreviewType.TOTAL_BENEFIT_AMOUNT;
import static christmas.constants.biz.PreviewType.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT;

import christmas.constants.biz.EventPolicy;
import christmas.constants.biz.PreviewType;
import christmas.constants.biz.VariousMenu;
import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidDataException;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class RestaurantController {

    private ReservationDate reservationDate;
    private Orders order;
    private Discount discount;

    public RestaurantController() {
    }

    public void eventStart() {
        opening();
        reservation();
        discountsApply();
        eventPreviews();
    }

    private void opening() {
        OutputView.printOpening();
    }

    private void reservation() {
        reservationDate = askReservationDate();
        order = askOrder();
    }

    private ReservationDate askReservationDate() {
        try {
            // 식당 예약 안내 멘트
            OutputView.printRequestReservationDate();

            String readDate = InputView.readReservationDate();
            LocalDate date = Parser.StringToLocalDate(readDate);
            return new ReservationDate(date);
        } catch (InvalidDataException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return askReservationDate();
        }
    }

    private Orders askOrder() {
        try {
            OutputView.printRequestOrders();
            String readOrder = InputView.readOrders();

            OutputView.printInputOrder(readOrder);
            return new Orders(readOrder);
        } catch (InvalidDataException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return askOrder();
        }
    }

    private void discountsApply() {
        OutputView.printPreviewCommnet(reservationDate.getReservationDate());
        discount = new Discount(order, reservationDate);
    }

    private void eventPreviews() {
        orderMenuPreview();
        totalAmountBeforeDiscountPreview();
        giveawayPreview();
        eventBenefitPreview();
        totalBenefitAmountPreview();
        totalAmountAfterDiscountPreview();
        badgePreview();
    }

    private void orderMenuPreview() {
        String form = previewTypeComment(ORDER_MENU);

        Map<String, String> orderMenu = order.getOrderMenu();

        orderMenu.forEach(
                (menu, quantity) -> OutputView.printBenefitPreview(form, menu, quantity)
        );
    }

    private void totalAmountBeforeDiscountPreview() {
        String form = previewTypeComment(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT);

        int orderPrice = order.getCalculateTotalOrderAmount();

        OutputView.printBenefitPreview(form, orderPrice);
    }

    private void giveawayPreview() {
        String form = previewTypeComment(GIVEAWAY_MENU);
        List<VariousMenu> gifts = VariousMenu.getGifts();
        int price = order.getCalculateTotalOrderAmount();
        if (EventPolicy.checkGiveawayEventConditions(price)) {
            for (VariousMenu gift : gifts) {
                int quantity = gift.getQuantity();
                String giftMenuName = gift.getMenuName();
                OutputView.printBenefitPreview(form, giftMenuName, quantity);
            }
            return;
        }
        OutputView.printNonBenefitPreview();
    }

    private void eventBenefitPreview() {
        String form = previewTypeComment(BENEFIT_DETAILS);
        Map<String, Integer> benefitDiscounts = discount.getBenefitDiscounts();
        if (benefitDiscounts.isEmpty()) {
            OutputView.printNonBenefitPreview();
            return;
        }

        benefitDiscounts.forEach(
                (eventPolicy, benefitDiscount) -> OutputView.printBenefitPreview(
                        form, eventPolicy, -benefitDiscount
                )
        );
    }

    private void totalBenefitAmountPreview() {
        String form = previewTypeComment(TOTAL_BENEFIT_AMOUNT);

        int benefitAmount = discount.getTotalBenefitAmount();
        OutputView.printBenefitPreview(form, -benefitAmount);
    }

    private void totalAmountAfterDiscountPreview() {
        String form = previewTypeComment(ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT);
        int payment = order.getCalculateTotalOrderAmount() - discount.getTotalBenefitAmount();
        OutputView.printBenefitPreview(form, payment);
    }

    private void badgePreview() {
        String form = previewTypeComment(EVENT_BADGE);
        int benefitAmount = discount.getTotalBenefitAmount();
        if (benefitAmount > 20_000) {
            OutputView.printBenefitPreview(form, "산타");
        } else if (benefitAmount > 10_000) {
            OutputView.printBenefitPreview(form, "트리");
        } else if (benefitAmount > 5_000) {
            OutputView.printBenefitPreview(form, "별");
        }
        if (benefitAmount == 0) {
            OutputView.printNonBenefitPreview();
        }
        //    - [ ] 12월 이벤트 배지 출력 기능
        /**
         * <12월 이벤트 배지>
         * 없음
         * <12월 이벤트 배지>
         * 산타
         */
    }

    private String previewTypeComment(PreviewType previewType) {
        OutputView.printPreviewType(previewType);
        return previewType.getFormat();
    }
}
