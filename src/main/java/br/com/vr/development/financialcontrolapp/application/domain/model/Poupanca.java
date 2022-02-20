package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "poupanca", schema = "financial_app")
public class Poupanca extends Conta {

    public Poupanca(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        super(agencia, correntista, depositoInicial);
    }

}
