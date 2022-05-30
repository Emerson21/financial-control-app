package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Poupanca;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("transferenciaInterna")
@AllArgsConstructor
public class TransferenciaInterna implements TransacoesService {

    private ContaRepository contaRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void transacionar(Valor valor, ContaOrigem origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) throws SaldoInsuficienteException {
        origem.saque(tipoTransferencia.aplicaTaxaNo(valor), tipoTransferencia);
        contaRepository.save((Conta) origem);
        ((Conta) contaDestino).deposita(valor, tipoTransferencia);
        contaRepository.save((Conta) contaDestino);
    }
}
