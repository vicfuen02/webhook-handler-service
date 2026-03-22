package es.vicfuen02.webhookHandler.consumer.kafka.service;

import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyEnum;
import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyHelperImpl;
import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaMessageListenerImpl implements KafkaMessageListener<WebHookEvent> {


    private final IdempotencyHelperImpl idempotencyHelper;


    @Transactional
    public void acceptEvent(WebHookEvent event) throws Exception {
        log.info("KafkaMessageListenerImpl event accepted");
        if (idempotencyHelper.getIdempotentById(event.getWebhookEventId(), "webhook-handler-consumer").isPresent()) {
            log.info("Event already received. Event id: {}", event.getWebhookEventId());
            return;
        }

        processBusinessEvent(event.getPayload());

        idempotencyHelper.save(event.getWebhookEventId(),
                IdempotencyEnum.RECEIVED,
                "webhook-handler-consumer"
        );

        log.info("KafkaMessageListenerImpl event processed");

    }

    private void processBusinessEvent(String payload) throws InterruptedException {
        Thread.sleep(5000L);
    }



}
