package es.vicfuen02.webhookHandler.requestHandler.service.model;


import es.vicfuen02.webhookHandler.common.model.BaseEvent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WebHookEvent extends BaseEvent {

    private String webhookEventId;
    private Date receivedAt;
}
