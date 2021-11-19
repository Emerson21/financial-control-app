package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import br.com.vr.development.financialcontrolapp.application.commons.Documento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Pessoa {
 
    private final Nome nome;
    private final Documento documento;
    private final DataNascimento dataDeNascimento;

}
