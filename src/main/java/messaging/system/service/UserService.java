package messaging.system.service;

import messaging.system.exception.AccountAlreadyExistException;
import messaging.system.exception.GeneralException;
import messaging.system.exception.WrongInputException;
import messaging.system.model.user.User;
import messaging.system.model.user.UserEntity;
import messaging.system.repository.UserRepository;
import messaging.system.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static messaging.system.constant.Constants.*;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Validator validator;

    public User createUser(User user) {
        try {
            if (isBlank(user.getUsername()) || isBlank(user.getNickname())) {
                throw new WrongInputException(WRONG_INPUT_EXCEPTION_MESSAGE);
            }

            // search for the user in the DB, if exits then return false ...
            UserEntity userEntity = userRepository.findByNickname(user.getNickname().toLowerCase());

            if (!Objects.isNull(userEntity)) {
                throw new AccountAlreadyExistException(USER_CREATION_ERROR_EXCEPTION_MESSAGE);
            }

            if (!validator.isNicknameValid(user.getNickname().toLowerCase())) {
                throw new GeneralException(INVALID_NICKNAME_EXCEPTION_MESSAGE);
            }
            userRepository.save(new UserEntity(user.getNickname().toLowerCase(), user.getUsername()));
            return user;
        } catch (AccountAlreadyExistException accountAlreadyExistException) {
            throw new AccountAlreadyExistException(accountAlreadyExistException.getMessage());
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
}