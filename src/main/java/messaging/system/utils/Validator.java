package messaging.system.utils;

import messaging.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {

    @Autowired
    UserRepository userRepository;

    public boolean isNicknameValid(String nickname) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(nickname);
        return !m.find();
    }

    public boolean isUserExist(Long id) {
        return userRepository.findById(id).isPresent();
    }
}
