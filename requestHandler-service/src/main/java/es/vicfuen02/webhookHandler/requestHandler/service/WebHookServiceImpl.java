package es.vicfuen02.webhookHandler.requestHandler.service;

import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyEnum;
import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyHelperImpl;
import es.vicfuen02.webhookHandler.common.kafka.serializer.SerializerHelperImpl;
import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxEventEnum;
import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxEventHelperImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class WebHookServiceImpl implements WebHookService {


    private OutboxEventHelperImpl outboxEventHelper;
    private IdempotencyHelperImpl idempotencyHelper;
    private SerializerHelperImpl<WebHookEvent> serializerHelper;

    @Override
    @Transactional
    public void webhook(WebHookModel webHookModel) {

        log.info("WebHookServiceImpl INIT");

        if (idempotencyHelper.getIdempotentById(webHookModel.getEventId(), "webhook-handler-producer").isPresent()) {
            log.info("Event already received. Event id: {}", webHookModel.getEventId());
            return;
        }

        WebHookEvent event = WebHookEvent.builder()
                .webhookEventId(webHookModel.getEventId())
                .payload(webHookModel.getPayload())
                .receivedAt(new Date())
                .build();

        outboxEventHelper.save(webHookModel.getEventId(),
                OutboxEventEnum.PENDING,
                serializerHelper.serialize(event)
        );

        idempotencyHelper.save(webHookModel.getEventId(),
                IdempotencyEnum.RECEIVED,
                "webhook-handler-producer"
        );

        log.info("WebHookServiceImpl END");

    }



}
