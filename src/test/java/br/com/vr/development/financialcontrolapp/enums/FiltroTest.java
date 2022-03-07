package br.com.vr.development.financialcontrolapp.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import br.com.vr.development.financialcontrolapp.application.enums.Filtro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FiltroTest {

    @Test
    public void deveCalcularPeriodo() {
        for (Filtro filtro : Filtro.values()) {
            Periodo periodo = filtro.calcular();
            Assertions.assertThat(periodo.getDataInicial()).isEqualTo(LocalDate.now().minusDays(filtro.getDias()));
        }
    }

}
