package es.vicfuen02.webhookHandler.common.idempotency.service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyModel {

    private Long id;
    private Long idempotentId;
    private String service;
    private IdempotencyEnum status;



}
