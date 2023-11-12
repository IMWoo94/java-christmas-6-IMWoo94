package christmas.controller;

import static christmas.constants.PreviewType.ORDER_MENU;

import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidDataException;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
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
        orderMenuPrint();
    }

    private void orderMenuPrint() {
        OutputView.printPreviewType(ORDER_MENU);
        String form = ORDER_MENU.getFormat();

        Map<String, String> orderMenu = order.getOrderMenu();

        orderMenu.forEach(
                (menu, quantity) -> OutputView.printPreview(String.format(form, menu, quantity))
        );
    }
}
