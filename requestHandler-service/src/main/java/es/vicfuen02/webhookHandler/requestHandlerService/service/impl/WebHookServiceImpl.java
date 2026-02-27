package es.vicfuen02.webhookHandler.requestHandlerService.service.impl;

import es.vicfuen02.webhookHandler.requestHandlerService.service.KafkaMessagePublisher;
import es.vicfuen02.webhookHandler.requestHandlerService.service.WebHookService;
import es.vicfuen02.webhookHandler.requestHandlerService.service.model.WebHookEvent;
import es.vicfuen02.webhookHandler.requestHandlerService.service.model.WebHookModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebHookServiceImpl implements WebHookService {

    private final KafkaMessagePublisher<String, WebHookEvent> kafkaMessagePublisher;

    @Override
    public void webhook(WebHookModel webHookModel) {

        WebHookEvent event = WebHookEvent.builder()
                .webhookEventId(webHookModel.getEventId())
                .payload(webHookModel.getPayload())
                .receivedAt(webHookModel.getReceivedAt())
                .build();


        kafkaMessagePublisher.publishEvent(webHookModel.getTopic(),null, event);

    }

}
