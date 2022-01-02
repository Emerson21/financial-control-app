package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import java.math.BigDecimal;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;

public class LancamentoNegativo extends Lancamento {
    
    public LancamentoNegativo(BigDecimal valor, Descricao descricao, ContaCorrente contaCorrente) {
        super(valor.negate(), descricao, contaCorrente, TipoLancamento.DEBITO);
    }

}
