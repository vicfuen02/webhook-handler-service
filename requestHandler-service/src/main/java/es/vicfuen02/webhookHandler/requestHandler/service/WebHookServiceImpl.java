package es.vicfuen02.webhookHandler.requestHandler.service;

import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.idempotency.IdempotencyEnum;
import es.vicfuen02.webhookHandler.requestHandler.idempotency.IdempotencyHelperImpl;
import es.vicfuen02.webhookHandler.requestHandler.mapper.WebHookServiceMapper;
import es.vicfuen02.webhookHandler.requestHandler.outbox.*;
import es.vicfuen02.webhookHandler.requestHandler.kafka.service.KafkaMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WebHookServiceImpl implements WebHookService {

    private final KafkaMessagePublisher<String, WebHookEvent> kafkaMessagePublisher;

    private OutboxEventHelperImpl outboxEventHelper;
    private IdempotencyHelperImpl idempotencyHelper;

    @Override
    @Transactional
    public void webhook(WebHookModel webHookModel) {

        log.info("Service INIT");


        outboxEventHelper.save(Long.valueOf(webHookModel.getEventId()),
                OutboxEventEnum.STARTED,
                webHookModel.getPayload()
        );

        idempotencyHelper.save(Long.valueOf(webHookModel.getEventId()),
                IdempotencyEnum.STARTED,
                "webhook-handler"
        );

        WebHookEvent event = WebHookEvent.builder()
                .webhookEventId(webHookModel.getEventId())
                .payload(webHookModel.getPayload())
                .receivedAt(new Date())
                .build();


        kafkaMessagePublisher.publishEvent(webHookModel.getTopic(),null, event);

        
        log.info("Service END");

    }



}
