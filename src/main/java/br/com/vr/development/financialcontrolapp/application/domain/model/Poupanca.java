package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "poupanca", schema = "financial_app")
public class Poupanca extends Conta {

    public Poupanca(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        this.depositoInicial = depositoInicial;
        this.adicionaDepositoInicialComoLancamento();
    }

    @Override
    public void deposita(Valor valor) {
        adicionar(Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas"), this));
    }

}
