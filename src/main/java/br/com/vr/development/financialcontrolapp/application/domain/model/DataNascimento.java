package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class DataNascimento {

    @NotNull
    private LocalDate data;
    
}
