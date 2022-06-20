package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.MessageSender;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;

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

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transacionar(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);

        //TODO - avaliar uma regra de expor essa transacao via mensagem em uma classe
        if (tipoTransferencia == TipoTransferencia.PIX) {
            this.transacionarViaMessagem(valor, origem, contaDestino, tipoTransferencia);
        } else {
            transacionarViaMicroServico(contaDestino);
        }
        eventPublisher.publishEvent(new TransferenciaEvent("TransferenciaExterna",  valor, ((Conta) contaDestino).getLancamentos(), tipoTransferencia));
    }

    @CircuitBreaker(name = "ms-central-bank-cb", fallbackMethod = "fallback")
    public void transacionarViaMicroServico(ContaDestino contaDestino) {
        transferenciaExternaClient.depositar(contaDestino);
    }

    private void fallback(RuntimeException e) {
        log.error("Executando FallBack do CircuitBreak ", e);
        throw e;
    }

    private void transacionarViaMessagem(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) {
        TransacaoMessage transacaoMessage = new TransacaoMessage(valor.toValorDTO(), origem, contaDestino, tipoTransferencia);
        try {
            String payload = new ObjectMapper().writeValueAsString(transacaoMessage);

            messageSender.publish(payload);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter mensagem {}", e);
        }

    }

}
