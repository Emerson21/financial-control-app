package br.com.vr.development.financialcontrolapp.application.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class NomeFantasia {

    @NotBlank
    @NotNull
    private String nome;


}
