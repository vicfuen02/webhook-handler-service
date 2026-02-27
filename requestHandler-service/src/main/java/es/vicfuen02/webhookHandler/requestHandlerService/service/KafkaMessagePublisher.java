package es.vicfuen02.webhookHandler.requestHandlerService.service;

import es.vicfuen02.webhookHandler.requestHandlerService.service.model.BaseEvent;

import java.io.Serializable;

public interface KafkaMessagePublisher<K extends Serializable, V extends BaseEvent> {


    void publishEvent(String topic, K key, V message);

}