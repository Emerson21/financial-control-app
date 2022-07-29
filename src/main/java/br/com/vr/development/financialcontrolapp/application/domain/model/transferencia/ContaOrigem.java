package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Poupanca;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = ContaCorrente.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrente.class, name = "contaCorrente"),
        @JsonSubTypes.Type(value = Poupanca.class, name = "poupanca")
})
public interface ContaOrigem extends Serializable {

    void saque(Valor valor, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException;

    void pagar(Fatura fatura, Valor valor) throws SaldoInsuficienteException;

    Banco getBanco();

    void removerDependenciaCiclicaEntreAgenciaEBanco();

    TransacaoMessageDTO.ContaOrigem toContaOrigemDTO();
}
