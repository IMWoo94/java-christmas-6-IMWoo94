package christmas;

import christmas.domain.Orders;
import christmas.domain.ReservationDate;
import christmas.utils.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 식당 예약 안내 멘트 출력 기능
        OutputView.printOpening();
        OutputView.printRequestReservationDate();

        // 예약 일자 입력 기능
        String readDate = InputView.readReservationDate();
        System.out.println("reservationDate = " + readDate);

        // 예약 일자 입력 값 숫자 타입으로 변환 기능
        LocalDate localDate = Parser.StringToLocalDate(readDate);
        System.out.println("localDate = " + localDate);

        // 예약 일자 저장 기능
        ReservationDate reservationDate = new ReservationDate(localDate);

        // 주문 요청 안내 멘트 출력 기능
        OutputView.printRequestOrders();

        // 주문 입력 기능
        String readOrder = InputView.readOrders();
        System.out.println("readOrder = " + readOrder);

        // 주문 저장
        Orders orders = new Orders(readOrder);
        // 주문 중복 및 확인

    }
}
