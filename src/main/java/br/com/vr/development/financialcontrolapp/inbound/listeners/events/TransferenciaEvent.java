package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
public class TransferenciaEvent {

    @Id
    @JsonProperty("_id")
    private UUID correlationId;

    @Field(name = "transacao_message", targetType = FieldType.IMPLICIT)
    @JsonProperty("transacaoMessage")
    private TransacaoMessageDTO transacaoMessage;

    public TransferenciaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        this.correlationId = correlationId;
        this.transacaoMessage = transacaoMessage;
    }

    public String correlationId() {
        return correlationId.toString();
    }

}
