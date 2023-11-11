package christmas.utils;

import static christmas.constants.ErrorMessage.INVALID_DATA;

import christmas.exception.InvalidDataException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOrderValidator {

    private static final String ORDER = "주문";
    // 정규식 패턴: (문자열 한글)-(숫자 1 ~ 20) or (문자열 한글)-(숫자 1 ~ 20),(문자열 한글)-(숫자 1 ~ 20)
    private static final String regex = "(([가-힣]+)-([1-9]|1[0-9]|20)){1}((:?,)([가-힣]+)-([1-9]|1[0-9]|20))*";
    private static final Pattern pattern = Pattern.compile(regex);

    private InputOrderValidator() {
    }

    public static void validateInputOrder(String inputOrder) {
        validateNull(inputOrder);
        validateEmpty(inputOrder);
        validateOrderFormat(inputOrder);
    }

    private static void validateNull(String inputOrder) {
        if (inputOrder == null) {
            throw new InvalidDataException(INVALID_DATA.getFormatMessage(ORDER));
        }
    }

    private static void validateEmpty(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new InvalidDataException(INVALID_DATA.getFormatMessage(ORDER));
        }
    }

    private static void validateOrderFormat(String inputOrder) {
        Matcher matcher = pattern.matcher(inputOrder);
        if (!matcher.matches()) {
            throw new InvalidDataException(INVALID_DATA.getFormatMessage(ORDER));
        }

    }
}
