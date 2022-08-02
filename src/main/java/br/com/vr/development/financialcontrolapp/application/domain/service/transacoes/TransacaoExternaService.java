package br.com.vr.development.financialcontrolapp.application.domain.service.transacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

import java.util.UUID;

public interface TransacaoExternaService {

    void transacionar(UUID correlationId, Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException;

}
