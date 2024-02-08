package messaging.system.model.message;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class MessageEntity implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private Long sender;

    @Column(name = "receiver")
    private Long receiver;

    @Column(name = "text")
    private String text;

    public MessageEntity(Message receivedMessage) {
        this.receiver = receivedMessage.getReceiver();
        this.sender = receivedMessage.getSender();
        this.text = receivedMessage.getText();
    }
}
