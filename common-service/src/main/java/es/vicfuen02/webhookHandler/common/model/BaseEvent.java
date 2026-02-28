package es.vicfuen02.webhookHandler.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Slf4j
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class BaseEvent implements Serializable {

    private UUID id;
    private Date createdAt;
    private Date processedAt;
    private String payload;

    public BaseEvent() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
    }





}
