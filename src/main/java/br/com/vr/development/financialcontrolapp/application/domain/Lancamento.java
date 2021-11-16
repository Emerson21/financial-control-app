package br.com.vr.development.financialcontrolapp.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Lancamento {

    private LocalDateTime dataHora;
    private TipoLancamento tipoLancamento;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;

}
