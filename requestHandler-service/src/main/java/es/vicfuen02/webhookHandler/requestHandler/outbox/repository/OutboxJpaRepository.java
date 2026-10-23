package es.vicfuen02.webhookHandler.requestHandler.outbox.repository;

import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxEventEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {

    List<OutboxEntity> findByStatus(OutboxEventEnum status);
    Optional<OutboxEntity> findByOutboxId(String outboxId);

}
