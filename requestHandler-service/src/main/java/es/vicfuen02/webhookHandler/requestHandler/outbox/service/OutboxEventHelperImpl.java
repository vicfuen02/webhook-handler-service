package es.vicfuen02.webhookHandler.requestHandler.outbox.service;

import es.vicfuen02.webhookHandler.requestHandler.outbox.mapper.OutboxEventMapper;
import es.vicfuen02.webhookHandler.requestHandler.outbox.repository.OutboxEntity;
import es.vicfuen02.webhookHandler.requestHandler.outbox.repository.OutboxJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OutboxEventHelperImpl {


    private OutboxJpaRepository outboxJpaRepository;
    private OutboxEventMapper outboxEventMapper;


    public List<OutboxModel> getOutboxEvents() {
        log.info("Getting outbox messages INIT");
        return outboxEventMapper.toModelList(
                outboxJpaRepository.findAll()
        );
    }
    public Optional<OutboxModel> getOutboxEventById(String outboxId) {
        log.info("Getting outbox message by id INIT");
        return outboxJpaRepository.findByOutboxId(outboxId)
                .map(outboxEventMapper::toModel);
    }

    @Transactional
    public OutboxModel save(String outboxId, OutboxEventEnum status, String payload) {
        log.info("Saving in outbox table");
        return outboxEventMapper.toModel(
            outboxJpaRepository.save(
                    OutboxEntity.builder()
                            .outboxId(outboxId)
                            .payload(payload)
                            .status(status)
                    .build()
            )
        );
    }

    @Transactional
    public OutboxModel markedAsDelivered(String outboxId) {
        log.info("MarkedAsDelivered in outbox table");
        return outboxJpaRepository.findByOutboxId(outboxId)
                .map(outbox -> {
                    outbox.setStatus(OutboxEventEnum.COMPLETED);
                    return outboxEventMapper.toModel(
                            outboxJpaRepository.save(outbox)
                    );
                })
                .orElseThrow(() -> new RuntimeException("Outbox not found with id: " + outboxId));
    }

    public List<OutboxModel> getPendingOutboxEvents() {
        log.info("Getting outbox messages to process INIT");
        return outboxEventMapper.toModelList(
                outboxJpaRepository.findByStatus(OutboxEventEnum.PENDING)
        );
    }





}
