package br.com.vr.development.financialcontrolapp.application.domain.model.cartao;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.application.enums.StatusFatura;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static br.com.vr.development.financialcontrolapp.application.enums.Competencia.JANEIRO;
import static br.com.vr.development.financialcontrolapp.application.enums.StatusFatura.PAGA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CartaoDeCreditoTest {

    @Test
    void deveCriarUmCartaoDeCreditoComLimiteDeMilReais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")));
        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("5000"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorDe100Reais() throws LimiteExcedidoException {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")));
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        cartaoDeCredito.debitar(Valor.de("100"), new Descricao("Compra no cartao de credito"), contaDestino);

        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("4900"));
        assertThat(cartaoDeCredito.lancamentos()).isNotEmpty();
        assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("150"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorAcimaDoLimiteDe5000Reais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")));
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        assertThatThrownBy(() -> cartaoDeCredito.debitar(Valor.de("5001"), new Descricao("Compra no cartao de credito"),  contaDestino));
    }

    @Test
    void deveRealizarOPagamentoDaFaturaNoSeuValorTotal() throws Exception {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")));
        ContaCorrente contaOrigem = ContaCorrenteFixture.create();
        contaOrigem.deposita(new Valor("450"), TipoTransferencia.DEPOSITO);

        ContaDestino contaDestino = ContaCorrenteFixture.create();
        cartaoDeCredito.debitar(Valor.de("500"), new Descricao("Compra no cartao de credito"), contaDestino);

        Valor limite = cartaoDeCredito.limite();
        Valor valorPagamento = new Valor("500");

        cartaoDeCredito.pagarFatura(JANEIRO, valorPagamento, contaOrigem);

        assertThat(cartaoDeCredito.valorFatura(JANEIRO)).isEqualTo(new Valor("500"));
        assertThat(cartaoDeCredito.fatura(JANEIRO).status()).isEqualTo(PAGA);
        assertThat(cartaoDeCredito.limite()).isEqualTo(limite.mais(valorPagamento));
    }

}
