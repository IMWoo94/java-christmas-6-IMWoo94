package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATA;

import christmas.exception.InvalidDateException;

public class InputOrderValidator {

    private static final String ORDER = "주문";

    private InputOrderValidator() {
    }

    public static void validateInputOrder(String inputOrder) {
        validateNull(inputOrder);
        validateEmpty(inputOrder);
    }

    private static void validateNull(String inputOrder) {
        if (inputOrder == null) {
            throw new InvalidDateException(INVALID_DATA.getFormatMessage(ORDER));
        }
    }

    private static void validateEmpty(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new InvalidDateException(INVALID_DATA.getFormatMessage(ORDER));
        }
    }
}
