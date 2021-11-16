package br.com.vr.development.financialcontrolapp.application.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import br.com.vr.development.financialcontrolapp.application.enums.TipoContaBancaria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ContaCorrente implements ContaBancaria {

    private AgenciaBancaria agencia;
    private Long numero;
    private Long digito;

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
