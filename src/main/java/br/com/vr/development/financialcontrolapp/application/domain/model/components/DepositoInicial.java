package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositoInicial {
    
    private BigDecimal valor;

    public boolean ehMenorQue(BigDecimal valorMinimo) {
        return valor == null || valor.compareTo(valorMinimo) < 0;
    }

    public Lancamento toLancamento(ContaCorrente contaCorrente) {
        return Lancamento.criarLancamentoPositivo(valor, new Descricao("Deposito Inicial"), contaCorrente);
    }

}
