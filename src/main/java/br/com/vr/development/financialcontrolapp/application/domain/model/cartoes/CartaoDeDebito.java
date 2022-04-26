package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public class CartaoDeDebito implements Cartao {

    private ContaCorrente contaCorrente;

    public CartaoDeDebito(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    @Override
    public void debitar(Valor valor, Descricao descricao, ContaDestino contaDestino) throws SaldoInsuficienteException {
        new Transferencia(valor, contaCorrente, contaDestino, TipoTransferencia.CARTAO_DEBITO).execute();
    }
}
