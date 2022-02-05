package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.AccessLevel;
import lombok.Getter;

public class Poupanca extends Conta {
    
    private AgenciaBancaria agencia;
    
    private Long numero;
    
    private int digito;

    private Correntista correntista;

    private DepositoInicial depositoInicial;

    @Getter(AccessLevel.PROTECTED) //Override abstract method com o Lombok
    private List<Lancamento> lancamentos;


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

    @Override
    public void adicionaDepositoInicialComoLancamento() {
        if (this.lancamentos == null) {
            lancamentos = new ArrayList<>();
        }
        
        this.lancamentos.add(this.depositoInicial.toLancamento(this));
    }

}
