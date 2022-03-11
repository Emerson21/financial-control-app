package br.com.vr.development.financialcontrolapp.domain.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ExtratoTest {

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.vr.development.financialcontrolapp.fixtures");
    }

    @Test
    public void deveFiltrarAListaDeMovimentacoesDeAcordoComOPeriodo() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");
        LocalDate hoje = LocalDate.now();

        Periodo periodo = new Periodo(hoje.minusDays(1), hoje.plusDays(1));
        contaCorrente.getLancamentos().add(Lancamento.criaLancamentoNegativo(new Valor("100"), new Descricao("Lancamento Negativo Test"), contaCorrente));
        contaCorrente.getLancamentos().add(Lancamento.criaLancamentoPositivo(new Valor("100"), new Descricao("Lancamento Positivo Test"), contaCorrente));
        contaCorrente.getLancamentos().add(Lancamento.criaLancamentoNegativo(new Valor("100"), new Descricao("Lancamento Negativo Test"), contaCorrente));
        contaCorrente.getLancamentos().add(Lancamento.criaLancamentoPositivo(new Valor("100"), new Descricao("Lancamento Positivo Test"), contaCorrente));

        Extrato extrato = new Extrato(contaCorrente.getLancamentos(), periodo);
        Assertions.assertThat(extrato.getMovimentacoes()).isNotNull();
        Assertions.assertThat(extrato.getMovimentacoes().getMovimentacoesPorDia()).isNotEmpty();
        Assertions.assertThat(extrato.getMovimentacoes().getMovimentacoesPorDia().get(hoje)).hasSize(contaCorrente.getLancamentos().size());

    }

}
