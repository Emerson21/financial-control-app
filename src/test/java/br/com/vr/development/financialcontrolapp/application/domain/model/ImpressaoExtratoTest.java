package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.vr.development.financialcontrolapp.LoadFixturesSetup;
import br.com.vr.development.financialcontrolapp.application.domain.model.extrato.Extrato;
import br.com.vr.development.financialcontrolapp.application.domain.model.extrato.ImpressaoExtrato;
import br.com.vr.development.financialcontrolapp.application.domain.model.extrato.SaidaExtrato;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorDia;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorTipoLancamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.PIX;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImpressaoExtratoTest extends LoadFixturesSetup {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Mock
    SaidaExtrato saidaExtrato;


    @BeforeEach
    void setup() {
        saidaExtrato = Mockito.mock(SaidaExtrato.class);
    }

    @Test
    public void deveImprimirExtrato() {
        LocalDate hoje = LocalDate.now();
        Periodo periodo = new Periodo(hoje.minusDays(1), hoje.plusDays(1));

        Set<Lancamento> lancamentos = getLancamentos();
        String dataFormatada = lancamentos.stream().findFirst().get().getDataHora().format(formatter);

        Extrato extrato = new Extrato(lancamentos, periodo);
        new ImpressaoExtrato(extrato, saidaExtrato).imprimir(new MovimentacaoPorDia());

        String tituloExtratoComCor = "\u001B[32m" + "Data : "+ hoje +" | Movimentacoes";

        verify(saidaExtrato, times(1)).imprimir(eq(tituloExtratoComCor));
        verify(saidaExtrato, times(1)).imprimir(eq("\tCREDITO | PIX | Valor: R$ 100,00 | Descricao: Lancamento Positivo Test | " + dataFormatada));
        verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + dataFormatada));
    }

    @Test
    public void deveImprimirExtratoPorTipolancamento() {
        LocalDate hoje = LocalDate.now();
        Periodo periodo = new Periodo(hoje.minusDays(1), hoje.plusDays(1));
        Set<Lancamento> lancamentos = getLancamentos();
        String dataFormatada = lancamentos.stream().findFirst().get().getDataHora().format(formatter);

        Extrato extrato = new Extrato(lancamentos, periodo);

        new ImpressaoExtrato(extrato, saidaExtrato).imprimir(new MovimentacaoPorTipoLancamento());

        verify(saidaExtrato).imprimir(eq("\u001B[32m"+"Tipo Lancamento : CREDITO | Movimentacoes"));
        verify(saidaExtrato).imprimir(eq("\u001B[32m"+"Tipo Lancamento : DEBITO | Movimentacoes"));
        verify(saidaExtrato, times(1)).imprimir(eq("\tCREDITO | PIX | Valor: R$ 100,00 | Descricao: Lancamento Positivo Test | " + dataFormatada));
        verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + dataFormatada));
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

