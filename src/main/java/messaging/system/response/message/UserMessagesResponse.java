package messaging.system.response.message;

import messaging.system.model.message.UserMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessagesResponse {
    private List<UserMessage> messages;
    private String errorMessage;
    private boolean success;
}
