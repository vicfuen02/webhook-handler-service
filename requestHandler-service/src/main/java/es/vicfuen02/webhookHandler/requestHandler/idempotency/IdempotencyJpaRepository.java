package es.vicfuen02.webhookHandler.requestHandler.idempotency;

import es.vicfuen02.webhookHandler.requestHandler.outbox.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdempotencyJpaRepository extends JpaRepository<IdempotencyEntity, Long> {

    IdempotencyEntity findByIdempotentIdAndService(Long idempotentId, String service);

}
