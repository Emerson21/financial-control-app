package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValorTest {
    
    @Test
    void deveValidarValorPositivo() {
        assertThat(new Valor("0").ehNegativo()).isFalse();
    }

    @Test
    void deveValidarValorNegativo() {
        assertThat(new Valor("-0.01").ehNegativo()).isTrue();
    }

    @Test
    void deveAdicionarValor() {
        Valor um = new Valor("1");
        assertThat(new Valor("0").mais(new Valor("1"))).isEqualTo(um);
    }

    @Test
    void deveAdicionarValorBigDecimal() {
        Valor um = new Valor("1");
        assertThat(new Valor("0").mais(new BigDecimal("1"))).isEqualTo(um);
    }

    @Test
    void deveAdicionarValorZERO() {
        Valor zero = Valor.ZERO;
        assertThat(new Valor("0").mais(Valor.ZERO)).isEqualTo(zero);
    }

    @Test
    void naoDeveTerOValorAlteradoEmCasoDeUsoDaOperacaoMenos() {
        Valor valor = new Valor("100");
        valor.menos(new Valor("50"));

        assertThat(valor).isEqualTo(new Valor("100"));
    }

    @Test
    void deveTestarIgualdadeDeValoresComCasasDecimais() {
        assertThat(new Valor("1.00").compareTo(new Valor("1.00"))).isEqualTo(0);
        assertThat(new Valor("1.00").equals(new Valor("1"))).isTrue();
        assertThat(new Valor("1.01").equals(new Valor("1"))).isFalse();
        assertThat(new Valor("1.01").equals(new Valor("1.00"))).isFalse();
        assertThat(new Valor("1").equals(new Valor("1"))).isTrue();
        assertThat(new Valor("1.00").compareTo(new Valor("1"))).isEqualTo(0);
    }

}
