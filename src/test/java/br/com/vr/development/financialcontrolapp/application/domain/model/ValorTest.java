package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

class ValorTest {
    
    @Test
    void deveValidarValorPositivo() {
        Assertions.assertThat(new Valor("0").ehNegativo()).isFalse();
    }

    @Test
    void deveValidarValorNegativo() {
        Assertions.assertThat(new Valor("-0.01").ehNegativo()).isTrue();
    }

    @Test
    void deveAdicionarValor() {
        Valor um = new Valor("1");
        Assertions.assertThat(new Valor("0").adicionar(new Valor("1"))).isEqualTo(um);
    }

    @Test
    void deveAdicionarValorBigDecimal() {
        Valor um = new Valor("1");
        Assertions.assertThat(new Valor("0").adicionar(new BigDecimal("1"))).isEqualTo(um);
    }

    @Test
    void deveAdicionarValorZERO() {
        Valor zero = Valor.ZERO;
        Assertions.assertThat(new Valor("0").adicionar(Valor.ZERO)).isEqualTo(zero);
    }



}
