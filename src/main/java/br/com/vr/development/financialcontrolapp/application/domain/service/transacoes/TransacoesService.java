package br.com.vr.development.financialcontrolapp.application.domain.service.transacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public interface TransacoesService {
    void transacionar(Transferencia transferencia) throws SaldoInsuficienteException;
}
