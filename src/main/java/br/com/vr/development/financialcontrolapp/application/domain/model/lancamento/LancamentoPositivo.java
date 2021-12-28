package br.com.vr.development.financialcontrolapp.application.domain.model.lancamento;

import java.math.BigDecimal;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;

public class LancamentoPositivo extends Lancamento {

    public LancamentoPositivo(BigDecimal valor, Descricao descricao, ContaCorrente contaCorrente) {
        super(valor, descricao, contaCorrente, TipoLancamento.CREDITO);
    }
    
}
