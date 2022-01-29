package br.com.vr.development.financialcontrolapp.fixtures;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class CorrentistaFixture {

    public static Correntista create() {

        return new Correntista(
                new Nome("Emerson", "Haraguchi"),
                new Cpf("29222004000"),
                TipoDocumento.CPF,
                new DataNascimento(LocalDate.of(1988, 10, 21)),
                new RendaMensal(new BigDecimal("2000")),
                new Email("thomascauajorgebarbosa-98@agnet.com.br"),
                new Celular("19", "2901-7197"),
                Arrays.asList(EnderecoFixture.create())
        );
    }

}
