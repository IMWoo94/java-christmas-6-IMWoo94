package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private InputView() {

    }

    public static String readReservationDate() {
        String date = Console.readLine();
        return date;
    }
}
