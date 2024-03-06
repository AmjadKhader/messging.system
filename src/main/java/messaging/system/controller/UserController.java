package messaging.system.controller;

import messaging.system.model.user.User;
import messaging.system.response.user.UserCreationResponse;
import messaging.system.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController()
@RequestMapping("/api/messaging-system/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(new UserCreationResponse(null,
                    "Body request is mandatory", false),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new UserCreationResponse(
                userService.createUser(user), "", true),
                HttpStatus.OK);
    }
}
