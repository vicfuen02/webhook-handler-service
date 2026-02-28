package es.vicfuen02.webhookHandler.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="kafka-consumer-config")
@Data
public class KafkaConsumerDataConfig {


    private String groupId;
    private String keyDeserializer;
    private String valueDeserializer;
    private String trustedPackages;


}
