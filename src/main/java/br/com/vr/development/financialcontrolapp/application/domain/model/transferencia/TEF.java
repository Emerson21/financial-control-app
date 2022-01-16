package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public class TEF implements Transferencia {

    private Valor valor;
    private ContaCorrente origem;
    private ContaCorrente destino;


    public TEF(Valor valor, ContaCorrente origem, ContaCorrente destino) {
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
    }

    @Override
    public void execute() {

        if (!origem.possuiSaldoDisponivel(valor)) {
            throw new SaldoInsuficienteException();
        }

        origem.saque(valor);
        destino.deposita(valor);
        
    }
    
}
