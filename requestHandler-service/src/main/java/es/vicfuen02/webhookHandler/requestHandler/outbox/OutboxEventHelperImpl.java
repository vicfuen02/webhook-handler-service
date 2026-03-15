package es.vicfuen02.webhookHandler.requestHandler.outbox;

import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(Long id, OutboxEventEnum status, String payload) {
        log.info("Saving in outbox table INIT");

        outboxJpaRepository.save(
                OutboxEntity.builder()
                        .eventId(id)
                        .payload(payload)
                        .status(status)
                .build()
        );
        log.info("Saving in outbox table END");
    }

    public List<OutboxModel> getOutboxEventsToProcess(OutboxEventEnum status) {
        log.info("Getting outbox messages to process INIT");
        return outboxEventMapper.toModelList(
                outboxJpaRepository.findByStatus(status)
        );
    }





}
