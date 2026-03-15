package es.vicfuen02.webhookHandler.requestHandler.repository;

import es.vicfuen02.webhookHandler.requestHandler.repository.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {
}
