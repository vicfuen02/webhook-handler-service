package es.vicfuen02.webhookHandler.common.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class KafkaHelper {



    public String createPayload(Object payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(payload);
        } catch (JacksonException e) {
            log.error("Error parsing payload");
            throw new RuntimeException("Error parsing payload");
        }
    }

    public <T> T getDeserializedPayload(String payload, Class<T> type) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(payload, type);
        } catch (JacksonException e) {
            log.error("Error deserializing payload");
            throw new RuntimeException("Error deserializing payload");
        }
    }


}
