package christmas.constants;

public enum ErrorMessage {
    ERROR_PREFIX(String.format("%s ", "[ERROR]")),
    INVALID_DATA("유효하지 않은 %s입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormatMessage(String value) {
        return String.format(getMessage(), value);
    }
}
