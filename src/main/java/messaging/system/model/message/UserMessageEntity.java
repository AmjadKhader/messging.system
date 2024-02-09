package messaging.system.model.message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity(name = "messages")
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class UserMessageEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;
}
