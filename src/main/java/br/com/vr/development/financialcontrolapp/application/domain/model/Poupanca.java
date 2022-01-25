package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;

public class Poupanca implements Conta, ContaDestino {
    
    private AgenciaBancaria agencia;
    
    private Long numero;
    
    private int digito;

    private Correntista correntista;

    private DepositoInicial depositoInicial;

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
        adicionarLancamento(Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas"), this));
    }

    @Override
    public void adicionaDepositoInicialComoLancamento() {
        if (this.lancamentos == null) {
            lancamentos = new ArrayList<>();
        }
        
        this.lancamentos.add(this.depositoInicial.toLancamento(this));
    }

    @Override
    public void adicionarLancamento(Lancamento lancamento) {
        this.lancamentos.add(lancamento);
    }


    //codigo duplicado na conta corrente
    public boolean possuiSaldoZerado() {
        return this.lancamentos == null || this.lancamentos.isEmpty();
    }

    //codigo duplicado na classe poupanca
    //alguns comportamentos sao iguais para os dois tipos de conta. Hora de utilizar herança?
    @Override
    public Valor getSaldo() {
        return possuiSaldoZerado() 
            ? Valor.ZERO 
            : this.lancamentos.stream()
                .map(Lancamento::getValor)
                .reduce(Valor.ZERO, Valor::adicionar);
    }
}
