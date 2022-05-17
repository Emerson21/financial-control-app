package br.com.vr.development.financialcontrolapp.application.domain.service.transacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import org.springframework.stereotype.Service;

@Service
public class TransacoesService {

    public void transacionar(Transferencia transferencia) throws SaldoInsuficienteException {
        transferencia.execute();
    }
}
