package messaging.system.controller;

import messaging.system.exception.GeneralException;
import messaging.system.exception.MessagesException;
import messaging.system.exception.UserNotFoundException;
import messaging.system.request.MessageRequest;
import messaging.system.response.message.SendMessageResponse;
import messaging.system.response.message.UserMessagesResponse;
import messaging.system.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static messaging.system.constant.Constants.MESSAGE_IS_BLANK;
import static messaging.system.constant.Constants.SAME_SENDER_AND_RECEIVER;

@RestController()
@RequestMapping("/api/messaging-system/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    // Send message to user
    @PostMapping(value = "send/{sender_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(
            @RequestBody MessageRequest messageBody,
            @PathVariable("sender_id") String senderId
    ) {
        try {
            if (Objects.isNull(messageBody) || messageBody.getMessage().isBlank()) {
                throw new MessagesException(MESSAGE_IS_BLANK);
            }
            if (senderId.equals(messageBody.getReceiver())) {
                throw new MessagesException(SAME_SENDER_AND_RECEIVER);
            }
            return new ResponseEntity<>(new SendMessageResponse(
                    messageService.sendMessage(senderId, messageBody.getReceiver(), messageBody.getMessage()), "", true),
                    HttpStatus.NO_CONTENT);

        } catch (UserNotFoundException | MessagesException e) {
            return new ResponseEntity<>(new SendMessageResponse(null, e.getMessage(), false),
                    HttpStatus.BAD_REQUEST);
        } catch (GeneralException e) {
            return new ResponseEntity<>(new SendMessageResponse(null, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // View all messages I sent
    @GetMapping(value = "view/send/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrieveMessagesISent(@PathVariable("user_id") String userId) {
        try {
            return new ResponseEntity<>(new UserMessagesResponse(
                    messageService.getUserSentMessages(userId), "", true),
                    HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.BAD_REQUEST);
        } catch (GeneralException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // View all messages I received
    @GetMapping(value = "view/receive/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrieveMessagesIReceived(@PathVariable("user_id") String userId) {
        try {
            return new ResponseEntity<>(new UserMessagesResponse(
                    messageService.getUserMessages(userId), "", true),
                    HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.BAD_REQUEST);
        } catch (GeneralException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // View all messages I received from particular user
    @GetMapping(value = "view/{user_id}/receive/{sender_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrieveMessagesIReceivedFromUser(
            @PathVariable("user_id") String userId,
            @PathVariable("sender_id") String senderId
    ) {
        try {
            return new ResponseEntity<>(new UserMessagesResponse(
                    messageService.getUserMessagesFromUser(userId, senderId), "", true),
                    HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.BAD_REQUEST);
        } catch (GeneralException e) {
            return new ResponseEntity<>(new UserMessagesResponse(null, e.getMessage(), false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
