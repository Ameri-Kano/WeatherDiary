package amerikano.weatherdiary.error;

public class InvalidDate extends RuntimeException {
    private final String description;
    private static final String MESSAGE = "날짜가 너무 과거이거나 미래입니다.";

    public InvalidDate(String description) {
        super(MESSAGE);
        this.description = description;
    }
}
