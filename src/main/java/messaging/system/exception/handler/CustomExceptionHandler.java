package messaging.system.exception.handler;

import messaging.system.exception.*;
import messaging.system.response.GeneralErrorResponse;
import messaging.system.response.UserNotFoundErrorResponse;
import messaging.system.response.message.SendMessageResponse;
import messaging.system.response.user.UserCreationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {AccountAlreadyExistException.class})
    public ResponseEntity<UserCreationResponse> handleAccountAlreadyExistException(AccountAlreadyExistException exception) {
        return new ResponseEntity<>(new UserCreationResponse(null, exception.getMessage(), false),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {GeneralException.class})
    public ResponseEntity<GeneralErrorResponse> handleGeneralException(GeneralException exception) {
        return new ResponseEntity<>(new GeneralErrorResponse(exception.getMessage(), false),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MessagesException.class})
    public ResponseEntity<SendMessageResponse> handleMessagesException(MessagesException exception) {
        return new ResponseEntity<>(new SendMessageResponse(null, exception.getMessage(), false),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<UserNotFoundErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(new UserNotFoundErrorResponse(exception.getMessage(), false, null, null),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WrongInputException.class})
    public ResponseEntity<UserCreationResponse> handleWrongInputException(WrongInputException exception) {
        return new ResponseEntity<>(new UserCreationResponse(null, exception.getMessage(), false),
                HttpStatus.BAD_REQUEST);
    }
}