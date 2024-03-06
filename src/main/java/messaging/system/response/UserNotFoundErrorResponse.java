package messaging.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import messaging.system.model.message.Message;
import messaging.system.model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundErrorResponse {
    private String errorMessage;
    private boolean success;
    private Message message;
    private User user;
}
