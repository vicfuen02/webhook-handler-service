package es.vicfuen02.webhookHandler.requestHandler.idempotency;


import es.vicfuen02.webhookHandler.requestHandler.outbox.OutboxEventEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="IDEMPOTENT_EVENTS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_idempotent_id_gen")
    @SequenceGenerator(name="seq_idempotent_id_gen", sequenceName="SEQ_IDEMPTONENT_ID", initialValue = 1000, allocationSize = 2)
    private Long id;

    @Column(unique = false, nullable = false)
    private Long idempotentId;

    @Column(unique = false, nullable = false, length = 50)
    private String service;

    @Enumerated(value = EnumType.STRING)
    private IdempotencyEnum status;



}
