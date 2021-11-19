package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Nome {

    private final String primeiroNome;
    private final String sobrenome;

    public String getNomeCompleto() {
        return String.format("%s %s", primeiroNome, sobrenome);
    }

}
