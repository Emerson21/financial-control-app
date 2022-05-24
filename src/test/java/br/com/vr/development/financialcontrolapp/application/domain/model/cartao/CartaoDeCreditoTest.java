package br.com.vr.development.financialcontrolapp.application.domain.model.cartao;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.junit.jupiter.api.Test;

import static br.com.vr.development.financialcontrolapp.application.enums.Competencia.JANEIRO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartaoDeCreditoTest {

    @Test
    void deveCriarUmCartaoDeCreditoComLimiteDeMilReais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), new Fatura(JANEIRO, Vencimento.dia(5)));
        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("5000"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorDe100Reais() throws LimiteExcedidoException {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), new Fatura(JANEIRO, Vencimento.dia(5)));
        Conta destino = ContaCorrenteFixture.create();

        cartaoDeCredito.debitar(Valor.de("100"), new Descricao("Compra no cartao de credito"), destino);

        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("4900"));
        assertThat(cartaoDeCredito.lancamentos()).isNotEmpty();
        assertThat(destino.getSaldo()).isEqualTo(new Valor("150"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorAcimaDoLimiteDe5000Reais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), new Fatura(JANEIRO, Vencimento.dia(5)));
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        assertThatThrownBy(() -> cartaoDeCredito.debitar(Valor.de("5001"), new Descricao("Compra no cartao de credito"),  contaDestino));
    }

}
