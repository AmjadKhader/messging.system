package messaging.system.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "visible_name")
    private String visibleName;

    public UserEntity(String nickname, String visibleName) {
        this.nickname = nickname;
        this.visibleName = visibleName;
    }
}
