package messaging.system.constant;

public class Constants {
    public static final String USER_CREATION_ERROR_EXCEPTION_MESSAGE = "Nickname is reserved";
    public static final String SAME_SENDER_AND_RECEIVER_EXCEPTION_MESSAGE = "User can't send message to himself";
    public static final String MESSAGE_IS_BLANK_EXCEPTION_MESSAGE = "Can't send empty message";
    public static final String INVALID_NICKNAME_EXCEPTION_MESSAGE = "Nickname should not contain symbols";
    public static final String SENDER_OR_RECEIVER_IS_NOT_FOUND_EXCEPTION_MESSAGE = "Sender or receiver is not found";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User not found";
    public static final String WRONG_INPUT_EXCEPTION_MESSAGE = "Username or nickname is empty";

    private Constants() {
    }
}
