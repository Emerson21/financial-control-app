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
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.PIX;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImpressaoExtratoTest extends LoadFixturesSetup {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Mock
    private SaidaExtrato saidaExtrato;

    @BeforeEach
    void setup() {
        saidaExtrato = Mockito.mock(SaidaExtrato.class);
    }

    @Test
    void deveImprimirExtrato() {
        LocalDate hoje = LocalDate.now();
        LocalDate ontem = LocalDate.now().minusDays(1);
        LocalDate amanha = LocalDate.now().plusDays(1);

        Periodo periodo = new Periodo(ontem.minusDays(1), amanha.plusDays(1));

        Set<Lancamento> lancamentos = getLancamentos();

        Extrato extrato = new Extrato(lancamentos, periodo);
        new ImpressaoExtrato(extrato, saidaExtrato).imprimir(new MovimentacaoPorDia());

        String tituloHojeExtratoComCor = "\u001B[32m" + "Data : "+ hoje +" | Movimentacoes";
        String tituloOntemExtratoComCor = "\u001B[32m" + "Data : "+ ontem +" | Movimentacoes";
        String tituloAmanhaExtratoComCor = "\u001B[32m" + "Data : "+ amanha +" | Movimentacoes";


        verify(saidaExtrato, times(1)).imprimir(eq(tituloHojeExtratoComCor));
        verify(saidaExtrato, times(1)).imprimir(eq(tituloOntemExtratoComCor));
        verify(saidaExtrato, times(1)).imprimir(eq(tituloAmanhaExtratoComCor));

        lancamentos.forEach(lancamento -> {
            verify(saidaExtrato, times(1)).imprimir(eq("\tCREDITO | PIX | Valor: R$ 100,00 | Descricao: Lancamento Positivo Test | " + hoje.atStartOfDay().format(formatter)));
            verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + ontem.atStartOfDay().format(formatter)));
            verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + amanha.atStartOfDay().format(formatter)));
        });

    }

    @Test
    void deveImprimirExtratoPorTipolancamento() {
        LocalDate hoje = LocalDate.now();
        LocalDate ontem = LocalDate.now().minusDays(1);
        LocalDate amanha = LocalDate.now().plusDays(1);

        Periodo periodo = new Periodo(ontem.minusDays(1), amanha.plusDays(1));

        Set<Lancamento> lancamentos = getLancamentos();
        Extrato extrato = new Extrato(lancamentos, periodo);

        new ImpressaoExtrato(extrato, saidaExtrato).imprimir(new MovimentacaoPorTipoLancamento());

        verify(saidaExtrato).imprimir(eq("\u001B[32m"+"Tipo Lancamento : CREDITO | Movimentacoes"));
        verify(saidaExtrato).imprimir(eq("\u001B[32m"+"Tipo Lancamento : DEBITO | Movimentacoes"));

        lancamentos.forEach(lancamento -> {
            verify(saidaExtrato, times(1)).imprimir(eq("\tCREDITO | PIX | Valor: R$ 100,00 | Descricao: Lancamento Positivo Test | " + hoje.atStartOfDay().format(formatter)));
            verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + ontem.atStartOfDay().format(formatter)));
            verify(saidaExtrato, times(1)).imprimir(eq("\tDEBITO | PIX | Valor: -R$ 100,00 | Descricao: Lancamento Negativo Test | " + amanha.atStartOfDay().format(formatter)));
        });
    }

    @Test
    void imprimindoNoConsole() {
        LocalDate ontem = LocalDate.now().minusDays(1);
        LocalDate amanha = LocalDate.now().plusDays(1);

        Periodo periodo = new Periodo(ontem.minusDays(1), amanha.plusDays(1));

        Set<Lancamento> lancamentos = getLancamentos();
        Extrato extrato = new Extrato(lancamentos, periodo);

        List<Agrupador> agrupadores = Arrays.asList(new MovimentacaoPorDia(), new MovimentacaoPorTipoLancamento());
        agrupadores.forEach(agrupador -> new ImpressaoExtrato(extrato, new SaidaExtrato()).imprimir(agrupador));
    }

    private Set<Lancamento> getLancamentos() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");

        Lancamento lancamentoNegativo = Lancamento.criaLancamentoNegativo(
                new Valor("100"),
                new Descricao("Lancamento Negativo Test"),
                contaCorrente,
                PIX);

        Lancamento lancamentoNegativo2 = Lancamento.criaLancamentoNegativo(
                new Valor("100"),
                new Descricao("Lancamento Negativo Test"),
                contaCorrente,
                PIX);

        Lancamento lancamentoPositivo = Lancamento.criaLancamentoPositivo(
                new Valor("100"),
                new Descricao("Lancamento Positivo Test"),
                contaCorrente,
                PIX);

        ReflectionTestUtils.setField(lancamentoNegativo, "dataHora", LocalDate.now().atStartOfDay().minusDays(1));
        ReflectionTestUtils.setField(lancamentoNegativo2, "dataHora", LocalDate.now().atStartOfDay().plusDays(1));
        ReflectionTestUtils.setField(lancamentoPositivo, "dataHora", LocalDate.now().atStartOfDay());

        contaCorrente.getLancamentos().add(lancamentoNegativo);
        contaCorrente.getLancamentos().add(lancamentoNegativo2);
        contaCorrente.getLancamentos().add(lancamentoPositivo);

        return contaCorrente.getLancamentos();
    }

}

