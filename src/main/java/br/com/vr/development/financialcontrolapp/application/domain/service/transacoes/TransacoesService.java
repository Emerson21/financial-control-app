package br.com.vr.development.financialcontrolapp.application.domain.service.transacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TransacoesService {
    void transacionar(Valor valor, ContaOrigem contaOrigem, ContaDestino
            contaDestino, TipoTransferencia tipo) throws SaldoInsuficienteException, JsonProcessingException;
}
