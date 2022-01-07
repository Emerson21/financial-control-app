package br.com.vr.development.financialcontrolapp.application.domain.model;

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
public class Nome {

    @NotBlank
    @NotNull
    private String primeiroNome;

    @NotBlank
    @NotNull
    private String sobrenome;

    public String getNomeCompleto() {
        return String.format("%s %s", primeiroNome, sobrenome);
    }

    public Nome(String nome) {
        this.primeiroNome = nome;
        this.sobrenome = "";
    }

}
