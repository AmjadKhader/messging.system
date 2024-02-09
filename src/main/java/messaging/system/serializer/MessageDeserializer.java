package messaging.system.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import messaging.system.model.message.Message;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MessageDeserializer implements Deserializer<Message> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Message deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Message.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Message");
        }
    }

    @Override
    public void close() {
    }
}
