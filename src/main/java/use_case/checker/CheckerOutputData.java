package use_case.checker;

public class CheckerOutputData {
    private final String message;

    public CheckerOutputData(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }
}
