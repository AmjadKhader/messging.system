package messaging.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralErrorResponse {
    private String errorMessage;
    private boolean success;
}
