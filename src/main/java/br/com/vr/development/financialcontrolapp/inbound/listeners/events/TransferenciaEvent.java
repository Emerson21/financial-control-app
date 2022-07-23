package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

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
