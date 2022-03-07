package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import static br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento.criaLancamentoNegativo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "conta_corrente")
public class ContaCorrente extends Conta {

    public ContaCorrente(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        super(agencia, correntista, depositoInicial);
    }

    public String getCodigoBanco() {
        return this.agencia.getBanco().getCodigo();
    }

    public NomeFantasia getNomeFantasia() {
        return this.agencia.getBanco().getNomeFantasia();
    }





}
