package christmas;

import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 식당 예약 안내 멘트 출력 기능
        OutputView.printOpening();
        OutputView.printRequestReservationDate();

        // 예약 일자 입력 기능
        String readDate = InputView.readReservationDate();

        // 예약 일자 입력 값 숫자 타입으로 변환 기능
        LocalDate localDate = Parser.StringToLocalDate(readDate);

        // 예약 일자 저장 기능
        ReservationDate reservationDate = new ReservationDate(localDate);

        // 주문 요청 안내 멘트 출력 기능
        OutputView.printRequestOrders();

        // 주문 입력 기능
        String readOrder = InputView.readOrders();

        // 입력된 주문 출력
        OutputView.printInputOrder(readOrder);

        // 주문 저장
        Orders orders = new Orders(readOrder);

        // 주문 내역 출력
        Map<String, String> orderMenu = orders.getOrderMenu();
        orderMenu.forEach(OutputView::printOrderMenu);

    }
}
