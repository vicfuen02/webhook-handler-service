package es.vicfuen02.webhookHandler.common.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SerializerHelperImpl<T> {


    private final ObjectMapper objectMapper;


    public <T> String serialize(T data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize {} object", data, e);
            throw new RuntimeException(e);
        }
    }


    public <T> T deserialize(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            log.error("Could not read {} object", outputType.getName(), e);
            throw new RuntimeException("Could not read " + outputType.getName() + " object." + e);
        }
    }

}
