package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Nome implements Serializable {

    private String primeiroNome;
    private String sobrenome;

    public String getNomeCompleto() {
        return String.format("%s %s", primeiroNome, sobrenome);
    }

}
