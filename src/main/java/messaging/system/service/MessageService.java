package messaging.system.service;

import messaging.system.exception.UserNotFoundException;
import messaging.system.messaging.producer.MessageProducer;
import messaging.system.model.message.Message;
import messaging.system.model.message.UserMessage;
import messaging.system.repository.UserMessageRepository;
import messaging.system.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static messaging.system.constant.Constants.SENDER_OR_RECEIVER_IS_NOT_FOUND;
import static messaging.system.constant.Constants.USER_NOT_FOUND;

@Service
public class MessageService {

    @Autowired
    UserMessageRepository messageRepository;
    @Autowired
    MessageProducer messageProducer;
    @Autowired
    Validator validator;

    public Message sendMessage(String senderId, String receiverId, String messageBody) {
        if (validator.isUserExist(Long.valueOf(senderId)) && validator.isUserExist(Long.valueOf(receiverId))) {
            return messageProducer.sendMessage(new Message(Long.valueOf(senderId), Long.valueOf(receiverId), messageBody));
        }
        throw new UserNotFoundException(SENDER_OR_RECEIVER_IS_NOT_FOUND);
    }

    public List<UserMessage> getUserSentMessages(String userId) {
        if (validator.isUserExist(Long.valueOf(userId))) {
            return messageRepository.findAllBySender(Long.valueOf(userId)).stream().map(
                    messageEntity ->
                            new UserMessage(
                                    messageEntity.getSender(),
                                    messageEntity.getReceiver(),
                                    messageEntity.getText())
            ).collect(Collectors.toList());
        }
        throw new UserNotFoundException(USER_NOT_FOUND);
    }

    public List<UserMessage> getUserMessages(String userId) {
        if (validator.isUserExist(Long.valueOf(userId))) {
            return messageRepository.findAllByReceiver(Long.valueOf(userId)).stream().map(
                    messageEntity ->
                            new UserMessage(
                                    messageEntity.getSender(),
                                    messageEntity.getReceiver(),
                                    messageEntity.getText())
            ).collect(Collectors.toList());
        }
        throw new UserNotFoundException(USER_NOT_FOUND);
    }

    public List<UserMessage> getUserMessagesFromUser(String userId, String senderId) {
        if (validator.isUserExist(Long.valueOf(userId))) {
            return messageRepository.findAllBySenderAndReceiver(Long.valueOf(senderId), Long.valueOf(userId)).stream().map(
                    messageEntity ->
                            new UserMessage(
                                    messageEntity.getSender(),
                                    messageEntity.getReceiver(),
                                    messageEntity.getText())
            ).collect(Collectors.toList());
        }
        throw new UserNotFoundException(USER_NOT_FOUND);
    }
}