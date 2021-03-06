package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.six2six.fixturefactory.Fixture;
import br.com.vr.development.financialcontrolapp.LoadFixturesSetup;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Despesa;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.DespesaException;
import br.com.vr.development.financialcontrolapp.exception.RendaException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.PIX;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TED;
import static org.assertj.core.api.Assertions.assertThat;

public class RelatorioFinanceiroPorCategoriaTest extends LoadFixturesSetup {

    @Test
    void deveExibirRelatorioFinanceiroCategorizadoPorDespesasEGanhos() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");

        try {
            List<Transacao> transacoes = new ArrayList<>();

            transacoes.addAll(getDespesas(contaCorrente));
            transacoes.addAll(getReceitas(contaCorrente));

            RelatorioFinanceiroPorCategoria totalPorCategoria = new RelatorioFinanceiroPorCategoria(transacoes);

            TotalPorCategoria resgateInvestimento = totalPorCategoria.getTotalPorCategorias().stream().filter(total -> total.getCategoria() == Transacao.Categoria.RESGATE_INVESTIMENTO).findFirst().get();
            TotalPorCategoria estudo = totalPorCategoria.getTotalPorCategorias().stream().filter(total -> total.getCategoria() == Transacao.Categoria.ESTUDO).findFirst().get();
            TotalPorCategoria salario = totalPorCategoria.getTotalPorCategorias().stream().filter(total -> total.getCategoria() == Transacao.Categoria.SALARIO).findFirst().get();
            TotalPorCategoria casa = totalPorCategoria.getTotalPorCategorias().stream().filter(total -> total.getCategoria() == Transacao.Categoria.CASA).findFirst().get();
            TotalPorCategoria alimentacao = totalPorCategoria.getTotalPorCategorias().stream().filter(total -> total.getCategoria() == Transacao.Categoria.ALIMENTACAO).findFirst().get();

            assertThat(resgateInvestimento.getValor()).isEqualTo(new Valor("30000"));
            assertThat(estudo.getValor()).isEqualTo(new Valor("-1000"));
            assertThat(salario.getValor()).isEqualTo(new Valor("20000"));
            assertThat(casa.getValor()).isEqualTo(new Valor("-300"));
            assertThat(alimentacao.getValor()).isEqualTo(new Valor("-50"));

        } catch (DespesaException e) {
            e.printStackTrace();
        } catch (RendaException rendaException){
            rendaException.printStackTrace();
        }

    }

    private List<Despesa> getDespesas(ContaCorrente contaCorrente) throws DespesaException {
        Lancamento lancamentoNegativo1 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("1000")), new Descricao("Pagamento mensalidade faculdade"), contaCorrente, TED);

        Lancamento lancamentoNegativo2 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("50")), new Descricao("Restaurante Java"), contaCorrente, PIX);

        Lancamento lancamentoNegativo3 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("300")), new Descricao("Madeira madeira"), contaCorrente, PIX);

        Despesa despesa1 = new Despesa(lancamentoNegativo1, Despesa.Categoria.ESTUDO);
        Despesa despesa2 = new Despesa(lancamentoNegativo2, Despesa.Categoria.ALIMENTACAO);
        Despesa despesa3 = new Despesa(lancamentoNegativo3, Despesa.Categoria.CASA);

        return Arrays.asList(despesa1, despesa2, despesa3);
    }

    private List<Receita> getReceitas(ContaCorrente contaCorrente) throws RendaException {
        Lancamento lancamentoPositivo1 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("10000")), new Descricao("Pagamento de proventos"), contaCorrente, TED);

        Lancamento lancamentoPositivo2 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("30000")), new Descricao("Resgaste de investimento"), contaCorrente, TED);

        Lancamento lancamentoPositivo3 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("10000")), new Descricao("Pagamento de proventos"), contaCorrente, TED);


        Receita receita1 = new Receita(lancamentoPositivo1, Receita.Categoria.SALARIO);
        Receita receita2 = new Receita(lancamentoPositivo2, Receita.Categoria.RESGATE_INVESTIMENTO);
        Receita receita3 = new Receita(lancamentoPositivo3, Receita.Categoria.SALARIO);

        return Arrays.asList(receita1, receita2, receita3);
    }

}
