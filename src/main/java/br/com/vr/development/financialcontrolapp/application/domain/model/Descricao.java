package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@AllArgsConstructor
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Descricao {

    @NotBlank
    @NotNull
    private String texto;
}
