package messaging.system.messaging.consumer;

import messaging.system.model.message.Message;
import messaging.system.model.message.MessageEntity;
import messaging.system.repository.MessageRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    MessageRepository messageRepository;

    @KafkaListener(topics = "messages", groupId = "messages")
    public void listen(ConsumerRecord<String, Message> message) {
        Message receivedMessage = message.value();
        messageRepository.save(new MessageEntity(receivedMessage));
    }
}
