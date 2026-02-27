package es.vicfuen02.webhookHandler.requestHandlerService.service.impl;

import es.vicfuen02.webhookHandler.requestHandlerService.service.KafkaMessagePublisher;
import es.vicfuen02.webhookHandler.requestHandlerService.service.model.BaseEvent;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;


@Component
@Slf4j
@AllArgsConstructor
public class KafkaMessagePublisherImpl<K extends Serializable, V extends BaseEvent> implements KafkaMessagePublisher<K, V> {


    private final KafkaTemplate<K, V> kafkaTemplate;


    @Override
    public void publishEvent(String topic, K key, V message) {

        log.info("KafkaMessagePublisherImpl.publishEvent INIT");

        CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topic, key, message);
        kafkaResultFuture.whenComplete((res, error) -> {
            if (error != null) {
                log.info("Kafka publisher callback ERROR ({})", res);
            } else {
                log.info("Kafka publisher callback OKAY ({})", res);
            }
        });

        log.info("KafkaMessagePublisherImpl.publishEvent END");

    }

    @PreDestroy
    public void close(){
        if(kafkaTemplate != null){
            log.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }

}
