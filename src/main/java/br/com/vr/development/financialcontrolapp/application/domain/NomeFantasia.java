package br.com.vr.development.financialcontrolapp.application.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NomeFantasia {

    @NotBlank
    @NotNull
    private String nome;


}
