package br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.UUID;

@Document("solicitada")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransferenciaSolicitadaEventModel {

    @Id
    @JsonProperty("correlation_id")
    private UUID correlationId;

    @Field(name = "aggregatetype", targetType = FieldType.STRING)
    private String aggregateType;

    @Field(name = "aggregateid", targetType = FieldType.STRING)
    private String aggregateId;

    @Field(name = "type", targetType = FieldType.STRING)
    private String type;

    @Field(name = "payload", targetType = FieldType.IMPLICIT)
    private TransferenciaSolicitadaEvent transferenciaSolicitadaEvent;

    public String correlationId() {
        return correlationId.toString();
    }

}
