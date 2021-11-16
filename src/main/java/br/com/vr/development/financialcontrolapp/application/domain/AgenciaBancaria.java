package br.com.vr.development.financialcontrolapp.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AgenciaBancaria {

    private Banco banco;
    private Integer numero;
    private Integer digito;

}
