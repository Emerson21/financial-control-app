package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public interface ITransferencia {

    void execute() throws SaldoInsuficienteException;

}
