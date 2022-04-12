package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;

import java.time.LocalDateTime;

public enum FuncaoCartao {

    DEBITO {

        public void debitar(Valor valor, ContaOrigem contaOrigem, ContaDestino contaDestino) {
            new Transferencia(valor, contaOrigem, contaDestino, TipoTransferencia.CARTAO_DEBITO).execute();
        }
    },
    CREDITO {

        @Override
        public void debitar(Valor valor, ContaOrigem contaOrigem, ContaDestino contaDestino) {
            new Transferencia(valor, contaOrigem, contaDestino, TipoTransferencia.CARTAO_CREDITO).execute();
        }
    };

    public abstract void debitar(Valor valor, ContaOrigem contaOrigem, ContaDestino contaDestino);
}
