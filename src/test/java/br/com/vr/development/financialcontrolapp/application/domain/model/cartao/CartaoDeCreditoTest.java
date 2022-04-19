package br.com.vr.development.financialcontrolapp.application.domain.model.cartao;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Limite;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeCredito;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.exception.LimiteIndisponivelException;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartaoDeCreditoTest {

    @Test
    void deveCriarUmCartaoDeCreditoComLimiteDeMilReais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), Competencia.JANEIRO, Vencimento.dia(5));
        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("5000"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorDe100Reais() throws LimiteIndisponivelException {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), Competencia.FEVEREIRO, Vencimento.dia(10));
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        cartaoDeCredito.debitar(Valor.de("100"), new Descricao("Compra no cartao de credito"), contaDestino);

        assertThat(cartaoDeCredito.limite()).isEqualTo(new Valor("4900"));
        assertThat(cartaoDeCredito.lancamentos()).isNotEmpty();
        assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("150"));
    }

    @Test
    void deveRealizarUmaTransacaoDeUmaCompraNoValorAcimaDoLimiteDe5000Reais() {
        CartaoDeCredito cartaoDeCredito = new CartaoDeCredito(new Limite(new Valor("5000")), Competencia.MARCO, Vencimento.dia(15));
        ContaDestino contaDestino = ContaCorrenteFixture.create();

        Assertions.assertThatThrownBy(() -> cartaoDeCredito.debitar(Valor.de("5001"), new Descricao("Compra no cartao de credito"),  contaDestino));
    }

}
