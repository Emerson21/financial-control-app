package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public interface ContaOrigem {

    Valor getSaldo();

    void saque(Valor valor, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException;

    void pagar(Fatura fatura, Valor valor) throws SaldoInsuficienteException;
}
