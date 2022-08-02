package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
@Qualifier("transferenciaExternaMS")
@AllArgsConstructor
public class TransferenciaExternaMS implements TransacoesService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaExternaClient transferenciaExternaClient;

    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void transacionar(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);

        transacionarViaMicroServico(contaDestino);

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

}
