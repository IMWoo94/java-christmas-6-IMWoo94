package christmas.constants;

public enum ErrorMessage {
    ERROR_PREFIX(String.format("%s ", "[ERROR]")),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
