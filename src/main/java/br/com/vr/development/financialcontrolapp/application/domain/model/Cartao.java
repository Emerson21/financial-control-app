package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;

public interface Cartao {

    void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino);

}
