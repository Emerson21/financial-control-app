package br.com.vr.development.financialcontrolapp.application.domain;

import java.util.Random;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContaCorrente {

    private AgenciaBancaria agencia;
    private Long numero;
    private int digito;
    private Correntista correntista;

    public ContaCorrente(AgenciaBancaria agencia, Correntista correntista) {
        this.agencia = agencia;
        this.numero = Long.valueOf(new Random().nextInt());
        this.digito = new Random().nextInt(10);
        this.correntista = correntista;
    }

}
