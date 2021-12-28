package br.com.vr.development.financialcontrolapp.application.domain.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Embeddable
public class Descricao {

    @NotBlank
    @NotNull
    private String texto;
}
