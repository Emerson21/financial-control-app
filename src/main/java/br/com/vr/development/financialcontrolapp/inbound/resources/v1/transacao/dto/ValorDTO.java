package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class ValorDTO {

    @JsonIgnore
    public static final ValorDTO ZERO = new ValorDTO(BigDecimal.ZERO);

    @Column(name = "valor")
    private BigDecimal value;

    @JsonIgnore
    public ValorDTO soma(ValorDTO valorDTO) {
        return new ValorDTO(valorDTO.getValue().add(value));
    }

    @JsonIgnore
    public ValorDTO somaIgnorandoSinal(ValorDTO valorDTO) {
        return new ValorDTO(valorDTO.getValue().abs().add(value.abs()));
    }

}
