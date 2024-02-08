package messaging.system.response.message;

import messaging.system.model.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageResponse {
    private Message message;
    private String errorMessage;
    private boolean success;
}
