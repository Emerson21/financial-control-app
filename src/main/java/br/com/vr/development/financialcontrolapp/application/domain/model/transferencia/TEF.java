package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public class TEF {

    private Valor valor;
    private ContaCorrente origem;
    private ContaCorrente destino;


    public TEF(Valor valor, ContaCorrente origem, ContaCorrente destino) {
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
    }

    
    public void execute() {

        origem.saque(valor);
        destino.deposita(valor);
        
    }
    
}
