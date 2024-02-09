package messaging.system.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import messaging.system.model.message.UserMessageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserMessageRepository {
    private final EntityManager entityManager;

    public UserMessageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserMessageEntity> findAllBySender(Long sender) {
        TypedQuery<UserMessageEntity> q = entityManager.createQuery(
                "SELECT CAST(message.id AS int) as id, message.text as text, " +
                        "sender.nickname as sender, receiver.nickname as receiver " +
                        "from messages message " +
                        "inner join users sender " +
                        "on CAST(sender.id AS int)= CAST(message.sender AS int)" +
                        "inner join users receiver " +
                        "on CAST(receiver.id AS int) = CAST(message.receiver AS int) " +
                        "where CAST(sender.id AS int) = CAST(" + sender + " AS int)", UserMessageEntity.class);
        return q.getResultList();
    }

    public List<UserMessageEntity> findAllByReceiver(Long receiver) {
        TypedQuery<UserMessageEntity> q = entityManager.createQuery(
                "SELECT CAST(message.id AS int) as id, message.text as text, " +
                        "sender.nickname as sender, receiver.nickname as receiver " +
                        "from messages message " +
                        "inner join users sender " +
                        "on CAST(sender.id AS int)= CAST(message.sender AS int)" +
                        "inner join users receiver " +
                        "on CAST(receiver.id AS int) = CAST(message.receiver AS int) " +
                        "where CAST(receiver.id AS int) = CAST(" + receiver + " AS int)", UserMessageEntity.class);
        return q.getResultList();
    }

    public List<UserMessageEntity> findAllBySenderAndReceiver(Long sender, Long receiver) {
        TypedQuery<UserMessageEntity> q = entityManager.createQuery(
                "SELECT CAST(message.id AS int) as id, message.text as text, " +
                        "sender.nickname as sender, receiver.nickname as receiver " +
                        "from messages message " +
                        "inner join users sender " +
                        "on CAST(sender.id AS int)= CAST(message.sender AS int)" +
                        "inner join users receiver " +
                        "on CAST(receiver.id AS int) = CAST(message.receiver AS int) " +
                        "where CAST(sender.id AS int) = CAST(" + sender + " AS int) " +
                        "AND CAST(receiver.id AS int) = CAST(" + receiver + " AS int)", UserMessageEntity.class);
        return q.getResultList();
    }
}
