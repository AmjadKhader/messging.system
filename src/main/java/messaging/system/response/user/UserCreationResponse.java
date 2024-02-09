package messaging.system.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import messaging.system.model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationResponse {
    private User user;
    private String errorMessage;
    private boolean success;
}
