package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.util.Random;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;

public class Poupanca extends Conta implements ContaDestino {
    
    private AgenciaBancaria agencia;
    
    private Long numero;
    
    private int digito;

    private Correntista correntista;


    public Poupanca(AgenciaBancaria agencia, Correntista correntista, DepositoInicial depositoInicial) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(Integer.MAX_VALUE);
        this.correntista = correntista;
        super.depositoInicial = depositoInicial;
        super.adicionaDepositoInicialComoLancamento();
    }

    @Override
    public void deposita(Valor valor) {
        adicionaLancamento(Lancamento.criaLancamentoPositivo(valor, new Descricao("Transferencia entre contas"), this));
    }

}
