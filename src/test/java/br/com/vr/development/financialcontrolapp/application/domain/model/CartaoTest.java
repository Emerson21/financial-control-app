package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.FuncoesCartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartaoTest {

    @Test
    void deveDebitar50ReaisDaContaCorrenteNoCartaoDeDebito() {

        FuncoesCartao funcoesCartao = new FuncoesCartao();
        funcoesCartao.adicionarFuncao(FuncaoCartao.DEBITO);

        ContaDestino contaDestino = ContaCorrenteFixture.create();

        Cartao cartao = new Cartao(funcoesCartao, ContaCorrenteFixture.create());
        cartao.debitar(new Valor("50"), FuncaoCartao.DEBITO, contaDestino);

        Assertions.assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("100"));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoCreditoNumCartaoQuePossuiSomenteDebito() {
        FuncoesCartao funcoesCartao = new FuncoesCartao();
        funcoesCartao.adicionarFuncao(FuncaoCartao.DEBITO);

        ContaDestino contaDestino = ContaCorrenteFixture.create();

        Cartao cartao = new Cartao(funcoesCartao, ContaCorrenteFixture.create());
        Assertions.assertThatThrownBy(() -> cartao.debitar(new Valor("400"), FuncaoCartao.CREDITO, contaDestino));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoDebitoNumCartaoQuePossuiSomenteCredito() {
        FuncoesCartao funcoesCartao = new FuncoesCartao();
        funcoesCartao.adicionarFuncao(FuncaoCartao.CREDITO);

        ContaDestino contaDestino = ContaCorrenteFixture.create();

        Cartao cartao = new Cartao(funcoesCartao, ContaCorrenteFixture.create());
        Assertions.assertThatThrownBy(() -> cartao.debitar(new Valor("400"), FuncaoCartao.DEBITO, contaDestino));
    }

    @Test
    void deveDebitarDaFaturaDoCartaoDeCredito50Reais() {

        FuncoesCartao funcoesCartao = new FuncoesCartao();
        funcoesCartao.adicionarFuncao(FuncaoCartao.CREDITO);

        ContaDestino contaDestino = ContaCorrenteFixture.create();

        ContaOrigem contaOrigem = new Fatura(new Limite(new Valor("1000")));

        Cartao cartao = new Cartao(funcoesCartao, contaOrigem);
        cartao.debitar(new Valor("50"), FuncaoCartao.CREDITO, contaDestino);

        Assertions.assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("100"));
        Assertions.assertThat(((Fatura)contaOrigem).getLancamentos()).isNotEmpty();
    }

}
