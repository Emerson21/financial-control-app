package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Random;

import static br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento.criaLancamentoNegativo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta_corrente", schema = "financial_app")
public class ContaCorrente extends Conta implements ContaOrigem {

    public ContaCorrente(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.depositoInicial = depositoInicial;
        this.adicionaDepositoInicialComoLancamento();
    }

    public String getCodigoBanco() {
        return this.agencia.getBanco().getCodigo();
    }

    public NomeFantasia getNomeFantasia() {
        return this.agencia.getBanco().getNomeFantasia();
    }

    public boolean possuiSaldoDisponivel(Valor valor) {
        return super.getSaldo().compareTo(valor) >= 0;
    }

    @Override
    public void deposita(Valor valor) {
        super.adicionar(
            Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas correntes"), this)
        );


    }

    @Override
    public void saque(Valor valor) {
        if (!possuiSaldoDisponivel(valor)) {
            throw new SaldoInsuficienteException();
        }

        adicionar(
            criaLancamentoNegativo(valor, new Descricao("Transferencia entre contas correntes"), this)
        );
    }

}
