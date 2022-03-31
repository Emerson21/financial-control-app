package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.six2six.fixturefactory.Fixture;
import br.com.vr.development.financialcontrolapp.LoadFixturesSetup;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Despesa;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.DespesaException;
import br.com.vr.development.financialcontrolapp.exception.RendaException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TED;
import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.PIX;

public class RelatorioFinanceiroTest extends LoadFixturesSetup {


    @Test
    void deveExibirRelatorioFinanceiroCategorizadoPorDespesasEGanhos() {
        ContaCorrente contaCorrente = Fixture.from(ContaCorrente.class).gimme("valid");

        Lancamento lancamentoNegativo1 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("1000")), new Descricao("Pagamento mensalidade faculdade"), contaCorrente, TED);

        Lancamento lancamentoNegativo2 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("50")), new Descricao("Restaurante Java"), contaCorrente, PIX);

        Lancamento lancamentoNegativo3 = Lancamento.criaLancamentoNegativo(
                new Valor(new BigDecimal("300")), new Descricao("Madeira madeira"), contaCorrente, PIX);


        Lancamento lancamentoPositivo1 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("10000")), new Descricao("Pagamento de proventos"), contaCorrente, TED);

        Lancamento lancamentoPositivo2 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("30000")), new Descricao("Resgaste de investimento"), contaCorrente, TED);

        Lancamento lancamentoPositivo3 = Lancamento.criaLancamentoPositivo(
                new Valor(new BigDecimal("10000")), new Descricao("Pagamento de proventos"), contaCorrente, TED);

        try {
            Despesa despesa1 = new Despesa(lancamentoNegativo1, Despesa.Categoria.ESTUDO);
            Despesa despesa2 = new Despesa(lancamentoNegativo2, Despesa.Categoria.ALIMENTACAO);
            Despesa despesa3 = new Despesa(lancamentoNegativo3, Despesa.Categoria.CASA);

            Receita receita1 = new Receita(lancamentoPositivo1, Receita.Categoria.SALARIO);
            Receita receita2 = new Receita(lancamentoPositivo2, Receita.Categoria.RESGATE_INVESTIMENTO);
            Receita receita3 = new Receita(lancamentoPositivo3, Receita.Categoria.SALRIO);

            List<Despesa> despesas = Arrays.asList(despesa1, despesa2, despesa3);
            List<Receita> receitas = Arrays.asList(receita1, receita2, receita3);


            RelarioFinanceiro financeiro = new RelarioFinanceiro(despesas, receitas);

            Assertions.assertThat(financeiro.getTotalDespesas()).isEqualTo(new Valor("-1350"));
            Assertions.assertThat(financeiro.getTotalRendas()).isEqualTo(new Valor("50000"));

        } catch (DespesaException e) {
            e.printStackTrace();
        } catch (RendaException rendaException){
            rendaException.printStackTrace();
        }

    }

}
