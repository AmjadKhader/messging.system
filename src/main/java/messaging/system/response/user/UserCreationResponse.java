package messaging.system.response.user;

import messaging.system.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationResponse {
    private User user;
    private String errorMessage;
    private boolean success;
}
