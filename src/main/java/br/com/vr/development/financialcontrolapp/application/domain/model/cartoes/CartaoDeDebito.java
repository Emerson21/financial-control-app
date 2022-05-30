package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartaoDeDebito implements Cartao {

    @Getter
    @Setter
    private ContaCorrente contaCorrente;

    public CartaoDeDebito(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    @Override
    public void debitar(Valor valor, Descricao descricao, ContaDestinoInterna contaDestino) throws SaldoInsuficienteException {

        contaCorrente.saque(valor, TipoTransferencia.CARTAO_DEBITO);
        contaDestino.deposita(valor, TipoTransferencia.DEPOSITO);

    }
}
