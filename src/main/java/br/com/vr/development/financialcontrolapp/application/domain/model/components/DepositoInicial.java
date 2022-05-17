package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositoInicial {
    
    private static final String DESCRICAO_DEPOSITO_INICIAL = "Deposito Inicial";

    private BigDecimal valor;

    public boolean ehMenorQue(BigDecimal valorMinimo) {
        return valor == null || valor.compareTo(valorMinimo) < 0;
    }

    public Lancamento toLancamento(Conta conta) {
        return Lancamento.criaLancamentoPositivo(
                new Valor(valor.toString()),
                new Descricao(DESCRICAO_DEPOSITO_INICIAL),
                conta,
                TipoTransferencia.DEPOSITO);
    }

}
