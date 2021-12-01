package br.com.vr.development.financialcontrolapp.application.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataNascimento {

    @NotNull
    private LocalDate data;
    
}
