package es.vicfuen02.webhookHandler.common.idempotency.respository;


import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="IDEMPOTENT_EVENTS",
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_idempotent_service", columnNames = {"idempotent_id", "service"})
        }
)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_idempotent_id_gen")
    @SequenceGenerator(name="seq_idempotent_id_gen", sequenceName="SEQ_IDEMPTONENT_ID", initialValue = 1000, allocationSize = 2)
    private Long id;

    @Column(nullable = false)
    private String idempotentId;

    @Column(nullable = false, length = 50)
    private String service;

    @Enumerated(value = EnumType.STRING)
    private IdempotencyEnum status;



}
