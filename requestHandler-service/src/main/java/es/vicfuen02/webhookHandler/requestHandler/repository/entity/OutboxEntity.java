package es.vicfuen02.webhookHandler.requestHandler.repository.entity;


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
    @GeneratedValue
    private Long id;

    private Long webHookId;

    @Column(unique = false, nullable = false, columnDefinition = "JSON")
    private String payload;



}
