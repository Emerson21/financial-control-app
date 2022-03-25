package br.com.vr.development.financialcontrolapp.domain.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorDia;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorTipoLancamento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.PIX;

public class ImpressaoExtratoTest {

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.com.vr.development.financialcontrolapp.fixtures");
    }

    @Test
    public void deveImprimirExtrato() {
        LocalDate hoje = LocalDate.now();
        Periodo periodo = new Periodo(hoje.minusDays(1), hoje.plusDays(1));

        Agrupador agrupador = new MovimentacaoPorDia();
        Extrato extrato = new Extrato(getLancamentos(), periodo);

        new ImpressaoExtrato(extrato).imprimir(agrupador);

    }

    @Test
    public void deveImprimirExtratoPorTipolancamento() {
        LocalDate hoje = LocalDate.now();
        Periodo periodo = new Periodo(hoje.minusDays(1), hoje.plusDays(1));

        Agrupador agrupador = new MovimentacaoPorTipoLancamento();
        Extrato extrato = new Extrato(getLancamentos(), periodo);

        new ImpressaoExtrato(extrato).imprimir(agrupador);

    }

    private Set<Lancamento> getLancamentos() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");

        contaCorrente.getLancamentos().add(
                Lancamento.criaLancamentoNegativo(
                        new Valor("100"),
                        new Descricao("Lancamento Negativo Test"),
                        contaCorrente,
                        PIX));

        contaCorrente.getLancamentos().add(
                Lancamento.criaLancamentoPositivo(
                        new Valor("100"),
                        new Descricao("Lancamento Positivo Test"),
                        contaCorrente,
                        PIX));

        return contaCorrente.getLancamentos();
    }

}
