package messaging.system.controller;

import messaging.system.controller.UserController;
import messaging.system.model.user.User;
import messaging.system.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        when(userService.createUser(any())).thenReturn(new User("nickname", "username"));

        ResponseEntity<?> result = userController.createUser(new User("nickname", "username"));
        Assertions.assertEquals(200, result.getStatusCode().value());
    }
}