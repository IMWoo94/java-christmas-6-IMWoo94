package christmas.view;

import christmas.constants.biz.PreviewType;

public class OutputView {
    private OutputView() {
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printPreviewType(PreviewType type) {
        printNewLine();
        System.out.println(type.getType());
    }

}
