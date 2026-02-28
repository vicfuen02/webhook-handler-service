package es.vicfuen02.webhookHandler.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix="kafka-config")
@Data
public class KafkaDataConfig {

    private String bootstrapServers;

}
