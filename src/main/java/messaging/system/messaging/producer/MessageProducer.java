package messaging.system.messaging.producer;

import messaging.system.model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message) {
        kafkaTemplate.send("messages", message);
    }
}
