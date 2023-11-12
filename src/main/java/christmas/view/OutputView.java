package christmas.view;

import christmas.constants.PreviewType;
import java.time.LocalDate;

public class OutputView {
    private OutputView() {
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printOpening() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printRequestReservationDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printRequestOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printInputOrder(String order) {
        System.out.println(order);
    }

    public static void printPreviewCommnet(LocalDate date) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date.getMonthValue(), date.getDayOfMonth());
    }

    public static void printPreviewType(PreviewType type) {
        printNewLine();
        System.out.println(type.getType());
    }

    public static void printPreview(String format) {
        System.out.println(format);
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

}
