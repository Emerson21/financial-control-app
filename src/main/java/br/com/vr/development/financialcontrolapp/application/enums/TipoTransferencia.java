package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {

    TED(new Valor("5"), new Descricao("Transferencia entre contas correntes")),
    TEF(Valor.ZERO, new Descricao("Transferencia entre contas correntes")),
    PIX(Valor.ZERO, new Descricao("Transferencia entre contas correntes")),
    DEPOSITO(Valor.ZERO, new Descricao("Transferencia entre contas correntes")),
    CARTAO_DEBITO(Valor.ZERO, new Descricao("Transacao no cartao de debito"));

    private Valor taxa;
    private Descricao descricao;

    TipoTransferencia(Valor taxa, Descricao descricao) {
        this.taxa = taxa;
        this.descricao = descricao;
    }

    public Valor aplicaTaxaNo(Valor valorTransferencia) {
        return taxa.adicionar(valorTransferencia);
    }

    public Descricao descricao() {
        return this.descricao;
    }

}
