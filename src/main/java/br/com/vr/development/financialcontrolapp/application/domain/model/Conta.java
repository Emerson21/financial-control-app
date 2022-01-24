package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;

public abstract class Conta {

    @Getter
    @OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL)
    List<Lancamento> lancamentos;
    

    @Embedded
    @NotNull
    @Column(name = "deposito_inicial", nullable = false)
    DepositoInicial depositoInicial;

    public void adicionaDepositoInicialComoLancamento() {
        if (this.lancamentos == null) {
            lancamentos = new ArrayList<>();
        }
        
        this.lancamentos.add(this.depositoInicial.toLancamento(this));
    }
    
    public boolean possuiSaldoZerado() {
        return this.lancamentos == null || this.lancamentos.isEmpty();
    }

    void adicionaLancamento(Lancamento lancamento) {
        this.lancamentos.add(lancamento);
    }

}
