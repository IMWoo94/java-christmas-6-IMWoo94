package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.exception.InvalidDataException;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;

public class RestaurantController {

    private ReservationDate reservationDate;
    private Orders order;
    private Discount discount;

    public RestaurantController() {
        openInit();
    }

    private void openInit() {
        // 식당 오픈 멘트
        OutputView.printOpening();
    }

    public void reservation() {
        // 예약 일자
        reservationDate = askReservationDate();

        // 주문
        order = askOrder();

        // 주문 내역 출력
        orderHistoryPrint();
    }

    private void orderHistoryPrint() {
        order.getOrderMenu()
                .forEach(OutputView::printOrderMenu);
    }

    private Orders askOrder() {
        try {
            // 주문 요청 안내 멘트
            OutputView.printRequestOrders();
            // 주문 입력 기능
            String readOrder = InputView.readOrders();
            // 입력된 주문 출력
            OutputView.printInputOrder(readOrder);
            // 주문 저장
            return new Orders(readOrder);
        } catch (InvalidDataException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return askOrder();
        }
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
}
