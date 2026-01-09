package es.vicfuen02.webhookHandler.requestHandler_service.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebHookModel {

    private String eventId;
    private String topic;
    private String payload;
    private Date receivedAt;

}
