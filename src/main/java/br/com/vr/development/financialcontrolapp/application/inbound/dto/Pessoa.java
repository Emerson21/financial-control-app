package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
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
