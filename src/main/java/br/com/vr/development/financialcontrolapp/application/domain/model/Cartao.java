package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = CartaoDeDebito.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoDeDebito.class, name = "cartaoDeDebito"),
        @JsonSubTypes.Type(value = CartaoDeCredito.class, name = "cartaoDeCredito")
})
public interface Cartao {

    void debitar(Valor valor, Descricao descricao, ContaDestinoInterna contaDestino) throws LimiteExcedidoException, SaldoInsuficienteException;

}
