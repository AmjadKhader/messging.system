package messaging.system.service;

import messaging.system.exception.GeneralException;
import messaging.system.exception.UserNotFoundException;
import messaging.system.messaging.producer.MessageProducer;
import messaging.system.model.message.Message;
import messaging.system.model.message.UserMessage;
import messaging.system.repository.UserMessageRepository;
import messaging.system.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static messaging.system.constant.Constants.SENDER_OR_RECEIVER_IS_NOT_FOUND_EXCEPTION_MESSAGE;
import static messaging.system.constant.Constants.USER_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class MessageService {

    private final UserMessageRepository messageRepository;
    private final MessageProducer messageProducer;
    private final Validator validator;

    public MessageService(UserMessageRepository messageRepository,
                          MessageProducer messageProducer,
                          Validator validator) {
        this.messageRepository = messageRepository;
        this.messageProducer = messageProducer;
        this.validator = validator;
    }

    public void sendMessage(String senderId, String receiverId, String messageBody) {
        try {
            if (validator.isUserExist(Long.valueOf(senderId)) && validator.isUserExist(Long.valueOf(receiverId))) {
                messageProducer.sendMessage(new Message(Long.valueOf(senderId), Long.valueOf(receiverId), messageBody));
                return;
            }
            throw new UserNotFoundException(SENDER_OR_RECEIVER_IS_NOT_FOUND_EXCEPTION_MESSAGE);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    public List<UserMessage> getUserSentMessages(String userId) {
        try {
            if (validator.isUserExist(Long.valueOf(userId))) {
                return messageRepository.findAllBySender(Long.valueOf(userId)).stream().map(
                        messageEntity ->
                                new UserMessage(
                                        messageEntity.getSender(),
                                        messageEntity.getReceiver(),
                                        messageEntity.getText())
                ).collect(Collectors.toList());
            }
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    public List<UserMessage> getUserMessages(String userId) {
        try {
            if (validator.isUserExist(Long.valueOf(userId))) {
                return messageRepository.findAllByReceiver(Long.valueOf(userId)).stream().map(
                        messageEntity ->
                                new UserMessage(
                                        messageEntity.getSender(),
                                        messageEntity.getReceiver(),
                                        messageEntity.getText())
                ).collect(Collectors.toList());
            }
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    public List<UserMessage> getUserMessagesFromUser(String userId, String senderId) {
        try {
            if (validator.isUserExist(Long.valueOf(userId))) {
                return messageRepository.findAllBySenderAndReceiver(Long.valueOf(senderId), Long.valueOf(userId)).stream().map(
                        messageEntity ->
                                new UserMessage(
                                        messageEntity.getSender(),
                                        messageEntity.getReceiver(),
                                        messageEntity.getText())
                ).collect(Collectors.toList());
            }
            throw new UserNotFoundException(USER_NOT_FOUND_EXCEPTION_MESSAGE);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }
}