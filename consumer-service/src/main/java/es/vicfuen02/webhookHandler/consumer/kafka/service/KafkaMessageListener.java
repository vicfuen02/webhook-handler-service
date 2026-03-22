package es.vicfuen02.webhookHandler.consumer.kafka.service;

import es.vicfuen02.webhookHandler.common.kafka.model.BaseEvent;

public interface KafkaMessageListener<T extends BaseEvent> {

    void acceptEvent(T event) throws Exception;

}
