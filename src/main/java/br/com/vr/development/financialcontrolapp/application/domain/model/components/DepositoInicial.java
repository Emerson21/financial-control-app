package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
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

    public Lancamento toLancemento(ContaCorrente contaCorrente) {
        return Lancamento.builder()
            .contaCorrente(contaCorrente)
            .dataHora(LocalDateTime.now())
            .descricao("Deposito Inicial")
            .tipo(TipoLancamento.CREDITO)
            .valor(this.valor)
            .build();
    }

}
