package christmas;

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
        String reservationDate = InputView.readReservationDate();
        System.out.println("reservationDate = " + reservationDate);

        // 예약 일자 입력 값 숫자 타입으로 변환 기능
        LocalDate localDate = Parser.StringToLocalDate(reservationDate);
        System.out.println("localDate = " + localDate);

    }
}
