package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Qualifier("transferenciaInterna")
@AllArgsConstructor
@Document(indexName = "transacoes")
public class TransferenciaInterna implements TransacoesService {

    private ContaRepository contaRepository;

    private ApplicationEventPublisher eventPublisher;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void transacionar(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(tipoTransferencia.aplicaTaxaNo(valor), tipoTransferencia);
        contaRepository.save((Conta) origem);
        ((Conta) contaDestino).deposita(valor, tipoTransferencia);
        contaRepository.save((Conta) contaDestino);
        eventPublisher.publishEvent(new TransferenciaEvent(UUID.randomUUID(),
                "TransferenciaInterna", valor, ((Conta) contaDestino).getLancamentos(), tipoTransferencia));
    }

    private String convertToPayload(TransacaoMessage transacaoMessage) {
        try {
            return new ObjectMapper().writeValueAsString(transacaoMessage);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter mensagem {}", e);
            return "";
        }
    }

}
