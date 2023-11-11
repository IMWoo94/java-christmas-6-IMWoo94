package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.InputDateValidator;

public class InputView {
    private InputView() {

    }

    public static String readReservationDate() {
        String date = Console.readLine();
        InputDateValidator.validateInputDate(date);
        return date;
    }
}
