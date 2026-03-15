package es.vicfuen02.webhookHandler.consumer.kafka.service;


import es.vicfuen02.webhookHandler.common.model.WebHookEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaMessageConsumerImpl {



    @RetryableTopic(attempts = "2", backOff = @BackOff(delay = 1000L, multiplier = 2))
    @KafkaListener(topics="webhook-topic", groupId="webhook-group-1")
    public void consumeMessage(ConsumerRecord<String, String> record,
                               Acknowledgment acknowledgment) {

        log.info("Received event message '{}',", record);

        try {

            Thread.sleep(5000L);



            acknowledgment.acknowledge();

        } catch (Exception e) {
            log.error("Failed to process event: {}", e.getMessage());
        }

        log.info("END Received event message '{}',", record);
    }

    @DltHandler
    public void consumeEventDLT(WebHookEvent event
            ,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic
    ) {
        log.info("DLT!!! KafkaMessageConsumerImpl.consumeEventDLT" +
                " topic: ({}), event: ({})", topic, event);
    }



}
