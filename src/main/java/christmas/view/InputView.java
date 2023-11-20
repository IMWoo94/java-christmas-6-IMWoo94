package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.InputDateValidator;
import christmas.utils.InputOrderValidator;

public class InputView {
    private InputView() {

    }

    public static String readReservationDate() {
        String date = Console.readLine();
        InputDateValidator.validateInputDate(date);
        return date;
    }

    public static String readOrders() {
        String order = Console.readLine();
        InputOrderValidator.validateInputOrder(order);
        return order;
    }
}
