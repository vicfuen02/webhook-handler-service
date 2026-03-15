package es.vicfuen02.webhookHandler.requestHandler.service;

import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.mapper.WebHookServiceMapper;
import es.vicfuen02.webhookHandler.requestHandler.repository.entity.OutboxEntity;
import es.vicfuen02.webhookHandler.requestHandler.kafka.service.KafkaMessagePublisher;
import es.vicfuen02.webhookHandler.requestHandler.repository.OutboxJpaRepository;
import es.vicfuen02.webhookHandler.requestHandler.service.model.OutboxModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WebHookServiceImpl implements WebHookService {

    private final KafkaMessagePublisher<String, WebHookEvent> kafkaMessagePublisher;

    private OutboxJpaRepository outboxJpaRepository;
    private WebHookServiceMapper webHookServiceMapper;

    @Override
    public void webhook(WebHookModel webHookModel) {

        log.info("Service INIT");


        outboxJpaRepository.save(
                OutboxEntity.builder()
                        .webHookId(Long.valueOf(webHookModel.getEventId()))
                        .payload(webHookModel.getPayload())
                .build()
        );


        WebHookEvent event = WebHookEvent.builder()
                .webhookEventId(webHookModel.getEventId())
                .payload(webHookModel.getPayload())
                .receivedAt(new Date())
                .build();


        kafkaMessagePublisher.publishEvent(webHookModel.getTopic(),null, event);

        
        log.info("Service END");

    }


    @Override
    public List<OutboxModel> getOutboxEvents() {
        return webHookServiceMapper.toModelList(
                outboxJpaRepository.findAll()
        );
    }

}
