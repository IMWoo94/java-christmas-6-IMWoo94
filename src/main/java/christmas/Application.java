package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 식당 예약 안내 멘트 출력 기능
        OutputView.printOpening();
        OutputView.printRequestReservationDate();

        // 예약 일자 입력 기능
        String reservationDate = InputView.readReservationDate();
        System.out.println("reservationDate = " + reservationDate);
    }
}
