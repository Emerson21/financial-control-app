package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Poupanca;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = ContaCorrente.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrente.class, name = "contaCorrente"),
        @JsonSubTypes.Type(value = Poupanca.class, name = "poupanca")
})
public interface ContaOrigem {

    Valor getSaldo();

    void saque(Valor valor, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException;

    void pagar(Fatura fatura, Valor valor) throws SaldoInsuficienteException;
}
