package messaging.system.controller;

import messaging.system.model.message.UserMessage;
import messaging.system.request.MessageRequest;
import messaging.system.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class MessageControllerTest {
    @Mock
    MessageService messageService;
    @InjectMocks
    MessageController messageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        ResponseEntity<?> result = messageController.sendMessage(new MessageRequest("message", "receiver"), "senderId");

        Assertions.assertEquals(204, result.getStatusCode().value());
    }

    @Test
    void testRetrieveMessagesISent() {
        when(messageService.getUserSentMessages(anyString())).thenReturn(List.of(new UserMessage("sender", "receiver", "text")));

        ResponseEntity<?> result = messageController.retrieveMessagesISent("userId");
        Assertions.assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testRetrieveMessagesIReceived() {
        when(messageService.getUserMessages(anyString())).thenReturn(List.of(new UserMessage("sender", "receiver", "text")));

        ResponseEntity<?> result = messageController.retrieveMessagesIReceived("userId");
        Assertions.assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testRetrieveMessagesIReceivedFromUser() {
        when(messageService.getUserMessagesFromUser(anyString(), anyString())).thenReturn(List.of(new UserMessage("sender", "receiver", "text")));

        ResponseEntity<?> result = messageController.retrieveMessagesIReceivedFromUser("userId", "senderId");
        Assertions.assertEquals(200, result.getStatusCode().value());
    }
}
