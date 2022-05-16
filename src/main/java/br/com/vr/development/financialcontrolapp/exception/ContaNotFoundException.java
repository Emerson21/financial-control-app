package br.com.vr.development.financialcontrolapp.exception;

import lombok.Getter;

@Getter
public class ContaNotFoundException extends RuntimeException {

    private String codigo = "-4";
    private String mensagem = "Conta nao encontrada";

    public ContaNotFoundException() {
        super("Conta nao encontrada");
    }

}
