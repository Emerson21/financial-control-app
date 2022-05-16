package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {

    TED(new Valor("5"), new Descricao("TED - Transferencia entre contas correntes")),
    TEF(Valor.ZERO, new Descricao("TEF - Transferencia entre contas correntes")),
    DOC(Valor.ZERO, new Descricao("DOC - Transferencia entre contas correntes")),
    PIX(Valor.ZERO, new Descricao("PIX - Transferencia entre contas correntes")),
    DEPOSITO(Valor.ZERO, new Descricao("Transferencia entre contas correntes")),
    CARTAO_DEBITO(Valor.ZERO, new Descricao("Transacao no cartao de debito")),
    CARTAO_CREDITO(Valor.ZERO, new Descricao("Transacao no cartao de credito")),
    PAGAMENTO_DE_FATURA(Valor.ZERO, new Descricao("Pagamento de fatura de cartao de credito"));

    private Valor taxa;
    private Descricao descricao;

    TipoTransferencia(Valor taxa, Descricao descricao) {
        this.taxa = taxa;
        this.descricao = descricao;
    }

    public Valor aplicaTaxaNo(Valor valorTransferencia) {
        return taxa.mais(valorTransferencia);
    }

    public Descricao descricao() {
        return this.descricao;
    }

}
