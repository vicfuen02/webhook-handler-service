package es.vicfuen02.webhookHandler.consumer.kafka.config;

import es.vicfuen02.webhookHandler.common.kafka.config.KafkaDataConfig;
import es.vicfuen02.webhookHandler.common.kafka.model.BaseEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class KafkaConsumerConfig<K extends Serializable, V extends BaseEvent> {


    private KafkaDataConfig kafkaDataConfig;
    private KafkaConsumerDataConfig kafkaConsumerDataConfig;


    @Bean
    public Map<String, Object> consumerConfig() {

        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,             kafkaDataConfig.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,        kafkaConsumerDataConfig.getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,      kafkaConsumerDataConfig.getValueDeserializer());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG ,                     kafkaConsumerDataConfig.getGroupId());
        properties.put(JacksonJsonDeserializer.TRUSTED_PACKAGES,            kafkaConsumerDataConfig.getTrustedPackages());

        return properties;
    }

    @Bean
    public ConsumerFactory<K, V> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE); // ← manual ack

        return factory;
    }





}
