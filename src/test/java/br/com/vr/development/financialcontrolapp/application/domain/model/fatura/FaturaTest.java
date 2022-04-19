package br.com.vr.development.financialcontrolapp.application.domain.model.fatura;

import br.com.vr.development.financialcontrolapp.application.domain.model.Fatura;
import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

public class FaturaTest {

    @Test
    void deveCriarUmaFaturaComUmaCompetencia() {

        Fatura fatura = new Fatura(Competencia.JANEIRO, Vencimento.dia(5));

        Periodo periodo = (Periodo) ReflectionTestUtils.getField(fatura, "periodo");
        assertThat(periodo.getDataInicial()).isEqualTo(YearMonth.of(LocalDate.now().getYear(), JANUARY).atDay(1));
        assertThat(periodo.getDataFinal()).isEqualTo(YearMonth.of(LocalDate.now().getYear(), JANUARY).atDay(31));
    }


}
