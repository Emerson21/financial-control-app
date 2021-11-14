package br.com.vr.development.financialcontrolapp.application.domain;

import br.com.vr.development.financialcontrolapp.application.commons.Cnpj;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Banco {
 
    private String nome;
    private String codigo;
    private Cnpj cnpj;

}
