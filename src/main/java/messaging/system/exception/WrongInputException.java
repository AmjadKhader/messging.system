package messaging.system.exception;

public class WrongInputException extends IllegalArgumentException {
    public WrongInputException(String message) {
        super(message);
    }
}
