package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataNascimento implements Serializable {

    private LocalDate data;
    
}
