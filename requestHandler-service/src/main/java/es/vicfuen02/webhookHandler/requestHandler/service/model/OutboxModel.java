package es.vicfuen02.webhookHandler.requestHandler.service.model;


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
    private Long webHookId;
    private String payload;



}
