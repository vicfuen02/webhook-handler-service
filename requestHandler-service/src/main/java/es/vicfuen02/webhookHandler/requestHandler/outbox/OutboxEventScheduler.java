package es.vicfuen02.webhookHandler.requestHandler.outbox;


import es.vicfuen02.webhookHandler.common.kafka.serializer.SerializerHelperImpl;
import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import es.vicfuen02.webhookHandler.requestHandler.kafka.service.KafkaMessagePublisher;
import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxEventHelperImpl;
import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class OutboxEventScheduler {


    private OutboxEventHelperImpl outboxEventHelper;
    private SerializerHelperImpl<WebHookEvent> serializerHelper;

    private final KafkaMessagePublisher<String, WebHookEvent> kafkaMessagePublisher;


    @Scheduled(fixedDelayString = "5000", initialDelayString="20000")
    @Transactional
    public void scheduler() {

        log.info("OutboxEventScheduler running...");
        List<OutboxModel> events = outboxEventHelper.getPendingOutboxEvents();

        for (OutboxModel event: events) {
            WebHookEvent webhook = serializerHelper.deserialize(event.getPayload(), WebHookEvent.class);
            kafkaMessagePublisher.publishEvent("webhook-topic", webhook.getWebhookEventId(), webhook);

            outboxEventHelper.markedAsDelivered(webhook.getWebhookEventId());
        }

    }


}
