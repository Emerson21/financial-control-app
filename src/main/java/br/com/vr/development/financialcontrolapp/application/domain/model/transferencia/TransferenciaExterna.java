package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("transferenciaExterna")
@AllArgsConstructor
public class TransferenciaExterna implements TransacoesService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaExternaClient transferenciaExternaClient;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void transacionar(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(valor, tipoTransferencia);
        contaRepository.save((ContaCorrente) origem);
        transferenciaExternaClient.depositar(contaDestino);
    }
}
