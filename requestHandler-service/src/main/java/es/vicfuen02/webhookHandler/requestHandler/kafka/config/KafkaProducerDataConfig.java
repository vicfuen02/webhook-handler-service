package es.vicfuen02.webhookHandler.requestHandler.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="kafka-producer-config")
@Data
public class KafkaProducerDataConfig {


    private String keySerializer;
    private String valueSerializer;


}
