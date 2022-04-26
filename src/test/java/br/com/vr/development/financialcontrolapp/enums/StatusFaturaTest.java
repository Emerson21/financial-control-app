package br.com.vr.development.financialcontrolapp.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.StatusFatura;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusFaturaTest {

    @Test
    void deveDefinirStatusPago() {
        Valor valorPagamento = new Valor("50.00");
        Valor valorFatura = new Valor("50.00");

        StatusFatura statusFatura = StatusFatura.definir(valorPagamento, valorFatura);
        assertThat(StatusFatura.PAGA).isEqualTo(statusFatura);
    }

    @Test
    void deveDefinirStatusParcialmentePago() {
        Valor valorPagamento = new Valor("49.99");
        Valor valorFatura = new Valor("50");

        StatusFatura statusFatura = StatusFatura.definir(valorPagamento, valorFatura);
        assertThat(StatusFatura.PARCIALMENTE_PAGA).isEqualTo(statusFatura);
    }

}
