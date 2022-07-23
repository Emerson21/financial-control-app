package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;

import java.util.UUID;

public class TransferenciaSolicitadaEvent extends TransferenciaEvent {

    public TransferenciaSolicitadaEvent(UUID correlationId, TransacaoMessage transacaoMessage) {
        super(correlationId, transacaoMessage);
    }
}
