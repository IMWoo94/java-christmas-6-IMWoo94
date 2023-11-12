package christmas.utils;

import static christmas.constants.GlobalConstant.ORDER;

import christmas.exception.InvalidDataException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOrderValidator {

    // 정규식 패턴: (문자열 한글)-(숫자 1 이상) or (문자열 한글)-(숫자 1 이상),(문자열 한글)-(숫자 1 이상)
    private static final String INPUT_FORMAT_REGEX = "(([가-힣]+)-([1-9]+[0-9]*)){1}((:?,)([가-힣]+)-([1-9]+[0-9]*))*";
    private static final Pattern PATTERN = Pattern.compile(INPUT_FORMAT_REGEX);

    private InputOrderValidator() {
    }

    public static void validateInputOrder(String inputOrder) {
        validateNull(inputOrder);
        validateEmpty(inputOrder);
        validateOrderFormat(inputOrder);
    }

    private static void validateNull(String inputOrder) {
        if (inputOrder == null) {
            throw InvalidDataException.from(ORDER.getValue());
        }
    }

    private static void validateEmpty(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw InvalidDataException.from(ORDER.getValue());
        }
    }

    private static void validateOrderFormat(String inputOrder) {
        Matcher matcher = PATTERN.matcher(inputOrder);
        if (!matcher.matches()) {
            throw InvalidDataException.from(ORDER.getValue());
        }
    }
}
