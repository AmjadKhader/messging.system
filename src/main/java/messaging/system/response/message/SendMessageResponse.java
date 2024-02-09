package messaging.system.response.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import messaging.system.model.message.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {
    private Message message;
    private String errorMessage;
    private boolean success;
}
