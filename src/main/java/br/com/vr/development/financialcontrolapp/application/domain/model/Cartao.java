package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public interface Cartao {

    void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino) throws LimiteExcedidoException, SaldoInsuficienteException;

}
