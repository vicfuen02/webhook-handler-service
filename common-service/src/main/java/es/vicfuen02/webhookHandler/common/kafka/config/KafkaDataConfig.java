package es.vicfuen02.webhookHandler.common.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@Configuration
@ConfigurationProperties(prefix="kafka-config")
@PropertySources({
        @PropertySource("classpath:kafka-config.properties"),
        @PropertySource(value = "classpath:kafka-config-${spring.profiles.active}.properties",
                ignoreResourceNotFound = true)
})
@Data
public class KafkaDataConfig {

    private String bootstrapServers;

}
