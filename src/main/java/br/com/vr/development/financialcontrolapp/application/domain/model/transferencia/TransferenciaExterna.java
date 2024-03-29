package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.MessageSender;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacaoExternaService;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
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
@Qualifier("transferenciaExterna")
@AllArgsConstructor
public class TransferenciaExterna implements TransacoesService, TransacaoExternaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MessageSender messageSender;

    private ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void transacionar(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);

        this.transacionarViaMessagem(correlationId, valor, origem, contaDestino, tipoTransferencia);

        eventPublisher.publishEvent(
                new TransferenciaEvent(correlationId,"TransferenciaExterna",
                        valor, contaDestino.getLancamentos(), tipoTransferencia));
    }

    private void transacionarViaMessagem(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) {
        TransacaoMessage transacaoMessage = new TransacaoMessage( correlationId.toString(), valor.toValorDTO(), origem, contaDestino, tipoTransferencia);
        messageSender.publishKafka(new TransferenciaSolicitadaEvent(correlationId, transacaoMessage.toDto()));
    }

}
