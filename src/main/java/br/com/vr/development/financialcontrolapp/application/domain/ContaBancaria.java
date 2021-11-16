package br.com.vr.development.financialcontrolapp.application.domain;

import java.math.BigDecimal;
import java.util.List;

import br.com.vr.development.financialcontrolapp.application.enums.TipoContaBancaria;

public interface ContaBancaria {

    TipoContaBancaria getTipo();

    BigDecimal getSaldo();

    boolean isPossuiLimite();

    BigDecimal getLimite();
    
    List<Lancamento> getLancamentos();

}
