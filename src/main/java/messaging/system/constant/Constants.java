package messaging.system.constant;

public class Constants {
    public static final String USER_CREATION_ERROR = "Nickname is reserved";
    public static final String SAME_SENDER_AND_RECEIVER = "User can't send message to himself";
    public static final String MESSAGE_IS_BLANK = "Can't send empty message";
    public static final String INVALID_NICKNAME = "Nickname should not contain symbols";
    public static final String SENDER_OR_RECEIVER_IS_NOT_FOUND = "Sender or receiver is not found";
    public static final String USER_NOT_FOUND = "User not found";

    private Constants() {
    }
}
