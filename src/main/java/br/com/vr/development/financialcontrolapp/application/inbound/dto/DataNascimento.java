package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DataNascimento {

    private final LocalDate data;
    
}
