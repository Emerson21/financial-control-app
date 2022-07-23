package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

import java.io.Serializable;

public interface ContaOrigem extends Serializable {

    void saque(Valor valor, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException;

    void pagar(Fatura fatura, Valor valor) throws SaldoInsuficienteException;

    Banco getBanco();
}
