package br.com.vr.development.financialcontrolapp.application.domain;

import br.com.vr.development.financialcontrolapp.application.commons.Cnpj;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.Nome;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Banco {
 
    private Nome nome;
    private String codigo;
    private Cnpj cnpj;

}
