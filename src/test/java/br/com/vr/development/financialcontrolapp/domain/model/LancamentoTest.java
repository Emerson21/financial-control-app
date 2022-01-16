package br.com.vr.development.financialcontrolapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;

public class LancamentoTest {
    
    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.vr.development.financialcontrolapp.fixtures");
    }

    @Test
    void deveCriarUmLancamentoComValorPositivo() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");
        BigDecimal money = new BigDecimal("50");

        Lancamento lancamento = Lancamento.criaLancamentoPositivo(new Valor(money), new Descricao("Descricao Test"), contaCorrente);

        assertThat(lancamento).isNotNull();
        assertThat(lancamento.getValor()).isEqualTo(new Valor(money));
    }

    // @Test
    // void deveLancarExcecaoLancamentoInvalidoExceptionAoCriarUmLancamentoPositivoPassandoUmValorNegativo() {
    //     ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");
    //     BigDecimal money = new BigDecimal("50");

    //     assertThrows(LancamentoInvalidoException.class, () -> {
    //         Lancamento.criaLancamentoPositivo(new Valor(money.negate()), new Descricao("Descricao Test"), contaCorrente);
    //     });

    // }

    @Test
    void deveCriarUmLancamentoDeDebitoContendoUmValorNegativo() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");
        BigDecimal money = new BigDecimal("50");

        Lancamento lancamento = Lancamento.criaLancamentoNegativo(new Valor(money), new Descricao("Descricao Test"), contaCorrente);

        assertThat(lancamento).isNotNull();
        assertThat(lancamento.getValor()).isEqualTo(new Valor(money.negate()));

    }

    // @Test
    // void deveLancarExcecaoLancamentoInvalidoExceptionAoCriarUmLancamentoNegativoPassandoUmValorPositivo() {
    //     ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");
    //     BigDecimal money = new BigDecimal("50");

    //     assertThrows(LancamentoInvalidoException.class, () -> {
    //         Lancamento.criaLancamentoNegativo(new Valor(money), new Descricao("Descricao Test"), contaCorrente);
    //     });

    // }

}
