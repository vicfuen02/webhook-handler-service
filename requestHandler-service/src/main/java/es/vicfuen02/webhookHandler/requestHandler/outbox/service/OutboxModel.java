package es.vicfuen02.webhookHandler.requestHandler.outbox.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboxModel {


    private Long id;
    private Long outboxId;
    private String payload;
    private OutboxEventEnum status;



}
