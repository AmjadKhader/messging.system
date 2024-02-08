package messaging.system.exception;

public class AccountAlreadyExistException extends IllegalArgumentException {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
