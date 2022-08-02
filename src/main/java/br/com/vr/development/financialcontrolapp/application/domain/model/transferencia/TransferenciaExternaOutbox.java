package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacaoExternaService;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaSolicitadaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransferenciaSolicitadaEventModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@Qualifier("transferenciaExternaOutbox")
@AllArgsConstructor
public class TransferenciaExternaOutbox implements TransacoesService, TransacaoExternaService {

    @Autowired
    private ContaRepository contaRepository;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TransferenciaSolicitadaEventRepository transferenciaSolicitadaEventRepository;

    @Override
    @Transactional
    public void transacionar(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);

        transacionarviaOutbox(correlationId, valor, origem, contaDestino, tipoTransferencia);

        eventPublisher.publishEvent(
                new TransferenciaEvent(correlationId,"TransferenciaExterna",
                        valor, contaDestino.getLancamentos(), tipoTransferencia));
    }

    private void transacionarviaOutbox(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) {
        TransacaoMessage transacaoMessage = new TransacaoMessage( correlationId.toString(), valor.toValorDTO(), origem, contaDestino, tipoTransferencia);
        TransferenciaSolicitadaEvent transferenciaSolicitadaEvent = new TransferenciaSolicitadaEvent(correlationId, transacaoMessage.toDto());

        TransferenciaSolicitadaEventModel solicitadaEventModel = new TransferenciaSolicitadaEventModel(
                correlationId,
                "transferencia_solicitada",
                transferenciaSolicitadaEvent.correlationId(),
                TransferenciaSolicitadaEvent.class.getTypeName(),
                transferenciaSolicitadaEvent
        );

        //Implementado solução com o debezium sendo um padrão de ms o transactional outbox pattern
        //Essa tabela está sendo monitorada pelo debezium que deverá publicar uma mensagem no topico outbox.event.transferencia_solicitada do kafka
        transferenciaSolicitadaEventRepository.save(solicitadaEventModel);

    }

}
