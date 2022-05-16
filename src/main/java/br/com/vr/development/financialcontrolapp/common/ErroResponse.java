package br.com.vr.development.financialcontrolapp.common;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ErroResponse {

    private String codigo;
    private String mensagem;

    public ErroResponse(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
