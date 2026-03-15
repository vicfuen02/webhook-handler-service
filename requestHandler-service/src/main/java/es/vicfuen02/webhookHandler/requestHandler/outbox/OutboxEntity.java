package es.vicfuen02.webhookHandler.requestHandler.outbox;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="OUTBOX_EVENTS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_outbox_id_gen")
    @SequenceGenerator(name="seq_outbox_id_gen", sequenceName="SEQ_OUTBOX_ID", initialValue = 1000, allocationSize = 2)
    private Long id;

    private Long eventId;

    @Column(unique = false, nullable = false, columnDefinition = "JSON")
    private String payload;

    @Enumerated(value = EnumType.STRING)
    private OutboxEventEnum status;



}
