package messaging.system.messaging.producer;

import messaging.system.model.message.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        kafkaTemplate.send("messages", message);
    }
}
