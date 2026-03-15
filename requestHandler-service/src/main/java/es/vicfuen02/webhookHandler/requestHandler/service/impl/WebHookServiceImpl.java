package es.vicfuen02.webhookHandler.requestHandler.service.impl;

import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.kafka.service.KafkaMessagePublisher;
import es.vicfuen02.webhookHandler.requestHandler.service.WebHookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class WebHookServiceImpl implements WebHookService {

    private final KafkaMessagePublisher<String, WebHookEvent> kafkaMessagePublisher;

    @Override
    public void webhook(WebHookModel webHookModel) {

        log.info("Service INIT");

        WebHookEvent event = WebHookEvent.builder()
                .webhookEventId(webHookModel.getEventId())
                .payload(webHookModel.getPayload())
                .receivedAt(new Date())
                .build();


        kafkaMessagePublisher.publishEvent(webHookModel.getTopic(),null, event);
        log.info("Service END");

    }

}
