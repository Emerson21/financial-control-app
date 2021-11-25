package br.com.vr.development.financialcontrolapp.application.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.vr.development.financialcontrolapp.application.enums.TipoContaBancaria;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ContaCorrente implements ContaBancaria {

    private AgenciaBancaria agencia;
    private Long numero;
    private int digito;

    public ContaCorrente(AgenciaBancaria agencia) {
        this.agencia = agencia;
        this.numero = new Random().nextLong();
        this.digito = new Random().nextInt(10);
    }

    @Override
    public TipoContaBancaria getTipo() {
        return TipoContaBancaria.CONTA_CORRENTE;
    }

    @Override
    public BigDecimal getSaldo() {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean isPossuiLimite() {
        return false;
    }

    @Override
    public BigDecimal getLimite() {
        return BigDecimal.ZERO;
    }

    @Override
    public List<Lancamento> getLancamentos() {
        return Collections.emptyList();
    }
    
}
