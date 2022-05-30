package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestinoInterna;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartaoTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveDebitar50ReaisDaContaCorrenteNoCartaoDeDebito() throws SaldoInsuficienteException {
        Conta destino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        cartao.debitar(new Valor("50"), new Descricao("Compra no cartao de debito"), destino);

        assertThat(destino.getSaldo()).isEqualTo(new Valor("100"));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoCreditoNumCartaoQuePossuiSomenteDebito() {
        ContaDestinoInterna contaDestino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        assertThatThrownBy(() -> cartao.debitar(new Valor("400"), new Descricao("Compra no cartao de debito"), contaDestino));
    }

    @Test
    void deveLancarExceptionAoTentarPassarAFuncaoDebitoNumCartaoQuePossuiSomenteCredito() {
        ContaDestinoInterna contaDestino = ContaCorrenteFixture.create();

        CartaoDeDebito cartao = new CartaoDeDebito(ContaCorrenteFixture.create());
        assertThatThrownBy(() -> cartao.debitar(new Valor("400"), new Descricao("Compra no cartao de debito"), contaDestino));
    }

}
