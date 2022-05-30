package br.com.vr.development.financialcontrolapp.application.domain.model.fatura;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.YearMonth;

import static br.com.vr.development.financialcontrolapp.application.enums.Competencia.FEVEREIRO;
import static br.com.vr.development.financialcontrolapp.application.enums.Competencia.JANEIRO;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

class FaturaTest {

    @Test
    void deveCriarUmaFaturaComUmaCompetencia() {

        Fatura fatura = new Fatura(Competencia.JANEIRO, Vencimento.dia(5));

        Periodo periodo = (Periodo) ReflectionTestUtils.getField(fatura, "periodo");
        assertThat(periodo.getDataInicial()).isEqualTo(YearMonth.of(LocalDate.now().getYear(), JANUARY).atDay(1));
        assertThat(periodo.getDataFinal()).isEqualTo(YearMonth.of(LocalDate.now().getYear(), JANUARY).atDay(31));
    }

    @Test
    void deveTrocarDeStatusParaPagaQuandoOValorForOMesmoDaFatura() {
        Fatura fatura = new Fatura(Competencia.FEVEREIRO, Vencimento.dia(5));
        fatura.novoLancamento(new Valor("100"), new Descricao("Compra no restaurante"));
        fatura.pagar(new Valor("100"));
        assertThat(fatura.isPaga()).isTrue();
    }

    @Test
    void deveTrocarDeStatusParaParcialmentePagaQuandoOValorDePagamentoForMenorQueOValorDaFatura() {
        Fatura fatura = new Fatura(Competencia.MARCO, Vencimento.dia(5));
        fatura.novoLancamento(new Valor("100"), new Descricao("Compra no restaurante"));
        fatura.pagar(new Valor("99.99"));
        assertThat(fatura.isParcialmentePaga()).isTrue();
    }

    @Test
    void deveRealizarOPagamentoDaFaturaNoSeuValorTotal() throws Exception {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), new Fatura(JANEIRO, Vencimento.dia(5)));
        ContaCorrente contaOrigem = ContaCorrenteFixture.create();
        contaOrigem.deposita(new Valor("450"), TipoTransferencia.DEPOSITO);

        ContaDestinoInterna contaDestino = ContaCorrenteFixture.create();
        cartaoDeCredito.debitar(Valor.de("500"), new Descricao("Compra no cartao de credito"), contaDestino);

        Valor limite = cartaoDeCredito.limite();
        Valor valorPagamento = new Valor("500");

        cartaoDeCredito.pagarFatura(JANEIRO, valorPagamento, contaOrigem);

        assertThat(cartaoDeCredito.valorFatura(JANEIRO)).isEqualTo(new Valor("500"));
        assertThat(cartaoDeCredito.fatura(JANEIRO).isPaga()).isTrue();
        assertThat(cartaoDeCredito.limite()).isEqualTo(limite.mais(valorPagamento));
    }

    @Test
    void deveRealizarOPagamentoParcialDoValorDaFatura() throws Exception {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), new Fatura(JANEIRO, Vencimento.dia(5)));
        ContaCorrente contaOrigem = ContaCorrenteFixture.create();
        contaOrigem.deposita(new Valor("450"), TipoTransferencia.DEPOSITO);

        ContaDestinoInterna contaDestino = ContaCorrenteFixture.create();
        cartaoDeCredito.debitar(Valor.de("500"), new Descricao("Compra no cartao de credito"), contaDestino);

        Valor limite = cartaoDeCredito.limite();
        Valor valorPagamento = new Valor("300");

        cartaoDeCredito.pagarFatura(JANEIRO, valorPagamento, contaOrigem);

        assertThat(cartaoDeCredito.valorFatura(FEVEREIRO)).isEqualTo(new Valor("200"));
        assertThat(cartaoDeCredito.fatura(JANEIRO).isParcialmentePaga()).isTrue();
        assertThat(cartaoDeCredito.limite()).isEqualTo(limite.mais(valorPagamento));
    }
}
