package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = CartaoDeDebito.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoDeDebito.class, name = "cartaoDeDebito"),
        @JsonSubTypes.Type(value = CartaoDeCredito.class, name = "cartaoDeCredito")
})
public interface Cartao {

    void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino) throws LimiteExcedidoException, SaldoInsuficienteException;

}
