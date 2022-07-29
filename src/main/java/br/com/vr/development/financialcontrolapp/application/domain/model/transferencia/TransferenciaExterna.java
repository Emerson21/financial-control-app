package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.MessageSender;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaSolicitadaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransferenciaSolicitadaEventModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@Qualifier("transferenciaExterna")
@AllArgsConstructor
public class TransferenciaExterna implements TransacoesService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaExternaClient transferenciaExternaClient;

    @Autowired
    private MessageSender messageSender;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private TransferenciaSolicitadaEventRepository transferenciaSolicitadaEventRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transacionar(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException, JsonProcessingException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);

        UUID correlationId = UUID.randomUUID();
        //TODO - avaliar uma regra de expor essa transacao via mensagem em uma classe
        if (tipoTransferencia == TipoTransferencia.PIX) {
            this.transacionarViaMessagem(correlationId, valor, origem, contaDestino, tipoTransferencia);
        } else {
            transacionarViaMicroServico(contaDestino);
        }
        
        eventPublisher.publishEvent(
                new TransferenciaEvent(correlationId,"TransferenciaExterna",
                        valor, contaDestino.getLancamentos(), tipoTransferencia));
    }

    @CircuitBreaker(name = "ms-central-bank-cb", fallbackMethod = "fallback")
    public void transacionarViaMicroServico(ContaDestino contaDestino) {
        transferenciaExternaClient.depositar(contaDestino);
    }

    private void fallback(RuntimeException e) {
        log.error("Executando FallBack do CircuitBreak ", e);
        throw e;
    }

    private void transacionarViaMessagem(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws JsonProcessingException {
        TransacaoMessage transacaoMessage = new TransacaoMessage( correlationId.toString(), valor.toValorDTO(), origem, contaDestino, tipoTransferencia);
        TransferenciaSolicitadaEvent transferenciaSolicitadaEvent = new TransferenciaSolicitadaEvent(correlationId, transacaoMessage.toDto());

        TransferenciaSolicitadaEventModel solicitadaEventModel = new TransferenciaSolicitadaEventModel(
                UUID.randomUUID(),
                "transferencia_solicitada",
                transferenciaSolicitadaEvent.correlationId(),
                TransferenciaSolicitadaEvent.class.getTypeName(),
                transferenciaSolicitadaEvent
        );

        //Implementado solução com o debezium sendo um padrão de ms o transactional outbox pattern
        //Essa tabela está sendo monitorada pelo debezium que deverá publicar uma mensagem no topico transferencia_solicitada do kafka
        transferenciaSolicitadaEventRepository.save(solicitadaEventModel);

        //publicando evento direto em tópico do kafka
//        messageSender.publishKafka(transferenciaSolicitadaEvent);
    }

}
