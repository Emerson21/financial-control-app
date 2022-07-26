package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
public class TransferenciaEvent {

    @JsonProperty("correlation_id")
    private UUID correlationId;

    @JsonProperty("transacaoMessage")
    private TransacaoMessage transacaoMessage;

    public TransferenciaEvent(UUID correlationId, TransacaoMessage transacaoMessage) {
        this.correlationId = correlationId;
        this.transacaoMessage = transacaoMessage;
    }

    public String correlationId() {
        return correlationId.toString();
    }




}
