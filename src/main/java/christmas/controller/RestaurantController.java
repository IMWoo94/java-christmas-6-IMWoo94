package christmas.controller;

import static christmas.constants.biz.PreviewType.BENEFIT_DETAILS;
import static christmas.constants.biz.PreviewType.EVENT_BADGE;
import static christmas.constants.biz.PreviewType.GIFT_MENU;
import static christmas.constants.biz.PreviewType.ORDER_MENU;
import static christmas.constants.biz.PreviewType.TOTAL_BENEFIT_AMOUNT;
import static christmas.constants.biz.PreviewType.TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT;
import static christmas.constants.biz.PreviewType.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT;
import static christmas.constants.message.PrintMessage.BENEFIT_PREVIEW;
import static christmas.constants.message.PrintMessage.NONE;
import static christmas.constants.message.PrintMessage.OPENING;
import static christmas.constants.message.PrintMessage.REQUEST_ORDER;
import static christmas.constants.message.PrintMessage.REQUEST_RESERVATION;
import static christmas.view.OutputView.printMessage;
import static christmas.view.OutputView.printPreviewType;

import christmas.constants.biz.Badges;
import christmas.constants.biz.EventPolicy;
import christmas.constants.biz.PreviewType;
import christmas.constants.biz.VariousMenu;
import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidDataException;
import christmas.utils.Parser;
import christmas.view.InputView;
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
        printMessage(OPENING.getMessage());
    }

    private void reservation() {
        reservationDate = askReservationDate();
        order = askOrder();
    }

    private ReservationDate askReservationDate() {
        try {
            printMessage(REQUEST_RESERVATION.getMessage());

            String readDate = InputView.readReservationDate();
            LocalDate date = Parser.StringToLocalDate(readDate);
            return new ReservationDate(date);
        } catch (InvalidDataException e) {
            printMessage(e.getMessage());
            return askReservationDate();
        }
    }

    private Orders askOrder() {
        try {
            printMessage(REQUEST_ORDER.getMessage());
            String readOrder = InputView.readOrders();

            return new Orders(readOrder);
        } catch (InvalidDataException e) {
            printMessage(e.getMessage());
            return askOrder();
        }
    }

    private void discountsApply() {
        printMessage(
                BENEFIT_PREVIEW.getFormatMessage(reservationDate.getMonth(), reservationDate.getDays()));
        discount = new Discount(order, reservationDate);
    }

    private void eventPreviews() {
        orderMenuPreview();
        totalAmountBeforeDiscountPreview();
        giftPreview();
        eventBenefitPreview();
        totalBenefitAmountPreview();
        totalAmountAfterDiscountPreview();
        badgePreview();
    }

    private void orderMenuPreview() {
        String form = previewTypeComment(ORDER_MENU);

        Map<String, String> orderMenu = order.getOrderMenu();

        orderMenu.forEach(
                (menu, quantity) -> printMessage(ORDER_MENU.getFormatMessage(menu, quantity))
        );
    }

    private void totalAmountBeforeDiscountPreview() {
        String form = previewTypeComment(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT);

        int orderPrice = order.getCalculateTotalOrderAmount();

        printMessage(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getFormatMessage(orderPrice));
    }

    private void giftPreview() {
        String form = previewTypeComment(GIFT_MENU);
        int price = order.getCalculateTotalOrderAmount();

        if (EventPolicy.checkGiveawayEventConditions(price)) {
            List<VariousMenu> gifts = VariousMenu.getGifts();
            for (VariousMenu gift : gifts) {
                int quantity = gift.getQuantity();
                String giftMenuName = gift.getMenuName();
                printMessage(GIFT_MENU.getFormatMessage(giftMenuName, quantity));
            }
            return;
        }
        printMessage(NONE.getMessage());
    }

    private void eventBenefitPreview() {
        String form = previewTypeComment(BENEFIT_DETAILS);
        Map<String, Integer> benefitDiscounts = discount.getBenefitDiscounts();
        if (benefitDiscounts.isEmpty()) {
            printMessage(NONE.getMessage());
            return;
        }

        benefitDiscounts.forEach(
                (eventPolicy, benefitDiscount) ->
                        printMessage(BENEFIT_DETAILS.getFormatMessage(eventPolicy, -benefitDiscount))
        );
    }

    private void totalBenefitAmountPreview() {
        String form = previewTypeComment(TOTAL_BENEFIT_AMOUNT);

        int benefitAmount = discount.getTotalBenefitAmount();
        printMessage(TOTAL_BENEFIT_AMOUNT.getFormatMessage(-benefitAmount));
    }

    private void totalAmountAfterDiscountPreview() {
        String form = previewTypeComment(TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT);

        int payment = order.getCalculateTotalOrderAmount() - discount.getTotalBenefitAmount();

        printMessage(TOTAL_ORDER_AMOUNT_AFTER_DISCOUNT.getFormatMessage(payment));
    }

    private void badgePreview() {
        String form = previewTypeComment(EVENT_BADGE);

        int benefitAmount = discount.getTotalBenefitAmount();

        Badges badge = Badges.getContionsBadge(benefitAmount);

        printMessage(EVENT_BADGE.getFormatMessage(badge.getName()));
    }

    private String previewTypeComment(PreviewType previewType) {
        printPreviewType(previewType);
        return previewType.getFormat();
    }
}
