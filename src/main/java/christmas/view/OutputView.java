package christmas.view;

import static christmas.constants.message.PrintMessage.NONE;
import static christmas.constants.message.PrintMessage.OPENING;
import static christmas.constants.message.PrintMessage.REQUEST_ORDER;
import static christmas.constants.message.PrintMessage.REQUEST_RESERVATION;

import christmas.constants.biz.PreviewType;
import christmas.constants.message.PrintMessage;
import java.time.LocalDate;

public class OutputView {
    private OutputView() {
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printNonBenefitPreview() {
        System.out.println(NONE.getMessage());
    }

    public static void printOpening() {
        System.out.println(OPENING.getMessage());
    }

    public static void printRequestReservationDate() {
        System.out.println(REQUEST_RESERVATION.getMessage());
    }

    public static void printRequestOrders() {
        System.out.println(REQUEST_ORDER.getMessage());
    }

    public static void printInputOrder(String order) {
        System.out.println(order);
    }

    public static void printPreviewCommnet(LocalDate date) {
        System.out.println(PrintMessage.BENEFIT_PREVIEW.getFormatMessage(date.getMonthValue(), date.getDayOfMonth()));
    }

    public static void printPreviewType(PreviewType type) {
        printNewLine();
        System.out.println(type.getType());
    }

    public static void printBenefitPreview(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

}
