package br.com.vr.development.financialcontrolapp.application.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pessoa {
 
    @NotNull
    @Valid
    private Nome nome;

    @NotNull
    @Valid
    private Cpf documento;

    @NotNull
    @Valid
    private DataNascimento dataDeNascimento;

}
