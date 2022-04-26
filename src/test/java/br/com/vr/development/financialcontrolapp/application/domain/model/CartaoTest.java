package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartaoTest {

    @Test
    void deveDebitar50ReaisDaContaCorrenteNoCartaoDeDebito() throws SaldoInsuficienteException {
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        cartao.debitar(new Valor("50"), new Descricao("Compra no cartao de debito"), contaDestino);

        assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("100"));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoCreditoNumCartaoQuePossuiSomenteDebito() {
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        assertThatThrownBy(() -> cartao.debitar(new Valor("400"), new Descricao("Compra no cartao de debito"), contaDestino));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoDebitoNumCartaoQuePossuiSomenteCredito() {
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        assertThatThrownBy(() -> cartao.debitar(new Valor("400"), new Descricao("Compra no cartao de debito"), contaDestino));
    }

}
